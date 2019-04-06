package Array;

public class KMP {
	public static int[] get_next(String s){
		
		int[] A = new int[s.length()];
		A[0] = -1;
		int i=0;
		int j=-1;
		while(i<s.length()-1){
			if(j == -1 || s.charAt(i) == s.charAt(j))
				A[++i] = ++j;
			else
				j = A[j];
		}
		return A;
	}
	
	public static void KMP(String s, String p){
		System.out.println("主字符串为："+s);
		System.out.println("模式字符串为："+p);
		int[] next = get_next(p);
		System.out.println("next数组为：");
		for (int i = 0; i < next.length; i++) {
			System.out.print(next[i] + " ");
		}
		System.out.println();
		int p1 =0;
		int p2 = 0;
		while(p1<s.length() && p2<p.length()){
			//p2 == -1说明当前模式串的指针已到达第一个位置，已经不能再退了，所以此时应该将主串和模式串同时向后移动一位
			if(p2 == -1 || s.charAt(p1) == p.charAt(p2)){
				p1++;
				p2++;
			}else{
				p2 = next[p2];
			}
		}
		if(p2 == p.length()){
			System.out.println("p2:"+p2+" p1:" + p1);
			System.out.print("主串匹配的位置为：");
			System.out.println(p1-p2);
		}else{
			System.out.println("不匹配！");
		}
	}
	//以上是求next数组和kmp算法的程序，算法的时间复杂度为O(m+n),但是依然是有缺陷的，因为在回溯的时候，虽然j=next[j]，但是
	//依然会有很多无效的比较，例如，模式“aaaab”在和主串“aaabaaaab”匹配时：当i=3,j=3时，s.data[3]≠t.data[3],由next[j]的指示还需进行i=3、j=2,i=3、j=1,i=3、j=0等3次比较。
	//实际上，因为模式中的第1、2、3个字符和第4个字符都相等，因此不需要再和主串中第4个字符相比较，而可以将模式一次向右滑动4个字符的位置直接进行i=4，j=0时的字符比较。
	//下面进行改进
	public static int[] new_getNext(String s){
		int[] new_next = new int[s.length()];
		int i = 0;
		int j = -1;
		new_next[0] = -1;
		while(i<s.length()-1){
			if(j==-1 || s.charAt(i) == s.charAt(j)){
				j++;
				i++;
				//这里判断的目的是：求next数组的目的就是当主串第i个位置和模式串第j个位置上的字符不同的时候，模式串的j应该退回到什么位置，
				//如果退回的位置和原来的位置上的值是一样的，那么这个回退就会变得没有意义，所以这里判断：s.charAt(i) == s.charAt(j)
				//若相等，则说明此时若是回退到j位置，没有意义的，回退完之后还是和主串不相等，还是要再次回退的，所以这里干脆直接一步到位，
				//一次直接回退到new_next[j]的位置。
				if(s.charAt(i) == s.charAt(j)){
					new_next[i] = new_next[j];
				}else
					new_next[i] = j;
			}else
				j = new_next[j];
		}
		return new_next;
	}
	public static void main(String[] args) {
		String s = "ABABDABABCABA";
		String p = "ABABCABA";
		KMP(s, p);
		int[] next = get_next(p);
		System.out.println("old next array:");
		for (int i = 0; i < next.length; i++) {
			System.out.print(next[i] + " ");
		}
		System.out.println();
		int[] new_next =new_getNext(p);
		System.out.println("new next array:");
		for (int i = 0; i < new_next.length; i++) {
			System.out.print(new_next[i] + " ");
		}
		System.out.println();
	}
}
