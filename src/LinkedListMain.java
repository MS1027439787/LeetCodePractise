/**
 * @author masai
 * @date 2021/2/1
 */
public class LinkedListMain {
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
        //ListNode tmp = LinkListSolution.removeNthFromEnd_official(head, 1);
        //ListNode tmp = LinkListSolution.removeNthFromEnd_1(head, 2);
        //ListNode tmp = LinkListSolution.removeNthFromEnd_official_2(head, 2);
        //返回链表倒数第k个节点值
        //System.out.println(LinkListSolution.kthToLast(head,5));
        //删除中间节点
        //LinkListSolution.deleteNode(head);
        //合并有序链表
        //ListNode tmp = LinkListSolution.mergeTwoLists(head, head);
        //两两交换链表中的节点
        ListNode tmp = LinkListSolution.swapPairs(head);
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }

        System.out.println("结束");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode random;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
