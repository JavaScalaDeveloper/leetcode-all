package com.solution._0004;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {1, 2, 4, 6, 9};
        int[] arr2 = {2, 3, 5, 10, 14, 22, 23};
        double res = solution.findMedianSortedArrays(arr1, arr2);
        System.out.println(res);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /*
    对于两个数组，分别在数组中查找第 k/2k/2 小的数，比较这两个数的大小。如果两个数相等，直接返回即可；否则，将较小的那一个数所在数组的前 k/2
    个数全部舍弃，继续在剩余的数中寻找第 k-k/2 小的数。这个过程可以使用递归实现，当一个数组中搜索完毕时，说明该数组的所有数都不可能是第 k 小
    的数，此时直接返回另一个数组的第 k-(已经排除的数目) 小的数即可。

    在具体实现中，我们设置了两个指针 mid1 和 mid2 分别表示 nums1 和 nums2 数组中第 k/2 小的数的下标，然后获取 mid1 和 mid2 对应的数，
    并比较它们的大小。如果 nums1[mid1] < nums2[mid2]，说明 nums1 中前 mid1+1 个数都不可能是第 k 小的数，因此更新起始位置 start1 为
    mid1+1，并递归在 nums1[mid1+1...m-1] 和 nums2[start2...n-1] 中寻找第 k-k/2 小的数；否则更新起始位置 start2 为 mid2+1，并递归
    在 nums1[start1...m-1] 和 nums2[mid2+1...n-1] 中寻找第 k-k/2 小的数。

    终止条件有三种：k=1、nums1 或 nums2 长度为 0 以及 start1 或 start2 超过数组长度。对于前两种情况，直接返回两个数组中较小的那个数即可；
    对于后一种情况，说明一个数组已经搜索完毕，直接返回另一个数组中第 k-(已经排除的数目) 小的数即可。
     */
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        }
        return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }
}
