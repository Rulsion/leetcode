package csdn.p00343;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 众数是指一组数据中出现次数量多的那个数，众数可以是多个。
 * 中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，如果这组数据的个数为偶数，那就把中间的两个数之和除以2，所得的结果就是中位数。
 * 查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数。
 * 输入描述
 * <p>
 * 输入一个一维整型数组，数组大小取值范围 0<N<1000，数组中每个元素取值范围 0<E<1000
 * 输出描述
 * <p>
 * 输出众数组成的新数组的中位数
 * 示例1   输入输出示例仅供调试，后台判题数据一般不包含示例
 * <p>
 * 输入
 * <p>
 * 10 11 21 19 21 17 21 16 21 18 15
 * <p>
 * 输出
 * <p>
 * 21
 * <p>
 * 示例2   输入输出示例仅供调试，后台判题数据一般不包含示例
 * <p>
 * 输入
 * <p>
 * 2 1 5 4 3 3 9 2 7 4 6 2 15 4 2 4
 * <p>
 * 输出
 * <p>
 * 3
 * <p>
 * 示例3   输入输出示例仅供调试，后台判题数据一般不包含示例
 * <p>
 * 输入
 * <p>
 * 5 1 5 3 5 2 5 5 7 6 7 3 7 11 7 55 7 9 98 9 17 9 15 9 9 1 39
 * <p>
 * 输出
 * <p>
 * 7
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numstrs = in.nextLine().split(" ");
        int[] arr = new int[numstrs.length];
        for (int i = 0; i < numstrs.length; i++) {
            arr[i] = Integer.parseInt(numstrs[i]);
        }
        System.out.println(getMid(arr));
    }

    public static int getMid(int[] arr) {


        //找出众数列表
        Map<Integer, Long> collect = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Integer> list = new ArrayList<>();
        collect.forEach((k,v)->{
            for (long i=0;i<v;i++){
                list.add(k);
            }
        });

        //排序
        list.sort(Integer::compare);

        //获取中间值
        if (list.size() % 2 == 1) {
            return list.get((list.size() >>> 1));
        }

        return (list.get((list.size()-1) >>> 1) + list.get(((list.size()-1) >>> 1) + 1)) >>> 1;


    }
}
