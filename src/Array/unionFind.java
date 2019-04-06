package Array;

import java.util.Scanner;

public class unionFind {
	//并查集的实现
	//应用一：找出犯罪团伙的数目
	/**
	 * 第一行输入n和m，n表示强盗个数，m表示有m条线索，接下来有m行，每一行包含两个数字，比如1 2表示1和2是同伙，输出团伙的个数
	 *  10 9
		1 2
		3 4
		5 2
		4 6
		2 6
		8 7
		9 7
		1 6
		2 4
		输出：3
	 */
	static class unionSet{
		public int[] parent;
		public unionSet(int size){
			parent = new int[size];
			for(int i=0;i<size;i++)
				parent[i] = i;
		}
		public int find(int x) {
			while(x != parent[x])
				x = parent[x];
			return x;
		}
		public void union(int x, int y) {
			int p1 = find(x);
			int p2 = find(y);
			if(p1 != p2)
				parent[p1] = p2;
		}
		public boolean isSameUnion(int x, int y) {
			return find(x) == find(y);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
//		int m = sc.nextInt();
//		unionSet set = new unionSet(n);
//		sc.nextLine();
//		for(int i=0;i<m;i++) {
//			int a = sc.nextInt();
//			int b = sc.nextInt();
//			set.union(a, b);
//		}
//		//所有的团伙关系union之后，开始统计团伙的个数，怎么统计呢？找出队长个数即可！因为一个团伙只能有一个队长
//		int res = 0;
//		for(int i=0;i<n;i++) {
//			if(set.parent[i] == i)
//				res += 1;
//		}
//		System.out.println(res);
		/**
		 * 并查集应用二：找出传染人数
		 * 已知的是0号同学已经被传染了，他参加了一些社团，给出一些社团及里面的人，问一共可以传染多少人，在一个社团里就会被传染
		 * 输入：第一行两个数n和m,n表示人数，标号0至n-1，m表示社团数目
		 * 从第二行到第m+1行，每行第一个数字k表示该社团有多少人，然后是k个人的编号
		 * 输出感染的人的编号
		 * 
		 */
		int n = sc.nextInt();
		int m = sc.nextInt();
		unionSet set1 = new unionSet(n);
		sc.nextLine();
		for(int i=0;i<m;i++) {
			int k = sc.nextInt();
			//选第一个数字当root,还剩k-1个数字
			int root = sc.nextInt();
			for(int j=0;j<k-1;j++) {
				int val = sc.nextInt();
				set1.union(root, val);
			}
		}
		//待输入完毕，开始统计那些人被感染,怎么统计呢，就是看和0在一起的有哪些人
		for(int i=0;i<n;i++) {
			if(set1.isSameUnion(0, i))
				System.out.print(i+" ");
		}
	}
	
	
	
	
	
	
}
