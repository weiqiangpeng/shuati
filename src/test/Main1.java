package test;

public class Main1 {
	public static void main(String[] args) {
		String s = " I  like you ";
		String[] arr = s.split(" ");
		System.out.println(arr.length);
		for(String val:arr) {
			System.out.println(val);
		}
	}
}
