/**
 * Created by David on 3/22/2016.
 */
//could turn into generic to make it more versitile
public class SinglyLinkedList {

	// give each node a reference to the previous node as well
    // node class cannot refer to the list that it is appart of
    private static class Node{
        private int data;
        private Node next;

        public Node(){

        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private int size;
    private Node head,tail;
    public SinglyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addFront(int a){
        Node n = new Node(a,head);
        if(tail == null){//
            tail = n;
        }
        size++;
        head = n;
    }
    public void addLast(int a){
        if(isEmpty()){
            addFront(a);
        }else{
            Node n = new Node(a,null);
            tail.setNext(n);
            tail = n;
            size++;
        }
    }

    public Integer removeFirst(){
        if(isEmpty()){
            return null;
        }else{
            if(size == 0){
                tail = null;
            }
            int ret = head.getData();
            head= head.getNext();
            size--;
            return ret;
        }
    }

    public Integer removeLast(){
        if(isEmpty()){
            return null;
        }
        int ret = tail.getData();
        if(size == 1){
            return removeFirst();
        }
        Node cursor;
        for(cursor = head; cursor.getNext() != tail; cursor= cursor.getNext()){ // or cursor.getNext() == tail
        }
        //cursor points to second to last node
        tail = cursor;
        cursor.setNext(null);
        size--;

        return ret;

    }
    public Integer min(){
        if(isEmpty()){
            return null;
        }
        int min = Integer.MAX_VALUE; // this works because there is nothing larger than this so everything will be smaller so the head will become the min value the first time through
        for(Node cursor = head; cursor != null; cursor = cursor.getNext()){
            int val = cursor.getData();
            if(val < min){
                min = val;
            }
        }

        return min;
    }
    public Integer max(){
        if(isEmpty()){
            return null;
        }
        int max = Integer.MIN_VALUE; // or could have max = head.getData()
        for(Node cursor = head; cursor != null; cursor = cursor.getNext()){
            int val = cursor.getData();
            if(val > max){
                max = val;
            }
        }

        return max;
    }


    public String toString(){
        String s= "";
        for (Node cursor = head;cursor !=null; cursor = cursor.getNext()) {
            s += cursor.getData() +" ";
        }
        return s;
    }

    public boolean isEmpty(){
        if (size ==0){
            assert (head == null);
            assert (tail == null);
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }
}
