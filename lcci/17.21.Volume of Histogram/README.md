# [面试题 17.21. 直方图的水量](https://leetcode.cn/problems/volume-of-histogram-lcci)

[English Version](/lcci/17.21.Volume%20of%20Histogram/README_EN.md)

## 题目描述


<p>给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。</p>

![](./images/rainwatertrap.png)

<p><small>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。&nbsp;<strong>感谢 Marcos</strong> 贡献此图。</small></p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出:</strong> 6</pre>

## 解法

动态规划法。

对于下标 i，水能达到的最大高度等于下标 i 左右两侧的最大高度的最小值，再减去 `height[i]` 就能得到当前柱子所能存的水量。

同[42. 接雨水](/solution/0000-0099/0042.Trapping%20Rain%20Water/README.md)

### **Java**

```java
class Solution {
    public int trap(int[] height) {
        int n;
        if ((n = height.length) < 3) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}
```
