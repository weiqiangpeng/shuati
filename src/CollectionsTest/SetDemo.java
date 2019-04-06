package CollectionsTest;

import java.util.Iterator;
import java.util.TreeSet;

public class SetDemo {
	public static void main(String[] args) {
		TreeSet<String> set = new TreeSet<>();
		set.add("b");
		set.add("a");
		set.add("r");
		set.add("e");
		//移除一个元素
		set.remove("r");
		
		//普通遍历,由于树集是对插入任意进行排序输出，所以，不可以根据索引获得
		System.out.println("根据索引遍历不行");
		System.out.println("增强for循环");
		for (String s:set) {
			System.out.println(s);
		}
		//迭代器遍历
		System.out.println("迭代器迭代：");
		Iterator<String> iter = set.iterator();
		while(iter.hasNext())
			System.out.println(iter.next());
		
		//lambda表达式迭代
		System.out.println("lambda表达式迭代：");
		Iterator<String> it = set.iterator();
		it.forEachRemaining(element->System.out.println(element));
	}
}
