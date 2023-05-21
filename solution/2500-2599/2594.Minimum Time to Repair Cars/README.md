# [2594. 修车的最少时间](https://leetcode.cn/problems/minimum-time-to-repair-cars)

## 题目描述

<p>给你一个整数数组&nbsp;<code>ranks</code>&nbsp;，表示一些机械工的 <strong>能力值</strong>&nbsp;。<code>ranks<sub>i</sub></code> 是第 <code>i</code> 位机械工的能力值。能力值为&nbsp;<code>r</code>&nbsp;的机械工可以在&nbsp;<code>r * n<sup>2</sup></code>&nbsp;分钟内修好&nbsp;<code>n</code>&nbsp;辆车。</p>

<p>同时给你一个整数&nbsp;<code>cars</code>&nbsp;，表示总共需要修理的汽车数目。</p>

<p>请你返回修理所有汽车&nbsp;<strong>最少</strong>&nbsp;需要多少时间。</p>

<p><strong>注意：</strong>所有机械工可以同时修理汽车。</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>ranks = [4,2,3,1], cars = 10
<b>输出：</b>16
<b>解释：</b>
- 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
- 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
- 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
- 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
16 分钟是修理完所有车需要的最少时间。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>ranks = [5,1,8], cars = 6
<b>输出：</b>16
<b>解释：</b>
- 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
- 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
- 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
16 分钟时修理完所有车需要的最少时间。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= ranks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 100</code></li>
	<li><code>1 &lt;= cars &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：二分查找**

我们注意到，修车时间越长，修理的汽车数目也越多。因此，我们可以将修车时间作为二分查找的目标，二分查找修车时间的最小值。

我们定义二分查找的左右边界分别为left=0,right=ranks[0] \times cars \times cars。接下来二分枚举修车时间mid，每个机械工可以修理的汽车数目为\lfloor \sqrt{\frac{mid}{r}} \rfloor，其中\lfloor x \rfloor表示向下取整。如果修理的汽车数目大于等于cars，则说明修车时间mid可行，我们将右边界缩小至mid，否则将左边界增大至mid+1。

最终，我们返回左边界即可。

时间复杂度(n \times \log n)，空间复杂度O(1)。其中n为机械工的数量。

### **Java**

```java
class Solution {
    public long repairCars(int[] ranks, int cars) {
        long left = 0, right = 1L * ranks[0] * cars * cars;
        while (left < right) {
            long mid = (left + right) >> 1;
            long cnt = 0;
            for (int r : ranks) {
                cnt += Math.sqrt(mid / r);
            }
            if (cnt >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```
