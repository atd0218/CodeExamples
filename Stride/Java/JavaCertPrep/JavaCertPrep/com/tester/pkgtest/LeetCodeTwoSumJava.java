package com.tester.pkgtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeTwoSumJava {
    public static int[] twoSum(int[] nums, int target) {
        int [] answer = new int[2];
        outer:
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    answer[0] = j;
                    answer[1] = i;
                    break outer;
                }
            }
        }
        return answer;
    }

    public static int[] TwoSumHashMap(int[] numbers, int target) {
        //int[] result = new int[2];
        //create a hashmap to store two integers that will end up being the answer
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        //loop through the numbers array looking for two numbers that add up to the target.
        for (int i = 0; i < numbers.length; i++) {
            //
            if (map.containsKey(target - numbers[i])) {
                //result[1] = i;
                //result[0] = map.get(target - numbers[i]);
                return new int[]{i,map.get(target-numbers[i])};
            }
            map.put(numbers[i], i);
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int [] nums = {2, 7, 5, 11};
        int target = 9;

        System.out.println(TwoSumHashMap(nums, target));
    }
}
