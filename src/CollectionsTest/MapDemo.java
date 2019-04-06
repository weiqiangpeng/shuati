package CollectionsTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		//往map中添加键值对
		map.put("pwq", 24);
		map.put("ll", 21);
		
		//根据键获取值
		System.out.println(map.get("ll"));
		//如果键值不存在,则返回null
		System.out.println(map.get("aaaa"));
		
		//为了程序更好，访问val的时候需要判断一下，key是否存在
		if(map.containsKey("pwq"))
			System.out.println(map.get("pwq"));
		
		//当然，也可以直接判断val是否存在
		System.out.println(map.containsValue(21));
		
		//遍历map，当键值都需要时，用entrySet
		System.out.println("使用entrySet遍历：");
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
		}
		
		//只需要键或者只需要值的时候，使用keySet或者values
		System.out.println("只需要键(会对键值进行排序)：");
		for(String s:map.keySet())
			System.out.println("key:" +s);
		System.out.println("只需要值：");
		for(int v:map.values())
			System.out.println("value:" +v);
		//使用迭代器
		System.out.println("使用迭代器：");
		Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, Integer> entry = iter.next();
			System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
		}
	}
}
