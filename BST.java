import java.util.Iterator;

//want K's to all implement comparable
public class BST<K extends Comparable<K>> implements Set<K> {

	protected BSTNode<K> root;
	protected int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	// search to look for a key and find it mayne
	//return node with key, k, aor the last node on the search path
	private BSTNode<K> search(K k){
		if(root == null){
			return null;
		}
		BSTNode<K> cursor = root;
		while(true){
			int compare = k.compareTo(cursor.data);
			if(compare == 0){ // then data is the same found it
				return cursor;
			}else if(compare <0){ // means k is less 
				if(cursor.left == null){
					return cursor;
				}else{
					cursor = cursor.left;
				}
			}else{
				if(cursor.right == null){
					return cursor;
				}else{
					cursor = cursor.right;
				}
			}
		}
		
	}
	
	@Override
	public boolean add(K k) {
		if(isEmpty()){
			root = makeNode(k,null);
			size = 1;
			return true;
		}
		
		BSTNode<K> n = search(k);
		int compare = k.compareTo(n.data);
		//System.out.println("K:" + k + " n.data " + n.data + " compare to = " + compare);
		
		if(compare == 0){ // it is already there so not adding it to tree
			return false;
		}else if(compare < 0){
			n.left = makeNode(k,n); // search only returns something without k in it if the left child is empty
		}else{
			n.right = makeNode(k,n);
		}
		
		size++;
		rebalanceInsert(n); // if have a BST tree then itll do nothing but if have an AVL tree itll do that stuff
		return true;
		
	}

	@Override
	public boolean contains(K k) {
		
		if(isEmpty()){
			return false;
		}
		return search(k).data.equals(k);
	}

	@Override
	public boolean remove(K k) {
		
		BSTNode<K> n = search(k);
		if(n == null || !n.data.equals(k)){
			return false;
		}
		
		//hard case
		if(n.left != null && n.right != null){
			BSTNode<K> pred = predecessor(n);
			n.data = pred.data;
			//now want to delete pred
			n = pred;
		}
		// want to delete n, which has 0 or 1 children
		
		BSTNode<K> survivor = n.left;
		if(n.left == null){survivor = n.right;}
		
		if(n == root){
			root = survivor;
			if(survivor != null){
				survivor.parent = null;
			}
			size--;
			return true;
		}
		
		boolean isLeft = n == n.parent.left;
		if(isLeft){
			n.parent.left = survivor;
		}else{
			n.parent.right = survivor;
		}
		if(survivor != null){
			survivor.parent = n.parent;
		}
		
		size--;
		if(n.parent != null){
			rebalanceDelete(n.parent);
		}
		return true;
	}
	// can only call this when n has a left subtree
	private BSTNode<K> predecessor(BSTNode<K> n){
		n = n.left;
		while(n.right != null){
			n = n.right;
		}
		return n;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size ==0;
	}

	@Override
	public Iterator<K> iterator() {
		throw new UnsupportedOperationException("nope");
	}
	
	
	public String toString(){
		return inOrderPrint(root);
	}
	
	
	// go to the left then go to the right then go to here
	private String inOrderPrint(BSTNode<K> n) {
		if(n == null){return "";}
		return inOrderPrint(n.left) + ","+ n.data + ", " + inOrderPrint(n.right);
	}

	// factory method
	// want to create objects in a heirachry but might want a subclass of it
	//but cant override constructors so can use this and override this method
	protected BSTNode<K> makeNode(K data, BSTNode<K> p){
		return new BSTNode<>(data,p);
	}
	
	// this are default and do nothing but can overide in subclasses
	protected void rebalanceInsert(BSTNode<K> n){}
	protected void rebalanceDelete(BSTNode<K> n){}

	// dont need a refrence to the tree in the node class
	protected static class BSTNode<K> {
		protected K data;
		protected BSTNode<K> left,right;
		protected BSTNode<K> parent; // used with the delete part
		
		public BSTNode(K d, BSTNode<K> p) {
			data = d;
			parent = p;
			left = right = null;
		}
		
	}

	
}
