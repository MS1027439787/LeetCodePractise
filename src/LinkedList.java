/**
 * @author masai
 * @date 2021/2/1
 */
public class LinkedList {
    public static void main(String[] args) {
        //链表初始化
        int i = 4;
        ListNode head = new ListNode(5);
        ListNode curr = head;
        while (i > 0) {
            ListNode node = new ListNode(i);
            curr.next = node;
            curr = curr.next;
            i--;
        }
        ListNode tmp = LinkListSolution.reverse(head);
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
