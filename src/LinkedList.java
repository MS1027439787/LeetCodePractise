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
        //链表翻转
        //ListNode tmp = LinkListSolution.reverse(head);
        //删除链表倒数第n个节点
        //ListNode tmp = LinkListSolution.removeNthFromEnd(head, 1);
        ListNode tmp = LinkListSolution.removeNthFromEnd_official(head, 1);

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

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
