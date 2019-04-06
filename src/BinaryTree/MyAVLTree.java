package BinaryTree;
//自己尝试写一个AVLTree的数据结构

public class MyAVLTree {
	public Node root;
	static class Node{
		int key;
		int height;
		int balance;
		Node left;
		Node right;
		Node parent;
		Node(int key, Node parent){
			this.key = key;
			this.parent = parent;
		}
	}
	
	//先写插入一个node
	public boolean insert(int key) {
		if(root == null) {
			root = new Node(key, null);
			return true;
		}
		Node n = root;
		while(true) {
			if(n.key == key)
				return false;
			Node parent = n;
			boolean goleft = n.key>key;
			n = goleft?n.left:n.right;
			if(n == null) {
				if(goleft) {
					parent.left = new Node(key, parent);
				}else {
					parent.right = new Node(key, parent);
				}
				reblance(parent);
				break;
			}
		}
		return true;
	}
	//再写删除一个节点的
	public void delete(Node node) {
		if(node.left == null && node.right == null) {
			if(node == root)
				root = null;
			else {
				Node parent = node.parent;
				if(parent.left == node)
					parent.left = null;
				else
					parent.right = null;
				reblance(parent);
			}
		}
		if(node.left != null) {
			Node child = node.left;
			while(child.right != null)
				child = child.right;
			node.key = child.key;
			delete(child);
		}
		if(node.right != null) {
			Node child = node.right;
			while(child.left != null)
				child = child.left;
			node.key = child.key;
			delete(child);
		}
	}
	
	public int height(Node n) {
		if(n == null)
			return -1;
		return n.height;
	}
	
	public void reheight(Node node) {
		if(node != null)
			node.height = 1+Math.max(height(node.left), height(node.right));
	}
	
	public void setblance(Node... n) {
		for(Node node:n) {
			reheight(node);
			node.balance = height(node.right) - height(node.left);
		}
	}
	//重新平衡的函数
	public void reblance(Node n) {
		setblance(n);
		if(n.balance == -2) {
			if(height(n.left.left)>height(n.left.right)) {
				n = rotateRight(n);
			}
			else
				n = rotateLeftThenRight(n);
		}
		else if(n.balance == 2) {
			if(height(n.right.right) > height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}
		if(n.parent != null)
			reblance(n.parent);
		else
			root = n;
	}
	
	public Node rotateLeft(Node a) {
		Node b = a.right;
		b.parent = a.parent;
		a.right = b.left;
		if(a.right!=null)
			a.right.parent = a;
		b.left = a;
		a.parent = b;
		if(b.parent != null) {
			if(b.parent.left == a)
				b.parent.left = b;
			else
				b.parent.right = b;
		}
		setblance(a,b);
		return b;
	}
	
	public Node rotateRight(Node a) {
		Node b = a.left;
		b.parent = a.parent;
		a.left = b.right;
		if(a.left != null)
			a.left.parent = a;
		
		b.right = a;
		a.parent = b;
		if(b.parent != null) {
			if(b.parent.left == a)
				b.parent.left = b;
			else
				b.parent.right = b;
		}
		setblance(a,b);
		return b;
	}
	public Node rotateLeftThenRight(Node a) {
		a.left = rotateLeft(a.left);
		return rotateRight(a); 
	}
	
	public Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}
	
	
}
