public class LinkedListDS {
    //?
    static Node head;
    static class Node{
        int data;
        Node next;
        // Constructor to create a new node
        // Next is by default initialized
        // as null
        Node(int d) {
            data = d;
        }
    }
    public void reverseLinkedList(){
        Node prev=null;
        Node current= head;
        Node next=null;
        while (current.next != null){
            next=current.next;
            current.next=prev;
            //keep current as prev then go forward
            prev= current;
            //go forward
            current=next;

        }
    }

}
