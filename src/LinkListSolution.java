/**
 * @author masai
 * @date 2021/2/1
 */
public class LinkListSolution {
    /**
     * 206.反转链表
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

    /**
     * 19. 删除链表的倒数第N个节点
     *    不添加前置节点，自己根据思路实现，第一遍漏掉了删除头结点的情况,后面重新完善了下
     *    思路：快慢指针
     */

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //快慢指针
        ListNode fast = head;
        ListNode slow = head;
        ListNode tmp = null;
        int i = 0;
        while (i < n) {
            fast = fast.next;
            i++;
        }
        while (fast != null) {
            fast = fast.next;
            tmp = slow;
            slow = slow.next;
        }
        if(tmp == null)
            //需要将原head节点的next指针置空吗？如果不置空，jvm会回收吗？
           head = head.next;
        if(tmp != null)
            tmp.next = slow.next;
        return head;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     */

    public static ListNode removeNthFromEnd_official(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}

