import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArraySolution {
    /**
     * 1、两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
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
     * 面试题：二维数组查找，每一行按照从左到右顺序递增，每一列按照从下到上顺序递增
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while(row < rows && column >= 0){
            int num = matrix[row][column];
            if(target == num){
                return true;
            }else if(target > num){
                row++;
            }else{
                column--;
            }
        }
        return false;
    }
}
