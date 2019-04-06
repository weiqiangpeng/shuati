package ShuaTi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheapestPrice {
	//问题：n个城市之间有m条航线，每条航线从城市u出发到达城市v，票价为w。给你若干城市的航线信息，一个出发城市src和一个目标城市dst，
	//请问从城市src飞到城市dst最多转机K次的最便宜的价格是多少？如果不存在从城市src飞到城市dst的线路，返回-1。
	
	//方法一：使用深度优先
	public static int findCheapestPriceI(int n, int[][] flights, int src, int end, int k) {
		/**
		 * 各个参数如下：
		 * n:一共n个城市
		 * flights:是一个二维数组，每一个元素表示一条航线信息，比如：{{1,2,100}，{1,3,400}}里面有2条航线信息：
		 *         分别是：1到2，花费100,1到3，花费400。
		 * src:起始地
		 * end:目的地
		 * k：最多可以转机的次数
		 * return:最小的花费
		 */
		
		//将输入的航航线转为我们熟悉的图表示，使用二维数组
		int[][] graph = new int[n][n];
		for(int[] flight:flights) {
			graph[flight[0]][flight[1]] = flight[2];
		}
		//初始化一个最大的代价
		int[] minCost = {Integer.MAX_VALUE};
		boolean[] visited = new boolean[n];
		helper(graph, src, end, k, 0, minCost, visited);
		return minCost[0] == Integer.MAX_VALUE?-1:minCost[0];
	}
	
	public static void helper(int[][] graph, int src, int end, int k, int cost, int[] minCost, boolean[] visited) {
		if(src == end) {
			minCost[0] = Math.min(minCost[0], cost);
			return;
		}
		if(k>=0 && cost<minCost[0]) {
			for(int i=0;i<graph.length;i++) {
				if(graph[src][i] != 0 && !visited[i]) {
					visited[i] = true;
					helper(graph,i,end,k-1,cost+graph[src][i], minCost, visited);
					visited[i] = false;
				}
			}
		}
	}
	
	//方法2：使用广度优先
	public static int findCheapestPriceII(int n, int[][] flights, int src, int end, int k) {
		int[][] graph = new int[n][n];
		for(int[] flight:flights)
			graph[flight[0]][flight[1]] = flight[2];
		//costs[i]表示从初始位置到达位置i的最小代价
		int[] costs = new int[n];
		Arrays.fill(costs, Integer.MAX_VALUE);
		costs[src] = 0;                //src到自己肯定是没有代价的
		//有点类似与二叉树的层次遍历，set表示每一层所能达到的点。也就是在graph中元素不为0的点
		Set<Integer> set1 = new HashSet<>();
		set1.add(src);
		//k是控制发散的层数
		while(!set1.isEmpty() && k>0) {
			//set2用来存放下一层可以到达的点，因为不同的from可能到达同一个点，所以为了去重，使用set这一数据结构
			Set<Integer> set2 = new HashSet<>();
			for(int from:set1) {
				//对于每一层出发的点，遍历所有点，找下一层可能到达的点,并更新到达的点的最小代价
				for (int i = 0; i < n; i++) {
					//从from出发，可以到达i的条件是graph[from][i]!=0
					if(graph[from][i] != 0 && costs[from] + graph[from][i] < costs[i]) {
						//如果从当前from出发，加上graph[from][i]比costs[i]小的话，就用此值更新costs[i],并把i这个下一层可以到达的点加进去
						costs[i] = costs[from] + graph[from][i];
						set2.add(i);
					}
				}
			}
			
			//这里输出每一层的costs看看
//			for (int i = 0; i < costs.length; i++) {
//				System.out.print(costs[i] + " ");
//			}
//			System.out.println();
			//使用set2来更新当前层的节点，并使k减少一个。
			set1 = set2;
			k-=1;
		}
		return costs[end] == Integer.MAX_VALUE?-1:costs[end];
	}
	
	
	//方法3：动态规划
	public static int findCheapestPriceIII(int n, int[][] flights, int src, int end, int k) {
		int[][] dp = new int[2][n];
	    for (int[] row : dp) {
	        Arrays.fill(row, Integer.MAX_VALUE);
	    }
	    
	    dp[0][src] = 0;
	    //当前行
	    int cur = 1;
	    while (k >= 0) {
	    	//当前行要根据上一行的数据进行计算
	        int prev = 1 - cur;
	        for (int[] flight : flights) {
	        	//遍历所有航线，发现上一行有到当钱航线的起始点的时候，说明上一行可以到达当前航线的终点
	            if (dp[prev][flight[0]] < Integer.MAX_VALUE) {
	            	//上一行到当前航线的终点的代价，等于。。。。。
	                dp[cur][flight[1]] = Math.min(dp[cur][flight[1]],
	                                              dp[prev][flight[0]] + flight[2]);
	            }
	        }
	        //进行下一次迭代的时候，把当前行赋给上一行，
	        dp[prev] = Arrays.copyOf(dp[cur], n);
	        cur = prev;
	        k--;
	    }
	    
	    return dp[1 - cur][end] == Integer.MAX_VALUE ? -1 : dp[1 - cur][end];
	}

	public static int myFindPath(int n, int[][] flights, int src, int end, int k) {
		//cost分别保存每一次迭代的src去各个地方的代价
		int[][] costs = new int[k+1][n];
		for (int i = 0; i < costs.length; i++) {
			Arrays.fill(costs[i], Integer.MAX_VALUE);
		}
		//无论是哪一次迭代的时候，src到src的代价肯定是0
		for (int i = 0; i < costs.length; i++) {
			costs[i][src] = 0;
		}
		for (int i = 1; i <= k; i++) {
			//进行k次迭代
			for (int j = 0; j < flights.length; j++) {
				//costs[i-1][flights[j][0]]:表示第i-1次迭代的时候，src到flights[j][0]这个地方的代价，如果不是Max值，说明是可达的，
				//也说明src可以到达flights[j][1]的
				if(costs[i-1][flights[j][0]] < Integer.MAX_VALUE) {
					costs[i][flights[j][1]] = Math.min(costs[i][flights[j][1]], costs[i-1][flights[j][0]] + flights[j][2]);
				}
			}
		}
		
		for(int[] A: costs) {
			for(int v:A) {
				System.out.print(v+" ");
			}
			System.out.println();
		}
		
		return costs[k][end] == Integer.MAX_VALUE?-1:costs[k][end];
	}
	
	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int [][] dp = new int[K+1][n];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<dp.length;i++)
            dp[i][src] = 0;
        
        for(int i=1;i<=K;i++){
            for(int j=0;j<flights.length;j++){
                if(dp[i-1][flights[j][0]] < Integer.MAX_VALUE){
                    dp[i][flights[j][1]] = Math.min(dp[i][flights[j][1]], dp[i-1][flights[j][0]]+flights[j][2]);
                }
            }
        }
        return dp[K][dst] == Integer.MAX_VALUE?-1:dp[K][dst];
    }
	
	
	public static void main(String[] args) {
		
		int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};
		int cost = findCheapestPriceI(3, flights, 0, 2, 2);
		System.out.println(cost);
		System.out.println("------------------------");
		int cost1 = findCheapestPriceII(3, flights, 0, 2, 2);
		System.out.println(cost1);
		System.out.println("------------------------");
		int cost2 = findCheapestPriceIII(3, flights, 0, 2, 2);
		System.out.println(cost2);
		System.out.println("------------------------");
		
		int cost3 = myFindPath(3, flights, 0, 2, 4);
		System.out.println(cost3);
		System.out.println("------------------------");
		int cost4 = findCheapestPrice(3, flights, 0, 2, 1);
		System.out.println(cost4);
	}
}
