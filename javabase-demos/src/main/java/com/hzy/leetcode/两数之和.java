package com.hzy.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 两数之和 {
    public static int[] getTargetNumsIndex(int[] nums, int target) {
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (valueIndexMap.containsKey(num2)) {
                return new int[]{valueIndexMap.get(num2), i};
            } else {
                valueIndexMap.put(num1, i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {11, 4, 7, 9, 77, 65, 8, 4, 567, 433, 56, 65};
        int target = 8;
        int[] result = getTargetNumsIndex(nums, target);
        if (result == null) {
            System.out.println(result);
        } else {
            Arrays.stream(result).forEach(x -> System.out.println(x));
        }
    }
}
