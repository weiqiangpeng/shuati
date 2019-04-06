package ShuaTi;

public class ReverseList {
	//尾插法建立单链表
	public static ListNode createListI(int[] A) {
		if(A.length == 0)
			return null;
		ListNode head = new ListNode(-1);
		head.next = null;
		ListNode r = head;
		for (int i = 0; i < A.length; i++) {
			ListNode node = new ListNode(A[i]);
			r.next = node;
			r = node;
		}
		r.next = null;
		return head;
	}
	
	//头插法建立单链表
	public static ListNode createListII(int[] A) {
		if(A.length == 0)
			return null;
		ListNode head = new ListNode(-1);
		head.next = null;
		
		for (int i = 0; i < A.length; i++) {
			ListNode node = new ListNode(A[i]);
			node.next = head.next;
			head.next = node;
		}
		return head;
	}
	
	
	//从尾到头倒序打印
	public static void ReversePrint(ListNode head) {
		if(head == null)
			return;
		ReversePrint(head.next);
		System.out.print(head.val + " ");
	}
	
	
	//从头打印
	public static void Allprint(ListNode head) {
		if(head == null)
			return;
		head = head.next;
		while(head!=null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	//翻转链表
	public static ListNode ReverseListI(ListNode head) {
		//这里的头结点head是头结点，但不包括数据，只是一个指引，亚节点
		head = head.next;
		if(head == null || head.next == null)
			return head;
		ListNode last = null;
		while(head!= null) {
			ListNode tmp = head.next;
			head.next = last;
			last = head;
			head = tmp;
		}
		//需要创建一个新的哑巴节点，让其作为反转后链表的头结点
		ListNode newHead = new ListNode(-1);
		newHead.next = last;
		return newHead;
	}
	
	public static void main(String[] args) {
		int[] A = {1,2,3,4,5,6,9,0};
		ListNode head = createListI(A);
		ListNode head1 = createListII(A);
		ReversePrint(head1.next);
		System.out.println();
		//头结点不输出，不保存数据，只是一个指引的作用
		head = head.next;
		while(head!=null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		
		System.out.println();
		head1 = head1.next;
		while(head1!=null) {
			System.out.print(head1.val + " ");
			head1 = head1.next;
		}
		
		System.out.println();
		int[] B = {1,2,3,4,5,6,9,10,11};
		ListNode h1 = createListI(B);
		Allprint(h1);
		ReversePrint(h1.next);
		System.out.println("reverse B:");
		ListNode newHead = ReverseListI(h1);
		Allprint(newHead);
	}
}
