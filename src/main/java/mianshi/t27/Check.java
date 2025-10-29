package mianshi.t27;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * leetcode 面试算法150题：
 *
 * @see <a href="https://leetcode.cn/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150">27.移除元素</a>
 */
public class Check {
    public static void main(String[] args) {

        System.out.println( removeElement(new int[]{3,2,2,3},3));
    }

    public static int removeElement(int[] nums, int val) {
        int base = 0;
        for (int i = 0;i<nums.length;i++){
            if (nums[i]!=val){

                nums[base] = nums[i];

                base++;
            }

        }

        System.out.println(JSONObject.toJSONString(nums));
        return base;
    }
}
