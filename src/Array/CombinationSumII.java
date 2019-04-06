package Array;

import java.util.ArrayList;

public class CombinationSumII {
	//给一个正整数target，求使得和等于该正整数的正整数序列
	public static ArrayList<ArrayList<Integer>> findPath(int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (target <= 0)
			return res;
		helper(1, 0, target, res, path);
		return res;
	}
	
	public static void helper(int pos, int sum, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path){
		if(sum > target)
			return;
		if (sum == target){
			res.add(new ArrayList<>(path));
			return;
		}
		for(int i=pos;i<target/2+1;i++){
			path.add(i);
			helper(i+1, sum+i, target, res, path);
			path.remove(path.size()-1);
			
		}
	}
	
	public static void main(String[] args) {
//		ArrayList<ArrayList<Integer>> res = findPath(11);
//		
//		for(ArrayList<Integer> a:res){
//			for(int i:a){
//				System.out.print(i+ " ");
//			}
//			System.out.println();
//		}
		System.out.println("--------------");
		String s = " ab  c d ";
		String[] r = s.split(" ");
		System.out.println(r.length);
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i]);
		}
	}
}
