package BinaryTree;

import java.util.Stack;

class BTreeNode{
	char val;
	BTreeNode left=null;
	BTreeNode right=null;
	BTreeNode(char ch){
		this.val = ch;
	}
}



public class FindCommonAncestor {
	
	//先给一个中序遍历
	public static void InOrder(BTreeNode root) {
		if(root == null)
			return;
		Stack<BTreeNode> st = new Stack<>();
		while(!st.isEmpty() || root!=null) {
			while(root!=null) {
				st.push(root);
				root = root.left;
			}
			root = st.pop();
			System.out.print(root.val+" ");
			root = root.right;
		}
	}
	
	public static BTreeNode findCommonAncestor(BTreeNode root, BTreeNode p1, BTreeNode p2) {
		if(root==null || root==p1 || root==p2)
			return root;
		BTreeNode left = findCommonAncestor(root.left, p1, p2);
		BTreeNode right = findCommonAncestor(root.right,p1, p2);
		if(left != null && right != null)
			return root;
		if(left != null)
			return left;
		if(right != null)
			return right;
		return null;
	}
	
	//给定一个二叉树，返回两个节点的最低公共祖先节点,二叉树定义如上所示
	public static void main(String[] args) {
		//先建立一棵树
		BTreeNode root = new BTreeNode('a');
		BTreeNode b = new BTreeNode('b');
		BTreeNode c = new BTreeNode('c');
		BTreeNode d = new BTreeNode('d');
		BTreeNode e = new BTreeNode('e');
		BTreeNode f = new BTreeNode('f');
		BTreeNode g = new BTreeNode('g');
		BTreeNode h = new BTreeNode('h');
		BTreeNode i = new BTreeNode('i');
		root.left = b;
		root.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		d.left = h;
		d.right = i;
//		InOrder(root);
		BTreeNode res = findCommonAncestor(root, g, e);
		System.out.println();
		System.out.println(res.val);
		
	}
}
