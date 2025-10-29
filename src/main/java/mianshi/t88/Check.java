package mianshi.t88;

import com.alibaba.fastjson.JSONObject;

/**
 * leetcode 面试算法150题：
 *
 * @see <a href="https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150">88.合并两个有序数组</a>
 */
public class Check {
    public static void main(String[] args) {
        // fastSort(new int[]{3,2,1},0,2);
        merge1(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
    }

    //合并之后再排序
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        //合并数组
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        //使用快排nums1
        fastSort(nums1, 0, nums1.length - 1);
        System.out.printf(JSONObject.toJSONString(nums1));
    }


    public static void fastSort(int[] nums, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return;
        }
        int startTmp = startIndex, endTmp = endIndex;

        //选取第一个元素为基准元素
        int base = nums[startTmp];
        int baseIndex = startTmp;
        for (; ; ) {
            if (startTmp >= endTmp) {
                break;
            }

            for (; ; ) {
                if (baseIndex >= endTmp) {
                    break;
                }
                //比较base元素和右侧元素
                if (base > nums[endTmp]) {
                    // 如果右侧元素比base小
                    // 替换右侧坐标元素到左侧坐标位置
                    nums[baseIndex] = nums[endTmp];
                    baseIndex = endTmp;
                    break;
                }
                //右侧指针左移
                endTmp--;

            }
            for (; ; ) {
                if (baseIndex <= startTmp) {
                    break;
                }
                //比较base元素和左侧元素
                if (base < nums[startTmp]) {
                    // 如果左侧元素比base大
                    // 替换左侧坐标元素到右侧坐标位置
                    nums[baseIndex] = nums[startTmp];
                    baseIndex = startTmp;
                    break;
                }
                //左侧指针右移
                startTmp++;
            }
        }
        // 将中间元素替换为基准元素
        nums[baseIndex] = base;
        //一轮快排完成，递归左右数组快排
        fastSort(nums, startIndex, baseIndex - 1);
        fastSort(nums, baseIndex + 1, endIndex);

    }
}
