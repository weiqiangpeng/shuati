package ShuaTi;

public class ReverseSentence {
	// public static String ReverseSentence(String str) {
	// if(str.trim().equals("")){
	// return str;
	// }
	// String[] a = str.split(" ");
	// StringBuffer o = new StringBuffer();
	// int i;
	// for (i = a.length; i >0;i--){
	// o.append(a[i-1]);
	// if(i > 1){
	// o.append(" ");
	// }
	// }
	// return o.toString();
	// }

	public static String reverseHelper(String s) {
		if (s.trim().length() == 0)
			return s;
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(String.valueOf(s.charAt(i)));
		}
		return sb.toString();
	}

	public static String ReverseSentenceII(String str) {
		if(str.trim().length() == 0)
			return str;
		String s = reverseHelper(str);
		StringBuilder sb = new StringBuilder();
		int start = 0;
		int end = 0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i) == ' '){
				end = i;
				String tmp = reverseHelper(s.substring(start, end));
				System.out.println(tmp);
				sb.append(tmp);
				start = i+1;
			}
		}
		return sb.toString();
	}
		
	public static String ReverseSentence(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		System.out.println(sb.toString());
		int start = 0, end = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == ' ') {
				end = i - 1;
				reverse(sb, start, end);
				start = i + 1;
			}
		}
		reverse(sb, start, str.length() - 1);
		return sb.toString();
	}

	private static void reverse(StringBuilder sb, int start, int end) {
		char t;
		while (start < end) {
			t = sb.charAt(start);
			sb.setCharAt(start, sb.charAt(end));
			sb.setCharAt(end, t);
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		String s = "   I am a student.  ";
		System.out.println(ReverseSentence(s));  //student. a am I   
		System.out.println(ReverseSentenceII(s));
	}
}
