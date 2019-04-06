package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class AboutBTree {
	//二叉树的一些算法
	//二叉树的层次遍历：递归写法
	
	private static int index = -1;
	//二叉树的序列化与反序列化
	public static String SeriaLize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if(root == null) {
			sb.append("#,");
			return sb.toString();
		}
		sb.append(root.val+",");
		sb.append(SeriaLize(root.left));
		sb.append(SeriaLize(root.right));
		return sb.toString();
	}
	
	//根据前序遍历的序列化字符串进行反序列化
	public static TreeNode DeSeriaLize(String preStr) {
		index++;
		String[] arr = preStr.split(",");
		if(!arr[index].equals("#")){
			TreeNode node = new TreeNode(Integer.valueOf(arr[index]));
			node.left = DeSeriaLize(preStr);
			node.right = DeSeriaLize(preStr);
			return node;
		}
		return null;
	}
	
	//求二叉树的最小深度,方法一：递归求解
	public static int getMinDepthI(TreeNode root) {
		if(root == null)
			return 0;
		return getMinDepthHelper(root);
	}
	public static int getMinDepthHelper(TreeNode root) {
		if(root == null)
			return Integer.MAX_VALUE;
		if(root.left == null && root.right == null)
			return 1;
		return 1+Math.min(getMinDepthHelper(root.left), getMinDepthHelper(root.right));
	}
	
	//求二叉树的最小深度，非递归方式求解,使用层次遍历的方式
	public static int getMinDepthII(TreeNode root) {
		if(root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 1;
		while(!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				TreeNode node = queue.poll();
				if(node.left == null && node.right == null)
					return level;
				if(node.left!=null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
			level++;
		}
		return level;
	}
	
	//求二叉树的最大深度，使用递归
	public static int getMaxDepthI(TreeNode root) {
		if(root == null)
			return 0;
		int l = getMaxDepthI(root.left);
		int r = getMaxDepthI(root.right);
		return 1+Math.max(l, r);
	}
	
	//求二叉树的最大深度，使用非递归,使用队列？？？因为队列可以层次访问
	public static int getMaxDepthII(TreeNode root) {
		if(root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		int level = 0;
		queue.offer(root);
		while(!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				TreeNode node = queue.poll();
				if(node.left != null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
			level++;
		}
		return level;
	}
	
	//求二叉树的所有节点个数,使用递归方式
	public static int getAllNodeNumber(TreeNode root) {
		if(root == null)
			return 0;
		int l = getAllNodeNumber(root.left);
		int r = getAllNodeNumber(root.right);
		return 1+l+r;
	}
	
	//求二叉树所有的叶子节点个数,递归方法，还可以用非递归方法，在遍历的时候判断是否是叶子节点
	public static int getAllLeafNumber(TreeNode root) {
		if(root == null)
			return 0;
		if(root.left == null && root.right == null)
			return 1;
		return getAllLeafNumber(root.left) + getAllLeafNumber(root.right);
	}
	
	public static void main(String[] args) {
		int[] pre = {3,2,1,5,4,7,9};
		int[] in = {1,2,3,4,5,7,9};
		TreeNode node = RecontrustBTree.recontrust(pre, in);
		RecontrustBTree.PreOrder(node);
		System.out.println();
		RecontrustBTree.InOrder(node);
		System.out.println();
		System.out.println(RecontrustBTree.CengciOrderByDiGui(node));
		System.out.println("The result of SeriaLize is:");
		String  s = SeriaLize(node);
		System.out.println(s);
		System.out.println("The result of DeSeriaLize is:");
		TreeNode root = DeSeriaLize(s);
		System.out.println("In order:");
		RecontrustBTree.InOrder(root);
		System.out.println();
		System.out.println("pre order:");
		RecontrustBTree.PreOrder(root);
		System.out.println();
		System.out.println("CengCi order:");
		RecontrustBTree.CengciOrder(root);
		System.out.println();
		System.out.println("The minimum of the tree is(非递归):"+ getMinDepthI(root));
		System.out.println("The minimum of the tree is(递归):"+ getMinDepthII(root));
		System.out.println("The maximum of the tree is(递归):"+ getMaxDepthI(root));
		System.out.println("The maximum of the tree is(非递归):"+ getMaxDepthII(root));
		System.out.println("The totla number of the tree's node is:"+getAllNodeNumber(root));
		System.out.println("The total number of the tree's leaf node is:"+getAllLeafNumber(root));
	}
}
