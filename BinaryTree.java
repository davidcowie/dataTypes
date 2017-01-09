
public class BinaryTree<T> {
	
	private Node<T> root;
	// probably should store the size
	
	public BinaryTree(){
		root = null;
		
	}
	
	public Node<T> addRoot(T data){
		// if root is not equal to null it will crash the program
		assert(root == null); // assert is where the programer might have screwed up and excpections are for if the user screws up
		//if(root != null){
		root = new Node<T>(data);
		/*}else{
			
		}*/
		return root;
	}
	
	public Node<T> addRight(Node<T> n, T data){
		assert(n.right == null);
		n.right = new Node<T>(data);
		return n.right;
	}
	public Node<T> addLeft(Node<T> n, T data){
		assert(n.left == null);
		n.left = new Node<T>(data);
		return n.left;
	}
	
	public interface Visitor<T>{
		void visit(Node<T> n); 
	}
	
	public void preorderTraversal(Visitor<T> v){
		preorderRecursive(root,v);
	}
	
	
	private void preorderRecursive(Node<T> n, Visitor<T> v) {
		if(n != null){
			v.visit(n);
			//System.out.print("n data: " + n.getData());
			preorderRecursive(n.left,v);
			//System.out.println("");
			//System.out.println("doing the right side");
			//System.out.print("n data post left side: " + n.getData());
			preorderRecursive(n.right,v);
		}
	}
	
	public void inOrderTraversal(Visitor<T> v){
		inOrderRecursive(root,v);
	}


	private void inOrderRecursive(Node<T> n, Visitor<T> v) {
		if(n!= null){
			inOrderRecursive(n.left,v);
			v.visit(n);
			inOrderRecursive(n.right,v);
		}
	}
	public void postOrderTraversal(Visitor<T> v){
		postOrderRecursive(root,v);
	}

	private void postOrderRecursive(Node<T> n, Visitor<T> v) {
		if(n != null){
			postOrderRecursive(n.left,v);
			postOrderRecursive(n.right,v);
			v.visit(n);
		}
	}

	//dont need a refrence to our tree inside of our node
	public static class Node<T> {
		private T data;
		private Node<T> left, right;
		private Node<T> parent; // dont need to store the parent
		
		
		public Node(T data){
			this.data = data;
			left = null;
			right = null;
		}
		
		
		public T getData(){
			return data;
		}
		public Node<T> getLeft(){
			return left;
		}
		
	}

}
