import java.util.Arrays;

/**
 * @author masai
 * @date 2019/12/25
 */
public class Test {
    public static void main(String[] args) {
        int start = 0, end = 8;
        int[] arr = {91, 60, 96, 13, 35, 65, 46, 65, 10};
        for (int a : MergeSort(arr)) {
            System.out.print(a + "\t");
        }
    }
        /**
         * 归并排序
         *
         * @param array
         * @return
         */
        public static int[] MergeSort ( int[] array){
            if (array.length < 2) return array;
            int mid = array.length / 2;
            //copyOfRange方法，包括from，不包括to，对下面来说就是left包括0不包括mid，right包括mid不包括array.length
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);
            return merge(MergeSort(left), MergeSort(right));
        }

        /**
         * 归并排序——将两段排序好的数组结合成一个排序数组
         *
         * @param left
         * @param right
         * @return
         */
        public static int[] merge( int[] left, int[] right){
            int[] result = new int[left.length + right.length];
            for (int index = 0, i = 0, j = 0; index < result.length; index++) {
                //合并过程i代表left数组下标，j指示right数组下标，index指示result数组下标，left，right都已经有序
                //将left，right较小的放入result数组。
                //边界判断，当i>= left.lengh时，代表left数组已经全部进入result数组
                //， 其实此时只需要将right数组剩余值依次往后赋值给result数组即可
                //
                if (i >= left.length) {
                    //当左数组已经完全进入结果数组时，只需要将右数组依次赋值给result数组即可
                    for (int k = j; k < right.length; k++) {
                        result[index] = right[k];
                        index++;
                    }
                    return result;
                }
                //同上
                else if (j >= right.length) {
                    for (int k = i; k < left.length; k++) {
                        result[index] = left[k];
                        index++;
                    }
                    return result;
                } else if (left[i] > right[j])
                    //比较然后赋值
                    result[index] = right[j++];
                else
                    result[index] = left[i++];
            }
            return result;
        }


}