package CollectionsTest;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {
	//验证ArrayList的加入，遍历方法
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		//加入元素到ArrayList中
		al.add(1);
		al.add(2);
		//删除元素
		al.remove(0);
		
		//获取ArrayList中的元素个数
		int vol = al.size();
		//传统的遍历
		System.out.println("传统方法遍历：");
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		
		//使用增强for循环进行遍历
		System.out.println("增强for循环遍历：");
		for(int a:al)
			System.out.println(a);
		
		//使用迭代器进行遍历
		System.out.println("迭代器遍历:");
		Iterator<Integer> iter = al.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		//lambda表达式迭代
		System.out.println("lambda表达式迭代：");
		Iterator<Integer> iterator = al.iterator();
		iterator.forEachRemaining(element ->System.out.println(element));
	}
}
