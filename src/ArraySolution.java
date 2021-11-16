import java.util.*;

public class ArraySolution {


    /**
     * 面试题：一个先递增再递减的数组，求出数组中不重复元素的个数
     * 要求: o(n)
     */


    public static int repeat_num(int[] arr) {
        int  max_index = 0, i = 0, j = 0, rep_num = 0, len = arr.length;
        //先找到最大值
        while (i < len - 1) {
            if (arr[i] > arr[i + 1]) {
                max_index = i;
                break;
            } else
                i++;
        }
        //双指针从最大值往后遍历，求出重复元素个数
        i = max_index - 1;
        j = max_index + 1;
        while (i >= 0 && j <= len - 1) {
            if (arr[i] == arr[j]) {
                i--;
                j++;
                rep_num += 2;
            } else if (arr[i] > arr[j])
                i--;
            else
                j++;
        }
        return len - rep_num;

    }

    /**
     * 面试题：删除arraylist中小于0的元素
     */
    public static List removeElement(List<Integer> list) {
        if (list.size() == 0 || list == null) {
            return list;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) < 0) {
                list.remove(i);
            }
        }
        return list;
    }

    /**
     * 1、两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashmap.containsKey(target - nums[i])) {
                return new int[]{hashmap.get(target - nums[i]), i};
            }
            hashmap.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 1、两数之和(暴力法)
     */
    public static int[] twoSum_2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 面试题：数组中重复的数字
     */
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            //添加失败，代表set中已存在
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    /**
     * 面试题：二维数组查找，每一行按照从左到右顺序递增，每一列按照从上到下顺序递增
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (target == num) {
                return true;
            } else if (target > num) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }
}
