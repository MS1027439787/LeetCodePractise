import java.util.Stack;

/**
 * @author masai
 * @date 2021/2/1
 */
public class LinkListSolution {

    /**
     * 剑指 Offer 06从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int arr[] =  new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = stack.pop().val;
        }
        return arr;
    }

    /**
     * 206.反转链表(剑指 Offer 24)
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
     * 剑指 Offer 35
     * 复杂链表的复制
     * 在 原链表每一个节点后，续接 一个新节点
     * 为 当前链表 的 每一个新new节点的random属性 赋值
     * 将 当前链表，按照一个间隔一个的顺序 拆分开
     * 将新new节点，串成一个 新链表
     * 将原链表的节点，拆出来并组合成 原链表
     * 将 原链表的最后一个节点 的 next指针，重新指向null
     */
    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head;

        /*
            在 原链表每一个节点后，续接 一个新节点
        */
        while (curNode != null) {
            ListNode tempNode = new ListNode(curNode.val);
            tempNode.next = curNode.next;
            curNode.next = tempNode;    // 将 当前节点的next 指向 新new节点
            curNode = tempNode.next;    // 将 当前节点 指向 原链表的下一个节点
        }

        /*
            为 当前链表 的 每一个新new节点的random属性 赋值
        */
        curNode = head;
        while (curNode != null) {
            /*
                由于我们让curNode首先指向的是head节点，
                因此，我们让curNode一直指向 原链表的节点 即可！
            */
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;  // 为 新new节点的random 赋值为 原链表中应该的random指向的节点的相应的新new节点
            }
            curNode = curNode.next.next;
        }

        /*
            将 当前链表，按照一个间隔一个的顺序 拆分开
                1、将新new节点，串成一个 新链表
                2、将原链表的节点，拆出来并组合成 原链表
        */
        curNode = head.next;
        ListNode preNode = head;
        ListNode result = head.next;
        while (curNode.next != null) {
            preNode.next = preNode.next.next;
            curNode.next = curNode.next.next;
            preNode = preNode.next;
            curNode = curNode.next;
        }
        preNode.next = null;    // 将 原链表的最后一个节点 的 next指针，重新指向null
        return result;
    }
    /**
     * 剑指 Offer 18
     * 删除链表的节点
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if(head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) pre.next = cur.next;
        return head;
    }

    /**
     *剑指 Offer 52
     *两个链表的第一个公共节点
     *浪漫相遇
     * 两个链表长度分别为L1+C、L2+C， C为公共部分的长度，按照楼主的做法：
     * 第一个人走了L1+C步后，回到第二个人起点走L2步；第2个人走了L2+C步后，
     * 回到第一个人起点走L1步。 当两个人走的步数都为L1+L2+C时就两个家伙就相爱了
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //极致简洁的代码
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }


    /**
     * 19. 删除链表的倒数第N个节点
     * 核心就是找到倒数第n个是正数第几个
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
     * 剑指 Offer 22
     * 链表中倒数第k个节点
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
     * 剑指 Offer 25
     * 合并两个排序的链表
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

    /**
     * 24. 两两交换链表中的节点
     * 递归
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
    /**
     * 24. 两两交换链表中的节点
     * 迭代
     */
    public static ListNode swapPairs2(ListNode head) {
        ListNode tmpHead = new ListNode(0);
        tmpHead.next = head;
        ListNode temp = tmpHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return tmpHead.next;
    }
}

