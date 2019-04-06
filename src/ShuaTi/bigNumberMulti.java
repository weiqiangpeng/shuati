package ShuaTi;

public class bigNumberMulti {
	//大数乘法
	public static String bigMulti(String s1, String s2) {
		if(s1 == null || s2 == null)
			return "";
		int n1 = s1.length();
		int n2 = s2.length();
		int[] a = new int[n1];
		int[] b = new int[n2];
		int[] c = new int[n1+n2];
		//将s1和s2的元素，倒序存放在a和b中，并转为int类型
		for(int i=0;i<n1;i++) a[n1-i-1] = s1.charAt(i) - '0';
		for(int i=0;i<n2;i++) b[n2-i-1] = s2.charAt(i) - '0';
		
		for(int val:a)
			System.out.print(val);
		System.out.println();
		for(int val:b)
			System.out.print(val);
		System.out.println();
		
		for(int i=0;i<n1;i++) {
			for(int j=0;j<n2;j++) {
				c[i+j] += a[i] * b[j];
			}
		}
		for(int i=0;i<c.length;i++) {
			if(c[i] > 10) {
				//万万不能写反了
				c[i+1] += c[i] / 10;
				c[i] = c[i] % 10;
			}
		}
		int index = c.length-1;
		for(;index>=0;index--) {
			if(c[index] != 0)
				break;
		}
		StringBuilder sb = new StringBuilder();
		for(;index>=0;index--)
			sb.append((char)(c[index]+'0'));
		return sb.toString();
	}
	public static void main(String[] args) {
		String s1 = "12345";
		String s2 = "98765";
		System.out.println(bigMulti(s1,s2));
		System.out.println(12345*98765);
	}
}
