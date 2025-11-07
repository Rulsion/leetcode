package csdn.p00086;

import java.util.Arrays;
import java.util.Comparator;

import java.util.Objects;
import java.util.Scanner;

/**
 * 小王在进行游戏大闯关，有一个关卡需要输入一个密码才能通过，密码获得的条件如下：
 * <p>
 * 在一个密码本中，每一页都有一个由26个小写字母组成的若干位密码，每一页的密码不同，需要从这个密码本中寻找这样一个最长的密码，
 * <p>
 * 从它的末尾开始依次去掉一位得到的新密码也在密码本中存在。
 * <p>
 * 请输出符合要求的密码，如果有多个符合要求的密码，则返回字典序最大的密码。
 * <p>
 * 若没有符合要求的密码，则返回空字符串。
 * <p>
 * 输入描述
 * <p>
 * 密码本由一个字符串数组组成，不同元素之间使用空格隔开，每一个元素代表密码本每一页的密码。
 * <p>
 * 输出描述
 * <p>
 * 一个字符串
 * <p>
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * <p>
 * 输入
 * <p>
 * h he hel hell hello
 * <p>
 * 输出
 * <p>
 * hello
 * <p>
 * 示例2 输入输出示例仅供调试，后台判题数据一般不包含示例
 * <p>
 * 输入
 * <p>
 * b ereddred bw bww bwwl bwwlm bwwln
 * <p>
 * 输出
 * <p>
 * bwwln
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(" ");

        System.out.println(getPassword(strs));

    }

    public static String getPassword(String[] arr) {
        //按照长度排序
        Arrays.sort(arr, Comparator.comparingInt(String::length));

        for (int n = arr.length - 1; n >= 0; n--) {
            String password = arr[n];
            boolean allCompareFlag = true;
            for (int m = 1; m < password.length(); m++) {
                String prefix = password.substring(0, m);
                boolean compareFlag = false;
                for (int k = 0; k < n; k++) {
                    if (arr[k].length() > prefix.length()) {

                        break;
                    }
                    if (Objects.equals(arr[k], prefix)) {
                        compareFlag = true;
                        break;
                    }

                }
                if (!compareFlag) {
                    allCompareFlag = false;
                    break;
                }
            }
            if (allCompareFlag){
                return password;
            }
        }


        return "";
    }
}
