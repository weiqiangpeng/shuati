package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RecontrustBTree {
	public static void main(String[] args) {
		String s = "abc";
		int[] pre = {1,2,4,5,3,6};
		int[] in = {4,2,5,1,6,3};
		TreeNode root = recontrust(pre, in);
		System.out.println("先序递归遍历结果为：");
		PreOrder(root);
		System.out.println();
		System.out.println("中序递归遍历结果为：");
		InOrder(root);
		System.out.println();
		System.out.println("后序递归遍历结果为：");
		PostOrder(root);
		System.out.println();
		System.out.println("先序非递归遍历结果为：");
		PreOrderII(root);
		System.out.println();
		System.out.println("中序非递归遍历结果为：");
		InOrderII(root);
		System.out.println();
		System.out.println("后序非递归遍历结果为：");
		PostOrderII(root);
		System.out.println();
		System.out.println("层次遍历结果为：");
		CengciOrder(root);
		//之字形打印
		System.out.println();
		System.out.println("之字形打印结果：");
		ArrayList<ArrayList<Integer>> res = Print(root);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("递归实现层次遍历:");
		ArrayList<ArrayList<Integer>> res1 = CengciOrderByDiGui(root);
		for (int i = 0; i < res1.size(); i++) {
			for (int j = 0; j < res1.get(i).size(); j++) {
				System.out.print(res1.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
		
	}
	public static TreeNode recontrust(int[] pre, int[] in){
		if(pre.length == 0 || in.length==0)
			return null;
		TreeNode root = new TreeNode(pre[0]);
		for (int i = 0; i < in.length; i++) {
			if(in[i] == pre[0]){
				root.left = recontrust(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i));
				root.right = recontrust(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
			}
		}
		return root;
	}
	
	public static void PreOrder(TreeNode root){
		//先序遍历递归版本
		if (root == null)
			return;
		System.out.print(root.val+" ");
		PreOrder(root.left);
		PreOrder(root.right);	
	}
	public static void InOrder(TreeNode root){
		//中序遍历递归版本
		if (root == null)
			return;
		InOrder(root.left);
		System.out.print(root.val + " ");
		InOrder(root.right);
	}
	
	public static void PostOrder(TreeNode root){
		//后序遍历递归版本
		if (root == null)
			return;
		PostOrder(root.left);
		PostOrder(root.right);
		System.out.print(root.val + " ");
	}
	
	public static void PreOrderII(TreeNode root){
		if (root == null)
			return;
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while(!st.empty()){
			TreeNode node = st.pop();
			System.out.print(node.val + " ");
			if (node.right != null)
				st.push(node.right);
			if(node.left != null)
				st.push(node.left);
		}
	}
	
	public static void InOrderII(TreeNode root){
		if (root == null)
			return;
		Stack<TreeNode> st = new Stack<>();
		while(root != null || !st.empty()){
			while(root!=null){
				st.push(root);
				root = root.left;
			}
			//此刻栈顶节点已经没有左子节点或者左子节点已经被访问过，此时可以出栈访问了
			TreeNode node = st.pop();
			System.out.print(node.val + " ");
			//然后指向其右节点
			root = node.right;
		}
	}
	
	public static void PostOrderII(TreeNode root){
		//后序遍历非递归算法1，采用两个辅助栈
		if (root == null)
			return;
		Stack<TreeNode> st1 = new Stack<>();
		Stack<TreeNode> st2 = new Stack<>();
		st1.push(root);
		while(!st1.empty()){
			TreeNode node = st1.pop();
			//进入st2的顺序为：先根，再右，再左，出st2的顺序就满足后序遍历
			st2.push(node);
			//进入栈st1的时候，先进左子树，后进右子树，那么出st1的时候左子树后出来，右子树先出来，
			//右子树先进st2,出st2的时候就是后出来，满足：先左后右，再根的顺序
			if(node.left != null)
				st1.push(node.left);
			if(node.right != null)
				st1.push(node.right);
		}
		while(!st2.empty()){
			System.out.print(st2.pop().val + " ");
		}
	}
	
	public static void PostOrderIII(TreeNode root){
		//后序遍历非递归算法，只用一个辅助栈,但是要设置标记，标记在访问一个节点的右子树的时候其左子树是否已经访问过
		
	}
	
	public static void CengciOrder(TreeNode root){
		if (root == null)
			return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode node = queue.poll();
			System.out.print(node.val + " ");
			if(node.left != null)
				queue.add(node.left);
			if(node.right != null)
				queue.add(node.right);
			
		}
		
	}
	
	public static ArrayList<ArrayList<Integer> > CengciOrderByDiGui(TreeNode pRoot) {
		//层次遍历,用递归实现
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		CengCiOrderHelper(pRoot, 1, res);
		return res;
	}
	public static void CengCiOrderHelper(TreeNode root, int depth, ArrayList<ArrayList<Integer>> res){
		if(root == null)
			return;
		if(depth > res.size())
			res.add(new ArrayList<Integer>());
		res.get(depth-1).add(root.val);
		CengCiOrderHelper(root.left, depth+1, res);
		CengCiOrderHelper(root.right, depth+1, res);
	}
	
	//之字形打印
	public static ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        if(pRoot == null)
            return res;
        int layer = 1;
        st1.push(pRoot);
        while(!st1.empty() || !st2.empty()){
            if(layer % 2 != 0){
                // 奇数层
                ArrayList<Integer> tmp = new ArrayList<>();
                while(!st1.empty()){
                    TreeNode node = st1.pop();
//                    System.out.println("----" + node.val);
                    if(node != null)
                        tmp.add(node.val);
                    if(node.left!=null)
                        st2.push(node.left);
                    if(node.right != null)
                        st2.push(node.right);
                }
                if(tmp.size()>0){
                    layer++;
                    res.add(tmp);
                }
            }else{
                //偶数层
                ArrayList<Integer> tmp = new ArrayList<>();
                while(!st2.empty()){
                    TreeNode node = st2.pop();
                    if(node != null)
                        tmp.add(node.val);
                    if(node.right != null)
                        st1.push(node.right);
                    if(node.left != null)
                        st1.push(node.left);
                }
                if(tmp.size()>0){
                    layer ++;
                    res.add(tmp);
                }
            }
        }
        return res;
    }
	
	
}
