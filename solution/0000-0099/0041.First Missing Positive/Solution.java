package com.solution._0041;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12, 1, -1, 3});
        System.out.println(res);
    }

    /*
    可以将该问题转化为一个桶排序（Bucket Sort）的问题。
    首先，考虑到最小正整数一定在 [1, n+1] 的范围内，因此可以先将小于等于 0 的数都视为无用的数，然后对于大于 0 且小于等于 n 的数，在一个长度
    为 n 的桶中标记出来。其中，桶中的第 i 个位置记录的是 i+1 是否存在于原数组中。
    遍历完整个数组后，再从桶中找出第一个没有被标记的位置，该位置对应的数即为最小正整数。如果所有位置都已经被标记，则说明原数组中包含了 [1, n]
    中的所有正整数，最小未出现正整数为 n+1。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
//        [1, 9, 3, 11, 12, -1, 7, 8],第一个位置放错的地方index=1
        // 查找第一个未被标记的位置
        for (int i = 0; i < n; ++i) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        // 如果所有位置都已被标记，则最小未出现正整数为 n+1
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /*
    基于“位图”（BitMap）
    利用一个长为 n+1 的二进制位图，来记录[1,n]中的数字是否出现过。遍历整个数组时，将 nums[i]-1 对应的二进制位置为1，表示这个数字已经出现
    过了。最后再次遍历整个二进制位图，返回第一个值为0的二进制位所在下标，即为缺失的最小正整数。
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        // 将 nums[i] - 1 对应的二进制位设为 1
        int bitMap = 0;
        for (int num : nums) {
            if (num > 0 && num <= n + 1) {
                bitMap |= 1 << (num - 1);
            }
        }
        // 返回第一个为0的二进制位所在下标
        for (int i = 0; i <= n; i++) {
            if ((bitMap & (1 << i)) == 0) {
                return i + 1;
            }
        }
        return -1;
    }

}
