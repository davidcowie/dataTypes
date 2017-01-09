
//generic class
public class DoubleLinkedList <T>{
	
	private int size;
	private Node<T> header,trailer; // adding extra null to add to front and end so not special cases
	
	public DoubleLinkedList(){
		header = new Node<>(null,null,null);
		trailer = new Node<>(null,header,null);
		header.setNext(trailer);
		size = 0;
	}
	
	public void swapElements(int n1, int n2){
		if(n1 < 0 || n2 < 0 || n1 > size || n2 > size){
			throw new ArrayIndexOutOfBoundsException();
		}
		int currNode =0;
		Node<T> first = null;
		Node<T> second = null;
		for(Node<T> node = header.getNext(); node != trailer; node = node.getNext()){
			currNode++;
			if(currNode == n1){
				first = node;
			}
			if(currNode == n2){
				second = node;
			}
		}
		//Node<T> intermediate = first; // this didnt work because when changed first- intermediate also changed
		Node<T> fpreT = first.getPrevious();
		Node<T> fnextT = first.getNext();

		first.setPrevious(second.getPrevious());
		//log("first previous: " + first.getPrevious().getData());
		first.setNext(second.getNext());
		//log("first next: " + first.getNext().getData());
		//the above part points to the right things
		//log("Pre change second previous: " + second.getPrevious().getData());
		//log("PRE change second next: " + second.getNext().getData());
		second.getPrevious().setNext(first);
		second.getNext().setPrevious(first);
		//log("WHAT should be pointing to first(both should equal 2): " + second.getPrevious().getNext().getData());
		//log("SHWAT SHould be poitning to fitst next " + second.getNext().getPrevious().getData());
		//all the above has things pointing correctly
		
		second.setPrevious(fpreT);
		second.setNext(fnextT);
		//log("Second new previous: " + second.getPrevious().getData());
		//log("SEcond new next: " + second.getNext().getData());
		fpreT.setNext(second);
		fnextT.setPrevious(second);
		//intermediate.getNext().setPrevious(second);
		//intermediate.getPrevious().setNext(second);
	}
	private void log(String s){
		System.out.println(s);
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	
	public void addFirst(T data){
		addBetween(data,header,header.getNext());
	}
	public void addLast(T data){
		addBetween(data,trailer.getPrevious(),trailer);
	}

	
	private void addBetween(T t,Node<T> b, Node<T> next) {
		
		Node<T> newNode = new Node<>(t,b,next);
		b.setNext(newNode);
		next.setPrevious(newNode);
		size++;
		
	}
	public T removeFirst(){
		return remove(header.getNext());
	}
	public T removeLast(){
		return remove(trailer.getPrevious());
	}
	private T remove(Node<T> next) {
		//dont delete the sentinels
		if(next == header || next == trailer){
			return null;
		}
		next.getPrevious().setNext(next.getNext());
		next.getNext().setPrevious(next.getPrevious());
		size--;
		return next.getData();
		
	}
	public String toString(){
		String s = "";
		
		for(Node n = header.getNext(); n != trailer;n = n.getNext()){
			s+= n.getData() + " ";
		}
		return s;
	}


	//dont think would have the problem if had a copy constructor and returned new Nodes so they point to different
	// spots in memory
		//also has to be generic
		private static class Node <T>{
			private Node<T> next,previous;
			private T data;
			
			public Node(T data,Node<T> pre, Node<T> nex){
				this.data = data;
				this.previous = pre;
				this.next = nex;
			}
			public Node(Node n){
				this.data = (T) n.getData();
				this.previous = n.getPrevious();
				this.next = n.getNext();
			}
			
			public T getData(){
				return data;
			}
			public Node<T> getNext(){
				return next;
			}
			public Node<T> getPrevious(){
				return previous;
			}
			public void setNext(Node<T> n){
				next = n;
			}
			public void setPrevious(Node<T> p){
				previous = p;
			}
		}


		
}
