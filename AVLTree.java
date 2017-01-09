
public class AVLTree<K extends Comparable<K>> extends BST<K> {

	private static class AVLNode<K> extends BSTNode<K>{
		private int height;
		public AVLNode(K d, BSTNode<K> p) {
			super(d, p);
			height = 0;
		}
		
	}
	
	@Override
	protected BSTNode<K> makeNode(K data,BSTNode<K> p){
		return new AVLNode<>(data,p);
	}
	
	public AVLTree(){
		super();
	}
	
	protected AVLNode<K> cast(BSTNode<K> n){return (AVLNode<K>) n;}
	
	protected void relink(BSTNode<K> p, BSTNode<K> c,boolean isLeft){
		if(c != null){
			c.parent = p;
		}
		if(isLeft){
			p.left = c;
		}else{
			p.right = c;
		}
	}
	
	protected void rotate(BSTNode<K> n){ // n is the child
		BSTNode<K> p = n.parent;
		BSTNode<K> gp = p.parent; // grand parent
		
		if(gp == null){ // p == root
			root = n;
			n.parent = null;
		}else{
			relink(gp,n,p == gp.left); 
		}
		
		if(n == p.left){
			relink(p,n.right,true);
			relink(n,p,false);
		}else{
			relink(p,n.left,false);
			relink(n,p,true);
		}
	}
	
	//single rotate from middle or double rotate thing
	protected BSTNode<K> restructure(BSTNode<K> n){
		BSTNode<K> p = n.parent;
		BSTNode<K> gp = p.parent; // grand parent CANNOT be null will only call if have hieight at least two
		
		boolean nIsLeft = n == p.left;
		boolean pIsLeft = p == gp.left;
		
		if(nIsLeft == pIsLeft){ // might have left left or right right situation
			// one rotation and rotate at the parent
			rotate(p);
			return p; // returning the new top
		}else{ // right-left or left-right
			// so two rotaions
			rotate(n);
			rotate(n);
			return n; // n is the new top
		}
	}
	
	protected void recomputeHeights(AVLNode<K> n){
		//the height of a node is one plus the max of its childrens height
		int lHeight = -1;
		if(n.left != null){
			lHeight = cast(n.left).height;
		}
		int rHeight = -1;
		if(n.right != null){
			rHeight = cast(n.right).height;
		}
		n.height = 1 + Math.max(lHeight, rHeight); 
	}
	
	protected boolean isBalanced(AVLNode<K> n){
		int lHeight = -1;
		if(n.left != null){
			lHeight = cast(n.left).height;
		}
		int rHeight = -1;
		if(n.right != null){
			rHeight = cast(n.right).height;
		}
		
		return Math.abs(lHeight - rHeight) <=1; 
	}
	
	protected AVLNode<K> tallestChild(AVLNode<K> n){
		
		if(n.left == null){ // what if both children are null
			return cast(n.right);
		}
		if(n.right == null){
			return cast(n.left);
		}
		if(cast(n.left).height > cast(n.right).height){
			return cast(n.left);
		}
		if(cast(n.left).height < cast(n.right).height){
			return cast(n.right);
		}
		
		if(n == root){
			return cast(n.left);
		}
		if(n == n.parent.left){
			return cast(n.left);
		}else{
			return cast(n.right);
		}
	}
	
	protected void rebalanceNode(AVLNode<K> n){
		
		//keep track of old hieght of n and the new hieght of n
		int oldHeight , newHeight;
		do{
			oldHeight = n.height;
			if(!isBalanced(n)){ // if n is not balanced-- then n has to be a grandparent
				// the tallest grandchild -- fix by modifying tallest grandchild
				n = cast(restructure(tallestChild(tallestChild(n)))); // the top changed so need to keep track of it
				recomputeHeights(cast(n.left));
				recomputeHeights(cast(n.right));
			}
			recomputeHeights(n);
			newHeight = n.height;
			// now have to move up the tree
			n = cast(n.parent);
			
		}while(oldHeight != newHeight && n != null);
		
	}
	
	@Override
	protected void rebalanceInsert(BSTNode<K> n){
		rebalanceNode(cast(n));
	}
	@Override
	protected void rebalanceDelete(BSTNode<K> n){
		rebalanceNode(cast(n));
	}
	
	@Override 
	public String toString(){
		return inOrderWithHeight(cast(root));
	}

	private String inOrderWithHeight(AVLNode<K> n) {
		if(n == null){
			return "";
		}
		return inOrderWithHeight(cast(n.left)) + "( " + n.data + " h: " + n.height + ") " + inOrderWithHeight(cast(n.right));
	}
	
}
