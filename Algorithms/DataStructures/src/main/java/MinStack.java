public class MinStack {
    private int top;
    private int minPointer;
    private int[] obj;
    private int stackSize;

    public MinStack(int stackSize) {
        this.top = -1;
        this.minPointer=-1;
        this.obj = new int[stackSize];
        this.stackSize=stackSize;
    }

    public int peek(){
        if(isEmpty()){
            return 0;
        }
        else {
            return obj[top];
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
            if(peek()<minPointer) minPointer=top;
            obj[top++]=newElement;
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
            return obj[minPointer];
        }
    }
}
