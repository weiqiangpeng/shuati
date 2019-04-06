package Array;

public class MaxNoRepeatSubString {
	public static int getmaxSubString(String s) {
		//求s的最长不重复子串
		if(s.length() <= 1)
			return s.length();
		//所有的字符都可以放入128大小的数组里,记录每一个字符最后出现的位置
		int[] lastIndex = new int[128];
		//定义滑动窗口左边界left和最大长度max
		int left = 0;
		int max = 0;
		
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			//如果当前字符是第一次出现，或者是在滑动窗口left的左边，那么直接扩展max即可
			if(lastIndex[ch] == 0 || lastIndex[ch] < left) {
				//i表示滑动窗口的右边界，如果ch是第一次出现，或者ch不在当前滑动窗口内，说明滑动窗口里都是无重复的，计算max
				max = Math.max(max, i-left+1);
			}
			else {
				//否则说明当前字符不是第一次出现，那么更新left，去掉这个重复的字符，使得当前窗口无重复值
				left = lastIndex[ch];
			}
			//更新ch最后一次出现的位置
			lastIndex[ch] = i+1;
		}
		return max;
	}
	
	public static void main(String[] args) {
		String s = "abacdaefw";
		System.out.println(getmaxSubString(s));
	}
}
