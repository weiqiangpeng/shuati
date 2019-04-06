package Array;

import java.util.ArrayList;
import java.util.List;
public class paraCombination {
	public static List<List<String>> getParaSubString(String s){
		List<List<String>> res = new ArrayList<>();
		boolean[] cut = new boolean[s.length()];
		helper(s, res, 0, cut);
		return res;
	}
	
	public static void helper(String s, List<List<String>> res,int index, boolean[] cut) {
		if(index == s.length()) {
			ArrayList<String> tmp = new ArrayList<>();
			int start = 0;
			int end = 0;
			while(end<s.length()) {
				if(cut[end]) {
					tmp.add(s.substring(start, end+1));
					start = end+1;
				}
				end++;
			}
			res.add(tmp);
			return;
		}
		for(int i=index;i<s.length();i++) {
			if(judge(s, index, i)) {
				cut[i] = true;
				helper(s,res,i+1,cut);
				cut[i] = false;
			}
		}
	}
	
	public static boolean judge(String s, int i, int j) {
		while(i<=j) {
			if(s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			}
			else
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s = "aab";
		System.out.println(s+"a");
		System.out.println(judge(s,0,1));
		List<List<String>> res = getParaSubString(s);
		for(List<String> r:res) {
			for(String a:r)
				System.out.print(a+" ");
			System.out.println();
		}
	}
}
