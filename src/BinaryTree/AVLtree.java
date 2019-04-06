package BinaryTree;

public class AVLtree {

	private Node root;

	//定义数据结构
	private static class Node {
		private int key;
		private int balance;
		private int height;
		private Node left;
		private Node right;
		private Node parent;

		Node(int key, Node parent) {
			this.key = key;
			this.parent = parent;
		}
	}

	public boolean insert(int key) {
		if (root == null) {
			root = new Node(key, null);
			return true;
		}

		Node n = root;
		while (true) {
			if (n.key == key)
				return false;

			Node parent = n;

			boolean goLeft = n.key > key;
			n = goLeft ? n.left : n.right;

			if (n == null) {
				if (goLeft) {
					parent.left = new Node(key, parent);
				} else {
					parent.right = new Node(key, parent);
				}
				rebalance(parent);
				break;
			}
		}
		return true;
	}

	//按照节点来删除
	private void delete(Node node) {
		//如果要删除的node节点左右孩子节点均为空
		if (node.left == null && node.right == null) {
			//先判断其是不是根节点，如果是根节点，直接将根节点置为空即可。
			if (node.parent == null) {
				root = null;
			} else {
				//否则，说明node是最下层的叶子节点，找出其父节点，再看node是父节点的左孩子还是右孩子，将其置为空即可。
				Node parent = node.parent;
				if (parent.left == node) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				//删除完，还要重新平衡下树，从父节点开始平衡
				rebalance(parent);
			}
			return;
		}

		//如果node的左孩子不为空，说明node不是叶子节点，如果node有左孩子，那么删除node之前，应该找到
		//node左子树的最大值，也就是node左孩子的最右侧节点
		if (node.left != null) {
			Node child = node.left;
			while (child.right != null)
				child = child.right;
			//找到node左孩子的最右侧节点child，将其值赋给node，再删除掉找到的child，这样就等于是删除掉node了。
			node.key = child.key;
			delete(child);
		} else {
			//否则，node右孩子不为空，则找出右子树最小的节点（也就是node右孩子最左侧的节点）child。
			Node child = node.right;
			while (child.left != null)
				child = child.left;
			//将child的key值赋给node，再删除child，就等于删除掉了node.
			node.key = child.key;
			delete(child);
		}
	}

	//按照key值来删除
	public void delete(int delKey) {
		//如果root为空的话，直接返回即可
		if (root == null)
			return;
		//否则，找到delkey所在的节点node，调用删除节点的函数delete(node)将node节点删除即可。
		Node child = root;
		while (child != null) {
			Node node = child;
			child = delKey >= node.key ? node.right : node.left;
			if (delKey == node.key) {
				delete(node);
				return;
			}
		}
	}

	//使树平衡的函数
	private void rebalance(Node n) {
		//首先给n节点设置平衡因子
		setBalance(n);

		//如果n的平衡因子是-2的话，说明n的左子树节点高度比右子树节点高度高2（平衡因子是右减去左），那么大体上是要向右转的
		//这也是确定了插入节点在n的左子树上，但到底是在n左子女的左子树上还是左子女的右子树上？还需要判断，因为这两种情况
		//对应不同的处理策略：1：新插入节点在n左子女的左子树上，进行R旋转。2：新插入节点在n左子女的右子树上，进行LR旋转
		if (n.balance == -2) {
			//左子女的左子树高度大于左子女的右子树高度，说明：新插入的节点是在左子女的左子树上，进行R旋转即可。
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else   
				//否则说明新插入节点是在左子女的右子树上，需要进行LR旋转
				n = rotateLeftThenRight(n);

			//如果n的平衡因子是2，说明右子树的高度比左子树高度高2，那么大体上可以确定是要向左转，
			//但也只能确定新插入节点是在n的右子女上，但到底是在n右子女的左子树上还是n右子女的右子树上，还需要判断，
			//1：新插入节点是在n右子女的左子树上，需要进行RL旋转。2：新插入节点在n右子女的右子树上，需要L旋转。
		} else if (n.balance == 2) {
			//如果n的右子女的右子树高度大于n右子女的左子树的高度，说明新插入节点在n右子女的右子树上，否则说明是插入到n右子女的左子树上。
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}

		//如果调整完n之后，还要判断n是否存在父节点，如果存在，则接着调整n的父节点，否则直接令n为根节点
		if (n.parent != null) {
			rebalance(n.parent);
		} else {
			root = n;
		}
	}

	//重点来了：左旋转：适用于在n的右子女的右子树上插入新的节点引起的失衡的时候。
	private Node rotateLeft(Node a) {
        //下面这个例子：在5的右子女6的右子树插入新节点9，进行左旋转
		//\（a-parent）                          \
		// 5(a)                  6
		//  \                   / \
		//   6（b）   ---L旋转-->            5   9  
		//  / \                 \
		//     9
		//先把a的右孩子保存在b里
		Node b = a.right;
		//将b的父节点设置为a的父节点
		b.parent = a.parent;
		//再将a的右孩子设置为b的左孩子
		a.right = b.left;

		//上一步设置的a的右孩子可能为空，当不为空的时候，还要将其a设置为a的右孩子的父节点，先设子女，再让子女认parent,
		if (a.right != null)
			a.right.parent = a;

		//接着：再理一理ab之间的关系，我们将b的左节点设置为a，再将a的父节点设置为b。
		b.left = a;
		a.parent = b;

		//第一步我们将a的父节点赋值给b的父节点，并未判断最开始a的父节点是否为空
		//如果不为空，那么该节点的孩子节点之一还是指向的a，我们现在需要将其指向b
		//先判断此时的a是该节点的左孩子还是右孩子，找到后用b替换点a即可。
		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}

		//旋转完之后，ab节点的平衡因子需要重新计算设置
		setBalance(a, b);
		//返回参数传进来a的替代节点：b
		return b;
	}

	//右旋转：当在节点a左子女的左孩子b上插入新的节点之后，需要调整
    //                    /(y可能null)                      /(y)
	//               (a) 9                                6
	//                  /      ---R旋转--->                                                /     \
	//             (b) 6                                4   9
	//                / \(x可能null)                        /(x)
	//               4
	//
	private Node rotateRight(Node a) {

		//用b保存a的左子树
		Node b = a.left;
		//将b的父节点设置为a的父节点
		b.parent = a.parent;

		//将a的左子树设置为b的右子树
		a.left = b.right;

		//如果a的左子树设置完成后发现其不为空，还要将其左子树的父节点设为a
		if (a.left != null)
			a.left.parent = a;

        //理清楚a与b之间的关系
		b.right = a;
		a.parent = b;

		//最开始的时候我们将b的父节点赋值为a的父节点，还未判断其是否为空，如果为空，不用管
		//如果不为空，还要判断此时a是b的父节点的做孩子还是右孩子，将其替换为b。因为其父节点的孩子指针还未变过来
		if (b.parent != null) {
			if (b.parent.right == a) {
				b.parent.right = b;
			} else {
				b.parent.left = b;
			}
		}
		//重新设置ab的平衡因子
		setBalance(a, b);

		return b;
	}

	//先左旋转再右旋转：适用于：在n(10)的左子女(6)的右孩子处插入(8)。
	//                 10(n)                     10(n)                            8                          
	//                /  \                       / \                             / \ 
	//               6          --先L旋转-->                   8       --再R旋转-->                                     6   10(n)
	//              / \                         /                              /     \
	//                 8                       6
	//                                        /
	//                                       
	private Node rotateLeftThenRight(Node n) {
		//代码也是分为2步，第一步：对n的左子女进行左旋转，返回结果作为n新的左子女
		//第二步：对n进行右旋转，返回旋转后替代n的节点。
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	//先右旋转再左旋转，适用情况：在n(10)的右子女(17)的左子树上插入节点14。
	//          10(n)                   10(n)                         14   
	//          / \                     / \                           /\
	//             17    --先R旋转-->                       14     --再L旋转-->                         10 17 
	//            /                         \                       /
	//           14                         17
	//
	//
	//
	private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	private int height(Node n) {
		if (n == null)
			return -1;
		return n.height;
	}

	//设置平衡因子。参数为多参数
	private void setBalance(Node... nodes) {
		for (Node n : nodes) {
			//对每一个node，算出来其左右孩子的高度，平衡因子等于右子树的高度减去左子树的高度
			//reheight的作用是：将高度重新更新，可能一开始所有节点都没有给height赋值。
			reheight(n);
			//然后使用更新后的高度计算平衡因子，赋给节点的balance属性
			n.balance = height(n.right) - height(n.left);
		}
	}

	public void printBalance() {
		printBalance(root);
	}

	private void printBalance(Node n) {
		if (n != null) {
			printBalance(n.left);
			System.out.printf("%s ", n.balance);
			printBalance(n.right);
		}
	}

	//计算树节点node的高度，也就是把重新计算的高度赋给节点的height属性
	private void reheight(Node node) {
		if (node != null) {
			node.height = 1 + Math.max(height(node.left), height(node.right));
		}
	}

	public static void main(String[] args) {
		AVLtree tree = new AVLtree();

		System.out.println("Inserting values 1 to 10");
		for (int i = 1; i < 10; i++)
			tree.insert(i);

		System.out.print("Printing balance: ");
		tree.printBalance();
	}
}