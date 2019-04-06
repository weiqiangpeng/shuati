package test;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
	int val;
	TreeNode left=null;
	TreeNode right=null;
	TreeNode(int val){
		this.val = val;
	}
}
public class TreeTest {
	//建立二叉树,根据前序遍历结果反序列化
	static int index = -1;
	public static TreeNode deserailize(String preorder) {
		index++;
		String[] arr = preorder.split(",");
		while(!arr[index].equals("#")) {
			TreeNode head = new TreeNode(Integer.valueOf(arr[index]));
			head.left = deserailize(preorder);
			head.right = deserailize(preorder);
			return head;
		}
		return null;
	}
	
	//先序遍历
	public static void preOrder(TreeNode head) {
		if(head == null)
			return;
		System.out.print(head.val+" ");
		preOrder(head.left);
		preOrder(head.right);
	}
	
	//中序遍历
	public static void inOrder(TreeNode head) {
		if(head == null)
			return;
		inOrder(head.left);
		System.out.print(head.val+" ");
		inOrder(head.right);
	}
	
	//后序遍历
	public static void postOrder(TreeNode head) {
		if(head == null)
			return;
		postOrder(head.left);
		postOrder(head.right);
		System.out.print(head.val+" ");
	}
	
	//层次遍历
	public static void cengciOrder(TreeNode head) {
		if(head == null)
			return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(head);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.print(node.val+" ");
			if(node.left != null)
				queue.offer(node.left);
			if(node.right != null)
				queue.offer(node.right);
		}
	}
	
	public static void main(String[] args) {
		String s = "7,6,9,#,#,2,#,8,#,#,5,#,7,20,#,#,#,";
		TreeNode head = deserailize(s);
		preOrder(head);
		System.out.println();
		inOrder(head);
		System.out.println();
		postOrder(head);
		System.out.println();
		cengciOrder(head);
		System.out.println();
	}
	
	
	
	
	
}
