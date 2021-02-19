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
     * 不添加前置节点，自己根据思路实现，第一遍漏掉了删除头结点的情况,后面重新完善了下
     * 思路：快慢指针
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
        if (tmp == null)
            //需要将原head节点的next指针置空吗？如果不置空，jvm会回收吗？
            head = head.next;
        if (tmp != null)
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

    /**
     * 19. 删除链表的倒数第N个节点(最简单)
     * 官方方法：先求链表长度，再进行删除
     * 大同小异
     */
    public static ListNode removeNthFromEnd_official_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 一遍通过
     */
    public static int kthToLast(ListNode head, int k) {
        ListNode tmp = head;
        ListNode result = head;
        int len = 0;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        for (int j = 1; j < len - k + 1; j++) {
            result = result.next;
        }
        return result.val;
    }

    /**
     * 面试题 02.03. 删除中间节点
     * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
     * 思路将该节点变为下一个节点，然后将其next指针重新调整
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 21. 合并两个有序链表(迭代)
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        pre.next = l1 ==null ? l2 : l1;
        return preHead.next;
    }
}

