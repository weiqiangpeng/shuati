package ShuaTi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MinArrangeOfArray {
	public static void main(String[] args) {
		int [] input = {3, 32, 321};
		String s = PrintMinNumber(input);
		System.out.println(s);
	}
	//将给定的一个正整数数组，所有的数字拼接成一个数字，让这个数字最小
	public static String PrintMinNumber(int [] numbers) {
		StringBuilder res = new StringBuilder();
		//新建一个字符串数组，将原始的整数数组转为字符串数组
		String [] array = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			String s = String.valueOf(numbers[i]);
			array[i] = s;
		}
		//转化完毕，自定义对字符串数组进行排序
		//排序规则    ab > ba 则"a" > "b"; ab<ba，则"a"<"b";ab=ba，则"a"="b"。
		Arrays.sort(array, new Comparator<String>(){
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				// TODO Auto-generated method stub
				return s1.compareTo(s2);
			}	
		});
		
		for (int i = 0; i < array.length; i++) {
			res.append(array[i]);
		}
		return res.toString();
	}
}
