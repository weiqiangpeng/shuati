package Array;
import java.util.*;
//给定一个整数数组和一个数字，求数组中元素组合之和等于该数字的组合，数组中的数字可以重复使用
public class CombinationSum {
	public static void main(String[] args) {
		int [] a = {1,1, 4,2,3};
		ArrayList<ArrayList<Integer>> res = FindPath(a, 5);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
		System.out.println("---------------------------");
		ArrayList<ArrayList<Integer>> res1 = FindPathII(a, 5);
		for (int i = 0; i < res1.size(); i++) {
			System.out.println(res1.get(i));
		}
	}
	//给定一个整数数组和一个数字，求数组中元素组合之和等于该数字的组合，数组中的数字可以重复使用
	public static ArrayList<ArrayList<Integer>> FindPath(int[] A, int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<>();
		if (A.length == 0)
			return res;
		f(A, 0, 0, target, res, path);
		return res;
	}
	
	public static void f(int[] A, int pos, int sum, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path){
		if(sum == target){
			res.add(new ArrayList<>(path));
			return;
		}
		if(sum > target)
			return;
		for(int i=pos;i<A.length;i++){
			path.add(A[i]);
			f(A,i,sum+A[i],target,res,path);
			//回溯
			path.remove(path.size()-1);
		}
	}
	
	//给定一个整数数组和一个数字，求数组中元素组合之和等于该数字的组合，数组中的数字不可以重复使用
	public static ArrayList<ArrayList<Integer>> FindPathII(int[] A, int target){
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		if(A.length == 0)
			return res;
		f1(A, target, 0, 0, res, path);
		return res;
	}
	
	public static void f1(int[] A, int target, int pos, int sum, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path) {
		if(sum == target) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		if(sum > target)
			return;
		for(int i=pos;i<A.length;i++) {
			path.add(A[i]);
			f1(A, target, i+1,sum+A[i], res, path);
			path.remove(path.size()-1);
		}
	}
}
