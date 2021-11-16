import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayMain {
    public static void main(String[] args) {
        int[] a = {-3,-2,-1,4,5,100,90,5,4,3};
        System.out.println(ArraySolution.repeat_num(a));
        int[] [] array = {{1,   4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24}};
        List<Integer> list = new ArrayList<Integer>(10);

        list.add(-1);
        list.add(-2);
        list.add(1);
        list.add(2);
        ArraySolution.removeElement(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        ArraySolution.findNumberIn2DArray(array, 19);
    }
}
