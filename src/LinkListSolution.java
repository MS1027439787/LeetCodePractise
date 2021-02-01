/**
 * @author masai
 * @date 2021/2/1
 */
public class LinkListSolution {
    /**
     * 单链表翻转
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }
}
