import java.util.*;

public class TreeSolution {
    private Map<Integer, Integer> indexMap;

    /**
     * 剑指 Offer 28. 对称的二叉树
     * 递归
     */
    public  boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }
    boolean  recur(TreeNode L, TreeNode R) {
        if(L == null && R == null) return true;
        if(L == null || R == null || L.val != R.val) return false;
        //按层分开，每次保证对比的都是镜像位置，可以以最左和最右进行推演思考
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    /**
     * 剑指 Offer 27
     * 二叉树的镜像
     * 翻转二叉树
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);;
        return root;
    }
    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 递归方法：树的深度等于左右子树深度的最大值+1
     */
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }
    /**
     * 94. 二叉树的中序遍历
     * 前序遍历，后续遍历只需要调换位置即可
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 剑指 Offer 32 - I
     * 从上到下打印二叉树
     * 即二叉树层次遍历
     * 广度优先搜索同理
     */
    public int[] levelOrder(TreeNode root) {
        if(root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        int length = list.size();
        int[] res = new int[length];
        for(int i = 0; i < length; i++){
            res[i] = list.get(i);
        }
        return res;
    }
    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * 按层次打印
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> list = new ArrayList();
        while(!queue.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            //循环次数刚好是进入循环时队列的size值,即该层的节点数
            for(int i = 0; i < queue.size(); i++){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            list.add(temp);

        }
        return list;
    }
    /**
     * 剑指 Offer 07. 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *    递归法和迭代法都比较难，中等难度
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    /**
     * 剑指 Offer 33
     * 二叉搜索树的后序遍历序列
     */

    /**
     * 剑指 Offer 36
     * 二叉搜索树与双向链表
     */
}
