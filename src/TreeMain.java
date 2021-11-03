import java.util.List;

public class TreeMain {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        a.left = c;
        a.right = d;
        root.left = a;
        root.right = b;
        List list = TreeSolution.preorderTraversal2(root);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        TreeSolution.mirrorTree(root);
        List list2 = TreeSolution.preorderTraversal2(root);
        for(int i = 0; i < list2.size(); i++){
            System.out.println(list2.get(i));
        }
    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
