package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Permutation {
	public static void main(String[] args) {
		System.out.println("全排列结果为：");
		ArrayList res = permutation("aabc");
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}

	// 输出给定字符串的全排列,例如“abc”，输出abc,acb,bac,bca,cab,cba试试
	public static ArrayList<String> permutation(String str) {
		TreeSet<String> res = new TreeSet<>();
		ArrayList<String> s = new ArrayList<>();
		if (str.length() == 0)
			return s;
		char[] A = str.toCharArray();
		f(res, A, 0);
		s.addAll(res);
		return s;
	}

	public static void f(TreeSet<String> res, char[] A, int i) {
		if (i == A.length - 1) {
			// System.out.println(new String(A));
			res.add(new String(A));
			return;
		}
		for (int j = i; j < A.length; j++) {
			swap(A, i, j);
			// 相当于将A[i]及其之前的固定住，递归求剩下的子序列全排列问题
			f(res, A, i + 1);
			swap(A, i, j);
		}
	}

	public static void swap(char[] A, int i, int j) {
		if (i != j) {
			char tmp = A[i];
			A[i] = A[j];
			A[j] = tmp;
		}
	}

	
}
