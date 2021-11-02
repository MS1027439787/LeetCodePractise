import java.util.ArrayList;
import java.util.Arrays;

public class SortSolution {

    //声明全局变量，用于记录数组array的长度,仅用于堆排序
    static int len1;

    public static void main(String[] args) {
        int[] arr = {10,100,20,70,80,1};
        //bubbleSort(arr);
        //QuickSort(arr, 0, arr.length - 1);
        //归并排序，测试时发现直接调用之后，原数组根本不变，看代码之后发现返回结果是一个新数组
        //arr = MergeSort(arr);
        //CountingSort(arr);
        //RadixSort(arr);
        for(int i = 0; i <=arr.length - 1; i++){
            System.out.println(arr[i]);
        }
    }
    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int temp;//定义一个临时变量
        for (int i = 0; i < arr.length - 1; i++) {//冒泡趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果顺序不对，则交换两个元素
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     *插入排序
     */
    public static int[] insertionSort(int[] a)
    {
        for( int i = 1; i < a.length; i++ ){
            int j;
            int tmp = a[ i ];
            //从后往前查找tmp位置
            for( j = i; j > 0 && tmp < a[j-1] ; j-- ){
                a[ j ] = a[ j - 1 ];
            }
            a[ j ] = tmp;
        }
        return a;
    }

    public static int[] insertionSort2(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 选择排序
     */
    public static int[] selectionSort(int[] arr) {
        if (arr.length == 0)
            return arr;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /**
     * 希尔排序（缩小增量排序）
     */
    public static int[] ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            //每一次for循环，就是一次直接插入排序的过程，找出array[i]在当前大分组中的位置并插入，和直接插入排序不同的是，直接插入排序增量为1
            // ，而这里的增量为gap（while里的循环），外层循环i增量为1代表着直接插入排序会在不同分组之间穿插着进行。
            for (int i = gap; i < len; i++) {
                //分组后第一组的第二个数，tmp暂存
                temp = array[i];
                //按照gap递减往前查询
                int preIndex = i - gap;
                //此循环开始时除array[i]之外，该组前面数据都已经有序，只需找到array[i]的位置即可，
                // 类比直接插入排序，先用tmp暂存array[i]，然后找位置的同时元素按照gap间隔依次后移，
                // 最后放入该位置。一次遍历，同时穿插调整各个大分组。
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                //当跳出while循环时，preindex值小于等于0，preIndex + gap 一定是当前组前面的一个数
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 快速排序方法
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序辅助算法——partition
     */
    public static int partition(int[] array, int start, int end) {
        //随机选取值作为枢轴，移动的核心是双指针，将小于枢轴的值不断左移，等遍历到最后一位时，小于等于枢轴的值都已经被移到了左方，而枢轴值是最后一位，枢轴值同理移动，最后smallIndex保存枢轴位置。
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++){
            if (array[i] <= array[end]) {
                //遍历比较，每有一个小于等于枢轴值的smallIndex值加1，由于是小于等于，所以等于枢轴的值会在枢轴左边
                smallIndex++;
                //开始交换数据，将小于枢轴值的值从start位置依次往后存放，结束时，
                // 小于枢轴的值都被移到了左方，右方自然就是大于枢轴的值，枢轴值作为数组最后一位同样参与遍历
                //理论上来说i是一定大于smallIndex的，因为i从start开始递增，无条件限制，而smallindex从start-1开始递增，而且递增有条件限制
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        }
        //返回当前枢轴位置
        return smallIndex;
    }
    /**
     * 交换数组内两个元素
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 堆排序算法
     *
     * @param array
     * @return
     */
    public static int[] HeapSort(int[] array) {
        len1 = array.length;
        if (len1 < 1) return array;
        //1.构建一个最大堆
        for (int i = (len1/2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
        //2.循环将堆首位（最大值）与末位交换，然后再重新调整最大堆
        while (len1 > 0) {
            int tmp = array[0];
            array[0] = array[len1 - 1];
            array[len1 - 1] = tmp;
            len1--;
            adjustHeap(array, 0);
        }
        return array;
    }
    /**
     * 自上而下调整使之成为最大堆
     *
     * @param array
     * @param i
     */
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len1 && array[i * 2] > array[maxIndex])
            maxIndex = i * 2;
        //如果有右子树，且右子树大于父节点和左子树中的最大者，则将最大指针指向右子树
        // ，此时maxIndex中存储的是左子树和父节点中的较大者的下标。
        if (i * 2 + 1 < len1 && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //如果父节点不是最大值，也就是上面对maxindex值有调整，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            int tmp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = tmp;
            //此时maxIndex指向的是交换位置后的子节点，由于交换了位置，子节点的子树必须递归进行调整。
            adjustHeap(array, maxIndex);
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

    /**
     * 计数排序
     *
     * @param array
     * @return
     */
    public static int[] CountingSort(int[] array) {
        if (array.length == 0) return array;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return array;
    }

    /**
     * 桶排序
     */
    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                for (int j = 0; j < bucketArr.get(i).size(); j++)
                    resultArr.add(bucketArr.get(i).get(j));
            } else {
                if (bucketCount == 1)
                    bucketSize--;
                ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }
        return resultArr;
    }
    /**
     * 基数排序
     * @param array
     * @return
     */
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;    }

}
