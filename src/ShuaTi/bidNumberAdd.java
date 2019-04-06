package ShuaTi;

public class bidNumberAdd {
	//大数加法
	public static String bigAdd(String s1, String s2) {
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();
		int n1 = s1.length();
		int n2 = s2.length();
		int max = Math.max(n1, n2);
		int[] a = new int[max+1];
		int[] b = new int[max+1];
		for(int i=n1-1;i>=0;i--){
			a[n1-i-1] = ch1[i] - '0';
		}
		for(int val:a)
			System.out.print(val+" ");
		System.out.println();
		for(int i=n2-1;i>=0;i--)
			b[n2-i-1] = ch2[i] - '0';
		for(int val:b)
			System.out.print(val+" ");
		System.out.println();
		for(int i=0;i<=max;i++) {
			if(a[i] + b[i] >= 10) {
				int tmp = a[i] + b[i];
				a[i] = tmp % 10;
				a[i+1] += tmp / 10;
			}
			else
				a[i] = a[i] + b[i];
		}
		StringBuilder sb = new StringBuilder();
		if(a[max] == 1) sb.append((char)(a[max]+'0'));
		for(int i=max-1;i>=0;i--)
			sb.append((char)(a[i] + '0'));
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		String s1 = "12345111111";
		String s2 = "789456321";
		System.out.println(bigAdd(s1, s2));
	}
}
