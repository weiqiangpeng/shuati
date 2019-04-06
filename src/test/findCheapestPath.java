package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class findCheapestPath {
	//输入的是航班信息，是一个二维数组，例如  [ [1,2,100],[1,3,200]. [2,3,300]]这样的数据，表示三条航线，1-2,100元，1-3,200元，2-3,300元
	//再输入一个正整数k，表示最多准转乘多少次
	//方法一：使用动态规划
	public static int findPathI(int n, int[][] flights, int src, int end, int k) {
		/**
		 * n:有n个城市
		 * flights:二维数组，表示航线信息
		 * src:起点
		 * end:终点
		 * k:最多转乘次数
		 * return:最小的代价
		 */
		
		//定义代价矩阵,为每一次src到各个城市的最小代价
		int[][] cost = new int[k+1][n];
		//初始代价设置为无穷大
		for(int[] B:cost) {
			Arrays.fill(B, Integer.MAX_VALUE);
			//但是src自己到自己的代价还是0
			B[src] = 0;
		}
		
		//开始自下向上填表
		for(int j=1;j<k+1;j++) {
			for(int i=0;i<flights.length;i++) {
				//cost[j-1][flights[i][0]] != Integer.MAX_VALUE 说明了src到flights[i][0]是可达的，进一步，到flights[i][1]也是可达的
				if(cost[j-1][flights[i][0]] < Integer.MAX_VALUE) {
					if(cost[j-1][flights[i][0]] + flights[i][2] < cost[j][flights[i][1]]) {
						cost[j][flights[i][1]] = cost[j-1][flights[i][0]] + flights[i][2];
					}
				}
			}
		}
		
//		for(int[] A: cost) {
//			for(int v:A) {
//				System.out.print(v+" ");
//			}
//			System.out.println();
//		}
		return cost[k][end] == Integer.MAX_VALUE?-1:cost[k][end];
	}
	
	//方法2：BFS,类似于层次遍历二叉树
	public static int findPathII(int n, int[][] flights, int src, int end, int k) {
		int[][] graph = new int[n][n];
		for(int i=0;i<flights.length;i++) {
			graph[flights[i][0]][flights[i][1]] = flights[i][2];
		}
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		Set<Integer> set1 = new HashSet<>();
		set1.add(src);
		while(!set1.isEmpty() && k>0) {
			Set<Integer> set2 = new HashSet<>();
			for(int from :set1) {
				for(int i=0;i<n;i++) {
					if(graph[from][i] != 0) {
						if(graph[from][i] + cost[from] < cost[i]) {
							cost[i] = graph[from][i] + cost[from];
							set2.add(i);
						}
					}
				}
			}
			k--;
			set1 = set2;
		}
		return cost[end]==Integer.MAX_VALUE?-1:cost[end];
	}
	
	//DFS
	public static int findPathIII(int n, int[][] flights, int src, int end, int k) {
		int[][] graph = new int[n][n];
		for(int[] A:flights) {
			graph[A[0]][A[1]] = A[2];
		}
		boolean[] visited = new boolean[n];
		int[] cost = {Integer.MAX_VALUE};
		helper(graph, visited, cost, src, end, k, 0);
//		System.out.println(cost[0]);
		return cost[0]==Integer.MAX_VALUE?-1:cost[0];
	}
	
	public static void helper(int[][] graph, boolean[] visited, int[] cost, int src, int end, int k, int sum) {
		if(src == end) {
			cost[0] = Math.min(cost[0], sum);
			return;
		}
		if(k>0 && sum < cost[0]) {
			for(int i=0;i<graph.length;i++) {
				if(graph[src][i] != 0 && !visited[i]) {
					visited[i] = true;
					helper(graph, visited, cost, i, end, k-1, sum+graph[src][i]);
					visited[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] flights = {{0,1,100}, {0,2,500}, {1,2,100}};
		System.out.println(findPathI(3, flights, 0, 2, 2));
		System.out.println("----------------------------");
		System.out.println(findPathII(3, flights, 0, 2, 2));
		System.out.println("----------------------------");
		System.out.println(findPathIII(3, flights, 0, 2, 2));
	}
}
