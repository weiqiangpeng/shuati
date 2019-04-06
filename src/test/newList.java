package test;

import java.util.ArrayList;
import java.util.Scanner;

public class newList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] oper = new int[m];
		sc.nextLine();
		for(int i=0;i<m;i++) {
			oper[i] = sc.nextInt();
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		for(int i=1;i<=n;i++) {
			res.add(i);
		}
		for(int i=0;i<oper.length;i++) {
			for(int j=0;i<res.size();j++) {
				if(oper[i] == res.get(j)) {
					int tmp = res.get(j);
					res.remove(j);
					res.add(0, tmp);
					break;
				}
			}
		}
		for(int v:res)
			System.out.print(v+" ");
	}
}
