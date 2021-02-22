import java.util.ArrayList;
import java.util.List;

/**
 * @author masai
 * @date 2021/2/5
 */
public class TreeSolution {
    //94、二叉树的中序遍历，递归方法
    public static List<Integer> inorderTraversal(TreeNode root, List result) {

        if (root != null) {
            if (root.left != null)
                inorderTraversal(root.left, result);
            result.add(root.val);
            if (root.right != null)
                inorderTraversal(root.right, result);
        }
        return result;
    }

    //面试题：求二叉树每一条路径上值的和并输出
    public static List<Integer> traversal(TreeNode root, List list, int sum) {
        if (root != null) {
            sum += root.val;
            if (root.right == null && root.left == null)
                list.add(sum);
            else {
                traversal(root.left, list, sum);
                traversal(root.right, list, sum);
            }
        }
        return list;
    }
}
