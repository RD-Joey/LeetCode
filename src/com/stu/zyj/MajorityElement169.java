package com.stu.zyj;

import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement169 {
    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length >>> 1];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) map.put(i, map.getOrDefault(i, 0) + 1);
        int n = nums.length >>> 1;
        for (int i : map.keySet()) if (map.get(i) > n) return i;
        return 0;
    }
}
