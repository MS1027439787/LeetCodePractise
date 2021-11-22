import java.util.*;

/**
 * @author masai
 * @date 2021/2/5
 */
public class TreeSolution {

    /**
     * 二叉树最近公共祖先
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //这里是思考难点。定义规则：比如在某一棵子树上先找到了p，则无需继续遍历这棵子树，因为即使这棵子树有q，p也一定是q的祖先，也就是它们两个的最近公共祖先。
        if (null == root || root.val == p.val || root.val == q.val)
            return root;
        //按照上述规则，找到root的左子树的最近公共祖先。
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //按照上述规则，找到root的右子树的最近公共祖先。
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //一边找到了，一边没找到，根据上述规则，找到的就是最近公共祖先。
        if (null == left)
            return right;
        if (null == right)
            return left;
        //如果在左右子树分别找到了p和q，则说明root是它们两个的最近公共祖先。
        return root;
    }

    /**
     * 二叉树前序遍历，迭代法
     * 前序的规则就是根结点 ---> 左子树 ---> 右子树.（一定要记清楚定义）
     */
    //递归方法
    public static void preorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        postorder(root.left, res);
        postorder(root.right, res);

    }

    //用栈来模拟整个递归算法的过程
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        //root != null该条件非常关键，切记，切记
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                //遍历到左边最后一个空节点（深度优先）
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    //该写法好理解，但是本质上没有利用栈来模拟递归过程
    public static List<Integer> preorderTraversal(TreeNode root) {
        List result = new ArrayList<>();
        if (root == null)
            return result;
        Stack stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = (TreeNode) stack.pop();
            result.add(tmp.val);
            if (tmp.right != null)
                stack.push(tmp.right);
            if (tmp.left != null)
                stack.push(tmp.left);
        }
        return result;
    }

    /**
     * 145. 二叉树的后序遍历
     */
    //递归方法
    public static void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    //迭代方法
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //prev保存上一个加入结果集的节点，判断一个节点能否加入结果集的条件有两个：
            // 1、左右子树都为空，
            // 2、右子树不空，但是右子树已经被访问
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    //94、二叉树的中序遍历，递归方法
    public static void inorderTraversal(TreeNode root, List res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    //迭代方法
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
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


    private Map<Integer, Integer> indexMap;

    /**
     * 剑指 Offer 28. 对称的二叉树
     * 递归
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;
        //按层分开，每次保证对比的都是镜像位置，可以以最左和最右进行推演思考
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    /**
     * 剑指 Offer 27
     * 二叉树的镜像
     * 翻转二叉树
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 递归方法：树的深度等于左右子树深度的最大值+1
     */
    public static int maxDepth(TreeNode root) {
        if (root == null)
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
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        int length = list.size();
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
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
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            //循环次数刚好是进入循环时队列的size值,即该层的节点数
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
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
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 递归法和迭代法都比较难，中等难度
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

}
