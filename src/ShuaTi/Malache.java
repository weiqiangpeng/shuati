package ShuaTi;

public class Malache {
	//求最长回文子字符串的经典算法，马拉车算法
	public String malacher(String s) {
		//s：是给定的字符串，求其最长回文子字符串
		if(s.length() <=1)
			return s;
		//进行预处理，将s首位和中间填充特殊字符#
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append("#");
		}
		String new_str = sb.toString();
		System.out.println("预处理后的字符串为："+new_str);
		int max_center = 0;
		int max_right = 0;
		int[] len = new int[new_str.length()];
		len[0] = 1;
		for(int j=1;j<new_str.length();j++) {
			boolean is_kuozhan = false;
			if(j<max_right) {
				int left_side = 2 *max_center - j;
				if(j+len[left_side] > max_right) {
					len[j] = max_right - j + 1;
					is_kuozhan = true;
				}
				else {
					len[j] = len[left_side];
				}
			}else {
				is_kuozhan = true;
			}
			
			if(is_kuozhan) {
				while(j-len[j]>=0 && j+len[j]<new_str.length() && new_str.charAt(j-len[j]) == new_str.charAt(j+len[j]))
					len[j] ++ ;
				if(len[j] > len[max_center]) {
					max_center = j;
					max_right = max_center + len[j] -1;
				}
			}
		}
		StringBuilder res = new StringBuilder();
		for(int i=max_center-(len[max_center]-1)+1;i<=max_center+(len[max_center]-1);i+=2) {
			res.append(new_str.charAt(i));
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		String s = "abbcbbabbbbbba";
		Malache obj = new Malache();
		String res = obj.malacher(s);
		System.out.println(res);
	}
}
