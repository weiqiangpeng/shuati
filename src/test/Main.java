package test;

import java.util.Scanner;
import java.util.ArrayList;
public class Main{
    public static void helper(int[] arr,ArrayList<Integer> path, int[] cost, int pos,int sum,int target){
        if(sum == target){
            if(cost[0] > path.size()){
                cost[0] = path.size();
                return;
            }
            return;
        }
        if(sum>target)
        	return;
        for(int i=pos;i<arr.length;i++){
            path.add(arr[i]);
            helper(arr,path,cost,i,sum+arr[i],target);
            path.remove(path.size()-1);
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = {6,8};
        int[] mincount = {Integer.MAX_VALUE};
        ArrayList<Integer> path = new ArrayList<>();
        helper(arr,path,mincount,0,0,n);
        System.out.println(mincount[0]==Integer.MAX_VALUE?-1:mincount[0]);
    }
}