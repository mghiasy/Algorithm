package runtime;

import util.RandomPermutations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

import java.util.*;


public class SortTester {
	/*
	 * Better way
	 
	 private enum SIZE { 
	     LIMITED, MID, UNLIMITED
	 }
	 private final SIZE VALUE_LIMIT = SIZE.LIMITED;
	 */
	 
	//LIMITED means values in test array lie in the range 0...length-1, all distinct
	//UNLIMITED means values in test array lie in range 0..MAX_VAL, may have duplicates
	 
	private final int LIMITED = 0; //largest value is size of array, no dup values
	private final int MID = 1;  //largest value is 10 million 
	private final int UNLIMITED = 2; //largest value is Integer.MAX_VALUE
	private final int VALUE_LIMIT = LIMITED;
	private final String PACKAGE = "sortroutines";
	private final String FILENAME = "src/main/sorters_to_be_run.txt";
	private final int[] ARRAY_SIZES = {3000, 7000, 8000, 1000}; 
	private final int NUM_TESTS = 20;
	private final int ACTUAL_NUM_TESTS = ARRAY_SIZES.length * (NUM_TESTS/ARRAY_SIZES.length);
	private int numSortRoutines = 0;
	private int[][][] testArrays;
	private TreeMap<Long,String> results = new TreeMap<Long,String>();
	private Stack<Sorter> sorters;
	public static void main(String[] args){
		SortTester st = new SortTester();
		st.runTests();
	}
	
	public SortTester() {
		sorters = new Stack<Sorter>();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
			String line = null;
			while( (line = reader.readLine()) != null) {
				Sorter next = createInstance(line);
				sorters.push(next);
			}
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unused")
	private void loadTestArrays(int numRoutines) {
		testArrays = new int[numRoutines][ACTUAL_NUM_TESTS][];
		int len = ARRAY_SIZES.length;
		int round = ACTUAL_NUM_TESTS/ARRAY_SIZES.length;
		for(int k = 0; k < numRoutines; ++k ){
			for(int q=0; q<len; ++q) {
				for(int j = q*round; j < (q+1)*round; ++j) {
					if(k==0 && VALUE_LIMIT==LIMITED){
						testArrays[k][j] = RandomPermutations.nextArray2(ARRAY_SIZES[q]);
					}
					else if(k==0 && VALUE_LIMIT ==UNLIMITED) {
						testArrays[k][j] = RandomPermutations.unlimitedArrWithDups(ARRAY_SIZES[q]);
					}
					else if(k==0 && VALUE_LIMIT ==MID) {
						testArrays[k][j] = RandomPermutations.midArrWithDups(ARRAY_SIZES[q]);
					}
					else {
						testArrays[k][j] = new int[ARRAY_SIZES[q]];
						for(int i = 0; i < ARRAY_SIZES[q]; ++i){
							testArrays[k][j][i] = testArrays[0][j][i];
						}
					}
				}
			}
		}
			
		
		
		/* the old way when we used just 4 different array sizes
		for(int k = 0; k < numRoutines; ++k ){
			for(int j = 0; j < ARRAY_SIZES.length; ++j){
				for(int q = 0; q < round;++q) {
				
					if(k==0 && IS_LIMITED) {
						testArrays[k][round*j+q] = RandomPermutations.nextArray2(ARRAY_SIZES[j]);
					}
					else if(k==0 && !IS_LIMITED) {
						testArrays[k][round*j+q] = RandomPermutations.unlimitedArrWithDups(ARRAY_SIZES[j]);
					}
					else {
						testArrays[k][j] = new int[ARRAY_SIZES[j]];
						for(int i = 0; i < ARRAY_SIZES[j]; ++i){
							testArrays[k][j][i] = testArrays[0][j][i];
						}
					}
				}
			}
			*/
			
			/*
			for(int j = 0; j < NUM_TESTS/4; ++j){
				if(k==0 && IS_LIMITED){
					testArrays[k][j] = RandomPermutations.nextArray2(ARRAY_SIZES[0]);
				}
				else if(k==0 && !IS_LIMITED) {
					testArrays[k][j] = RandomPermutations.unlimitedArrWithDups(ARRAY_SIZES[0])
				}
				else {
					testArrays[k][j] = new int[ARRAY_SIZES[0]];
					for(int i = 0; i < ARRAY_SIZES[0]; ++i){
						testArrays[k][j][i] = testArrays[0][j][i];
					}
				}
			}
			for(int j = NUM_TESTS/4; j < NUM_TESTS/2; ++j){
				if(k==0){
					testArrays[k][j] = RandomPermutations.nextArray2(ARRAY_SIZES[1]);
				}
				else {
					testArrays[k][j] = new int[ARRAY_SIZES[1]];
					for(int i = 0; i < ARRAY_SIZES[1]; ++i){
						testArrays[k][j][i] = testArrays[0][j][i];
					}
				}
			}	
			for(int j = NUM_TESTS/2; j < 3*NUM_TESTS/4; ++j){
				if(k==0){
					testArrays[k][j] = RandomPermutations.nextArray2(ARRAY_SIZES[2]);
				}
				else {
					testArrays[k][j] = new int[ARRAY_SIZES[2]];
					for(int i = 0; i < ARRAY_SIZES[2]; ++i){
						testArrays[k][j][i] = testArrays[0][j][i];
					}
				}
			}	
			for(int j = 3*NUM_TESTS/4; j < NUM_TESTS; ++j){
				if(k==0){
					testArrays[k][j] = RandomPermutations.nextArray2(ARRAY_SIZES[3]);
				}
				else {
					testArrays[k][j] = new int[ARRAY_SIZES[3]];
					for(int i = 0; i < ARRAY_SIZES[3]; ++i){
						testArrays[k][j][i] = testArrays[0][j][i];
					}
				}
			}*/				
			
		
		
	}
	public void runTests() {
		numSortRoutines=sorters.size();
		loadTestArrays(numSortRoutines);
		//printArray(testArrays);

		long start = 0L;
		long finish = 0L;
		int sortIndex = -1;
		long[] nextTimeArr = null;
		while(!sorters.isEmpty()) {
			Sorter nextSorter = sorters.pop();
			nextTimeArr = new long[ACTUAL_NUM_TESTS];
			sortIndex++;
			for(int i = 0; i < ACTUAL_NUM_TESTS; ++i){
				start = time();
				
				nextSorter.sort(testArrays[sortIndex][i]);
				
				finish = time();
				//System.out.println(Arrays.toString(newarr));
				/* debugging 
				if(i==5 || i ==15){
					System.out.println(Arrays.toString(testArrays[0][i]));
				}*/
				nextTimeArr[i] = finish-start;
			}
			String name = util.Util.getClassNameNoPackage(nextSorter.getClass());
			results.put(sum(nextTimeArr),name);
		}
		showResults();
	}
	public void showResults() {
		Set<Long> keySet = results.keySet();
		List<Long> list = new ArrayList<Long>(keySet);
		//Collections.sort(list);
		//TreeMap specs guarantee sorted order
		for(int i = 0; i < list.size(); ++i){
			Long time = list.get(i);
			String classname = results.get(time);
			System.out.println((time/1000000)+" ms -> "+ classname);
		}
	
	}
	private long sum(long[] arr) {
		long accum = 0L;
		for(long e: arr){
			accum += e;
		}
		return accum;
	}

	
	private Sorter createInstance(String className) throws Exception {
		className = PACKAGE+"."+className;
		Class cl = Class.forName(className);
		Object ob = cl.newInstance();
		return (Sorter)ob;
		
	}

	static long time() {
		return System.nanoTime();
		//return System.currentTimeMillis();
	}
	static void printArray(int[][][] arr) {
		for(int i = 0; i < arr.length; ++i) {
			System.out.println(""+i+": ");
			for(int j= 0; j < arr[i].length; ++j) {
				System.out.println("  "+j+":");
				System.out.println("       "+Arrays.toString(arr[i][j]));
			}
		}
	}
}

