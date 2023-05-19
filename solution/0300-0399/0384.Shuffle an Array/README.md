# [384. 打乱数组](https://leetcode.cn/problems/shuffle-an-array)

[English Version](/solution/0300-0399/0384.Shuffle%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是&nbsp;<strong>等可能</strong>&nbsp;的。</p>

<p>实现 <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(int[] nums)</code> 使用整数数组 <code>nums</code> 初始化对象</li>
	<li><code>int[] reset()</code> 重设数组到它的初始状态并返回</li>
	<li><code>int[] shuffle()</code> 返回数组随机打乱后的结果</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
<strong>输出</strong>
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

<strong>解释</strong>
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>nums</code> 中的所有元素都是 <strong>唯一的</strong></li>
	<li>最多可以调用 <code>10<sup>4</sup></code> 次 <code>reset</code> 和 <code>shuffle</code></li>
</ul>

## 解法

### **Java**

```java
class Solution {
    private int[] nums;
    private int[] original;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.original = Arrays.copyOf(nums, nums.length);
        this.rand = new Random();
    }

    public int[] reset() {
        nums = Arrays.copyOf(original, original.length);
        return nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < nums.length; ++i) {
            swap(i, i + rand.nextInt(nums.length - i));
        }
        return nums;
    }

    private void swap(int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
```

### **TypeScript**
