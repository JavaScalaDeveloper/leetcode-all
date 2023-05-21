# [2023. 连接后等于目标字符串的字符串对](https://leetcode.cn/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target)

## 题目描述

<p>给你一个 <strong>数字</strong>&nbsp;字符串数组 <code>nums</code>&nbsp;和一个 <strong>数字</strong>&nbsp;字符串 <code>target</code>&nbsp;，请你返回 <code>nums[i] + nums[j]</code>&nbsp;（两个字符串连接）结果等于 <code>target</code>&nbsp;的下标 <code>(i, j)</code>&nbsp;（需满足 <code>i != j</code>）的数目。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = ["777","7","77","77"], target = "7777"
<b>输出：</b>4
<b>解释：</b>符合要求的下标对包括：
- (0, 1)："777" + "7"
- (1, 0)："7" + "777"
- (2, 3)："77" + "77"
- (3, 2)："77" + "77"
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = ["123","4","12","34"], target = "1234"
<b>输出：</b>2
<b>解释：</b>符合要求的下标对包括
- (0, 1)："123" + "4"
- (2, 3)："12" + "34"
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = ["1","1","1"], target = "11"
<b>输出：</b>6
<b>解释：</b>符合要求的下标对包括
- (0, 1)："1" + "1"
- (1, 0)："1" + "1"
- (0, 2)："1" + "1"
- (2, 0)："1" + "1"
- (1, 2)："1" + "1"
- (2, 1)："1" + "1"
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>2 &lt;= target.length &lt;= 100</code></li>
	<li><code>nums[i]</code>&nbsp;和&nbsp;<code>target</code>&nbsp;只包含数字。</li>
	<li><code>nums[i]</code>&nbsp;和&nbsp;<code>target</code>&nbsp;不含有任何前导 0 。</li>
</ul>

## 解法

**方法一：枚举**

遍历数组 `nums`，对于每个i，枚举所有j，如果i \neq j且nums[i] + nums[j] = target，则答案加一。

时间复杂度O(n^2 \times m)，空间复杂度O(1)。其中n和m分别为数组 `nums` 和字符串 `target` 的长度。

**方法二：哈希表**

我们可以用哈希表统计数组 `nums` 中每个字符串出现的次数，然后遍历字符串 `target` 的所有前缀和后缀，如果前缀和后缀都在哈希表中，则答案加上它们出现的次数的乘积。

时间复杂度O(n + m^2)，空间复杂度O(n)。其中n和m分别为数组 `nums` 和字符串 `target` 的长度。

### **Java**

```java
class Solution {
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && target.equals(nums[i] + nums[j])) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int ans = 0;
        for (int i = 1; i < target.length(); ++i) {
            String a = target.substring(0, i);
            String b = target.substring(i);
            int x = cnt.getOrDefault(a, 0);
            int y = cnt.getOrDefault(b, 0);
            if (!a.equals(b)) {
                ans += x * y;
            } else {
                ans += x * (y - 1);
            }
        }
        return ans;
    }
}
```
