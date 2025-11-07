package csdn.p00088;

/**
 * AI识别到面板上有N (1 ≤N ≤ 100) 个指示灯，灯大小一样，任意两个之间无重叠。由于AI识别误差，每次别到的指示灯位置可能有差异，以4个坐标值描述AI识别的指示灯的大小和位置(左上角x1,y1，右下角x2,y2)请输出先行后列排序的指示灯的编号，排序规则:<br/>
 * 1.每次在尚未排序的灯中挑选最高的灯作为的基准灯<br/>
 * 2.找出和基准灯属于同一行所有的灯进行排序。两个灯高低偏差不超过灯半径算同一行(即两个灯坐标的差灯高度的一半)。<br/>
 * 输入描述<br/>
 * 第一行为N，表示灯的个数<br/>
 * 接下来N行，每行为1个灯的坐标信息，格式为:<br/>
 * 编号x1 y1 x2 y2<br/>
 * 1：编号全局唯一<br/>
 * 2：1<编号≤100<br/>
 * 3：0≤x1 < x2≤1000<br/>
 * 4：0≤y1 < y2 ≤ 1000<br/>
 * <p>
 * 输出描述<br/>
 * 排序后的编号列表，编号之间以空格分隔<br/>
 * 示例1：<br/>
 * <p>
 * 输入<br/>
 * 5<br/>
 * 1 0 0 2 2<br/>
 * 2 6 1 8 3<br/>
 * 3 3 2 5 4<br/>
 * 5 5 4 7 6<br/>
 * 4 0 4 2 6<br/>
 * 输出<br/>
 * 1 2 3 4 5
 */

import java.util.*;

public class Main {
    static class Node {
        int id;
        int x1, y1, x2, y2;

        Node(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            int id = input.nextInt();
            int x1 = input.nextInt();
            int y1 = input.nextInt();
            int x2 = input.nextInt();
            int y2 = input.nextInt();
            a[i] = new Node(id, x1, y1, x2, y2);
        }

        Arrays.sort(a, Comparator.comparingInt(node -> node.y1));

        int l = 0;
        for (int i = 1; i < n; i++) {
            // 找到与基准灯在同一行的所有灯
            if (a[i].y1 - a[l].y1 <= (a[l].y2 - a[l].y1) / 2)
                continue;
            // 对这些灯进行排序
            Arrays.sort(a, l, i, Comparator.comparingInt(node -> node.x1));
            // 更新基准灯
            l = i;
        }

        // 最后一个基准灯及其同行灯进行排序
        Arrays.sort(a, l, n, Comparator.comparingInt(node -> node.x1));

        for (int i = 0; i < n; i++) {
            System.out.print(a[i].id + " ");
        }
    }
}
