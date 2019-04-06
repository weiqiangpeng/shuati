package ListDemo;

class ListNode{
	int val;
	ListNode next;
	ListNode(int val){
		this.val = val;
		this.next = null;
	}
}

public class reverseList {
	
	public static void printList(ListNode head) {
		while(head!=null) {
			System.out.print(head.val+"-->");
			head = head.next;
		}
		System.out.println();
	}
	
	public static ListNode ReverseList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode last = null;
		while(head!=null) {
			ListNode tmp = head.next;
			head.next = last;
			last = head;
			head = tmp;
		}
		return last;
	}
	
	public static ListNode ReverseListByDiGui(ListNode head) {
		if(head==null || head.next == null)
			return head;
		ListNode newHead = ReverseListByDiGui(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(9);
		printList(head);
//		ListNode head1 = head;
//		ListNode newHead = ReverseList(head1);
//		printList(newHead);
		ListNode newHead2 = ReverseListByDiGui(head);
		printList(newHead2);
	}
}
