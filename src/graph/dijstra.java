package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class dijstra {
	
	 
	
	public static void main(String[] args) {
		//优先队列，默认是小根堆
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		pq.add(3);
		pq.add(9);
		pq.add(6);
		pq.add(1);
		while(!pq.isEmpty())
			System.out.print(pq.poll()+" ");
	}
}
