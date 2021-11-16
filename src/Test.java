import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author masai
 * @date 2019/12/25
 */
public class Test {
    public static void main(String[] args) {
        String a = "abc";
        System.out.println(a.equals(a));
        Map map = new HashMap();
        map.put("abc","abc");
     }

    public static ListNode reverseabc(ListNode list) {
        if (list == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = list;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}