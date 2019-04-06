package ShuaTi;
import java.util.Stack;


public class TwoStack {
	//使用两个栈模拟一个队列
	private static Stack<Integer> st1 = new Stack<>();
	private static Stack<Integer> st2 = new Stack<>();
	
	public static int pop() {
		if(st2.empty()) {
			while(!st1.empty())
				st2.push(st1.pop());
		}
		return st2.pop();
	}
	public static void push(int a) {
		st1.push(a);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			push(i);
		}
		System.out.println(pop());
		push(77);
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
	}
}
