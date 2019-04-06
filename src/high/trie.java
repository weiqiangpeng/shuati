package high;

public class trie {
	//字典树
	/**
	 * 主要解决的问题为：查询是否包含某字符串，统计某个字符串的数量，判断是否有某个前缀，统计以某个字符串为前缀的字符串数量，
	 */
	//首先定义字典树的数据结构
	static class Node{
		/**
		 * path是指经过这个Node节点的字母的个数
		 * end是指以这个Node节点结尾的字符串的数量
		 * next是指这个Node节点的后继节点，一般都是以小写字母，所以可以设置数量为26
		 */
		public int path;
		public int end;
		Node[] next;
		
		public Node() {
			path = 0;
			end = 0;
			next = new Node[26];
		}
	}
	public Node root;
	public trie() {
		//字典树的构造方法，就是初始化一个根节点出来
		root = new Node();
	}
	//在字典树中插入一个字符串
	public void insert(String s) {
		if(s == null)
			return;
		Node cur = root;
		int index=0;
		for(int i=0;i<s.length();i++) {
			index = s.charAt(i) - 'a';
			//为空就创建，index是cur现在的next数组里的下标
			if(cur.next[index] == null)
				cur.next[index] = new Node();
			cur = cur.next[index];
			cur.path++;
		}
		cur.end++;
	}
	
	//统计某个字符串的数量,其实就是遍历到这个字符串末尾的节点，看它的end为多少，如果遍历不到，则返回0
	public int count(String s) {
		if(s == null)
			return 0;
		Node cur = root;
		int index;
		for(int i=0;i<s.length();i++) {
			index = s.charAt(i) - 'a';
			if(cur.next[index] == null)
				return 0;
			cur = cur.next[index];
		}
		return cur.end;
	}
	//判断是否包含某个字符
	public boolean contains(String s) {
		return count(s) > 0;
	}
	//统计以某个字符串为前缀的字符串数量
	public int countPrefix(String prefix) {
		if(prefix == null)
			return 0;
		Node cur = root;
		int index = 0;
		for(int i=0;i<prefix.length();i++) {
			index = prefix.charAt(index) - 'a';
			if(cur.next[index] == null)
				return 0;
			cur = cur.next[index];
		}
		//注意这里返回的是path，表示经过这个节点的个数
		return cur.path;
	}
	//判断是否包含某个前缀
	public boolean startWith(String prefix) {
		return countPrefix(prefix) > 0;
	}
	//删除某个字符串
	public void delete(String s) {
		if(s == null)
			return;
		//否则和插入那些操作是一样的套路
		Node cur = root;
		int index = 0;
		for(int i=0;i<s.length();i++) {
			index = s.charAt(i) - 'a';
			if(--cur.next[index].path == 0) {
				cur.next[index] = null;
				return;
			}
			cur = cur.next[index];
		}
		cur.end--;
	}
	public static void main(String[] args) {
        trie trie = new trie();

        trie.insert("abc");
        trie.insert("ab");
        trie.insert("ab");
        trie.insert("abd");
        trie.insert("bc");
        trie.insert("bd");
        trie.insert("cd");
        trie.insert("cde");
        trie.insert("ce");

        System.out.println(trie.count("ab"));
        trie.delete("ab");
        System.out.println(trie.count("ab"));

        System.out.println(trie.count("abd"));
        trie.delete("ab");
        System.out.println(trie.count("ab"));
        System.out.println(trie.count("abd"));

        trie.delete("abd");
        System.out.println(trie.count("abd"));
    }
	
}
