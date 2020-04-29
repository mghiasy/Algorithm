public class MinStack {
    private int top;
    private int minValue;
    private int[] obj;
    private int stackSize;
    private int[] sortedList;

    //insert 2* newElement - minElement instead of newElement
    //so that previous minimum element can be retrieved

    public MinStack(int stackSize) {
        this.top = -1;
        this.minValue=0;
        this.obj = new int[stackSize];
        this.stackSize=stackSize;
        this.sortedList=new int[stackSize];
    }

    public int peek(){
        if(isEmpty()){
            return 0;
        }
        else {
            int topElement=obj[top];
            if(topElement>minValue) {
                return topElement;
            }
            // If t < minEle means minEle stores value of t.
            else{
                //return (topElement+minValue)/2;
                return minValue;
            }
        }
    }

    public boolean isEmpty(){
        if (top==-1) return  true;
        else return  false;
    }

    public void push(int newElement){
        if(top>= stackSize){
            System.out.println("Stack is full");
            return;
        }
        else {
            //If x is greater than or equal to minValue, simply insert x.
            if(peek()<= minValue) {
                obj[top++]=newElement;
            }
            //If x is less than minValue, insert (2*x – minEle) into the stack and make minEle equal to x
            else{
                obj[top++]=newElement*2 - minValue;
                //new value of minValue is equal to newElement
                minValue=newElement;
            }

        }
    }

    public int pop(){
        int o;
        if(isEmpty()){
            System.out.println("Stack is full");
            return 0;
        }
        else{
            o=obj[top];

            //So we have changed minValue in this point
            //we should recalculate it
            if(o<minValue) {
                //minValue was newElement in push
                minValue=2*minValue-o;
            }
            top--;
            return o;
        }
    }
    public int min(){
        if(isEmpty()){
            System.out.println("Stack is full");
            return 0;
        }
        else{
            return minValue;
        }
    }
}
