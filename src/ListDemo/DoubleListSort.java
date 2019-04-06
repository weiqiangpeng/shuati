package ListDemo;

class DoubleListNode{
	int val;
	DoubleListNode next;
	DoubleListNode pre;
	
	DoubleListNode(int val){
		this.val = val;
		this.next = null;
		this.pre = null;
	}
}

public class DoubleListSort {
	public static void print(DoubleListNode head) {
		while(head != null) {
			if(head.pre == null && head.next == null) {
				System.out.println(head.val+"的前驱是：null,后继也是空");
			}else if(head.pre == null && head.next != null) {
				System.out.println(head.val+"的前驱是：null,后继是："+head.next.val);
			}else if(head.pre != null && head.next == null) {
				System.out.println(head.val+"的前驱是："+head.pre.val+" 后继是：null");
			}
			else {
				System.out.println(head.val+"的前驱是："+head.pre.val+" 后继是："+head.next.val);
			}
			head = head.next;
		}
	}
	
	//双向链表的排序
	public static DoubleListNode sortList(DoubleListNode head) {
		//借鉴选择排序的思想
		if(head == null || head.next == null)
			return head;
		//创建一个哑节点,让其志向头结点
		DoubleListNode ya = new DoubleListNode(220);
		ya.next = head;
		head.pre = ya;
		while(head != null) {
			DoubleListNode p = head.next;
			while(p != null) {
				if(head.val > p.val) {
					System.out.println("交换了："+head.val + "和： "+p.val);
					swapNode(head, p);
					//还要把节点名称交换过来
					DoubleListNode tmp = head;
					head = p;
					p = tmp;
				}
				p = p.next;
			}
			head = head.next;
		}
		return ya.next;
	}
	
	public static void swapNode(DoubleListNode s1, DoubleListNode s2) {
		//交换2个节点的函数，分情况讨论：s1的下一个节点是s2，s2的下一个节点是s1,s1和s2不相邻
		if(s1.next == s2) {
			if(s2.next != null) {
				s2.next.pre = s1;
			}
			if(s1.pre != null)
				s1.pre.next = s2;
			s1.next = s2.next;
			s2.pre = s1.pre;
			s1.pre = s2;
			s2.next = s1;
		}
//		else if(s1.pre == s2) {
//			if(s2.next != null)
//				s2.next.pre = s1;
//			if(s1.pre != null)
//				s1.pre.next = s2;
//			s2.next = s1.next;
//			s1.pre = s2.pre;
//			s1.next = s2;
//			s2.pre = s1;
//		}
		else if(s2.next == s1)
			swapNode(s2,s1);
		else {
			//s1和s2不相邻的情况
			if(s1.next!= null)
				s1.next.pre = s2;
			if(s1.pre != null)
				s1.pre.next = s2;
			if(s2.next != null)
				s2.next.pre = s1;
			if(s2.pre != null)
				s2.pre.next = s1;
			DoubleListNode tmp1 = s1.pre;
			DoubleListNode tmp2 = s1.next;
			s1.next = s2.next;
			s1.pre = s2.pre;
			s2.next = tmp2;
			s2.pre = tmp1;
		}
	}
	
	public static void main(String[] args) {
		DoubleListNode head = new DoubleListNode(4);
		DoubleListNode sec = new DoubleListNode(9);
		DoubleListNode thir = new DoubleListNode(2);
		DoubleListNode four = new DoubleListNode(1);
		head.next = sec;
		sec.next = thir;
		sec.pre = head;
		thir.next = four;
		thir.pre = sec;
		four.pre = thir;
		print(head);
		System.out.println("下面开始排序........................");
		print(sortList(head));
	}
}
