
public class TreeTest {

	public static void main(String[] args) {
		
		BinaryTree<Integer> bt = new BinaryTree<>();
		//bt.addRoot(0);
		
		BinaryTree.Node<Integer> root = bt.addRoot(1);
		BinaryTree.Node<Integer> left1 = bt.addLeft(root, 2);
		BinaryTree.Node<Integer> right1 = bt.addRight(root, 3);
		bt.addLeft(left1, 4);
		bt.addRight(left1, 5);
		
		bt.addLeft(left1.getLeft(),7);
		bt.addRight(left1.getLeft(),8);
		bt.addRight(right1,9);
		
		bt.preorderTraversal(new BinaryTree.Visitor<Integer>(){
			
			@Override
			public void visit(BinaryTree.Node<Integer> n){
				System.out.println(n.getData());
			}
			
		});
		
		Stringify sf = new Stringify();
		
		bt.preorderTraversal(sf);
		System.out.println("preorder: " + sf);
		sf = new Stringify();
		bt.inOrderTraversal(sf);
		log("in order: " + sf);
		sf = new Stringify();
		bt.postOrderTraversal(sf);
		log("post order: " + sf);
		
		Addify af = new Addify();
		
		bt.preorderTraversal(af);
		System.out.println(af);
		af = new Addify();
		bt.inOrderTraversal(af);
		log("in order: sum: " + af);
		af = new Addify();
		bt.postOrderTraversal(af);
		log("post ordre sum: " + af);
		
		
	}
	public static void log(String s){
		System.out.println(s);
	}
	private static class Addify implements BinaryTree.Visitor<Integer>{

		private int sum;
		public Addify(){
			sum = 0;
		}
		@Override
		public void visit(BinaryTree.Node<Integer> n) {
			sum += n.getData();
		}
		
		public String toString(){
			return "sum: " + sum;
		}
		
	}
	
	private static class Stringify implements BinaryTree.Visitor<Integer>{

		private StringBuilder sb;
		
		public Stringify(){
			sb = new StringBuilder();
		}
		@Override
		public void visit(BinaryTree.Node<Integer> n) {
			sb.append(n.getData());
		}
		
		public String toString(){
			return sb.toString();
		}
	}

}
