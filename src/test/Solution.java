package test;
import java.awt.List;
import java.util.ArrayList;

class Solution {
    public static ArrayList<ArrayList<Integer>> permute(int[] nums) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        helper(nums, res, 0);
        return res;
    }
    
    public static void helper(int[] nums, ArrayList<ArrayList<Integer>> res, int pos){
        if(pos == nums.length-1){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int val:nums)
                tmp.add(val);
            res.add(tmp);
            return;
        }
        for(int i=pos;i<nums.length;i++){
            swap(nums, i, pos);
            helper(nums, res, pos+1);
            swap(nums, i, pos);
        }
        
    }
    
    public static void swap(int[] A, int i, int j){
        if(i!= j){
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
    
    
    public static void main(String[] args) {
//		int[] A = {1, 2, 3};
//		int rand1 = (int)(Math.random()*A.length);
//		System.out.println(rand1);
//		ArrayList<ArrayList<Integer>> res = permute(A);
//		for(ArrayList<Integer> r:res) {
//			for(int val:r) {
//				System.out.print(val + " ");
//			}
//			System.out.println();
    	String s1 = "Iam";
    	String s2 = new String(s1);
    	System.out.println(s2);
		}
	}