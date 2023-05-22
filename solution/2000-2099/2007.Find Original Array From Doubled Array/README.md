# [2007. 从双倍数组中还原原数组](https://leetcode.cn/problems/find-original-array-from-doubled-array)

## 题目描述

<p>一个整数数组&nbsp;<code>original</code>&nbsp;可以转变成一个 <strong>双倍</strong>&nbsp;数组&nbsp;<code>changed</code>&nbsp;，转变方式为将 <code>original</code>&nbsp;中每个元素 <strong>值乘以 2 </strong>加入数组中，然后将所有元素 <strong>随机打乱</strong>&nbsp;。</p>

<p>给你一个数组&nbsp;<code>changed</code>&nbsp;，如果&nbsp;<code>change</code>&nbsp;是&nbsp;<strong>双倍</strong>&nbsp;数组，那么请你返回&nbsp;<code>original</code>数组，否则请返回空数组。<code>original</code>&nbsp;的元素可以以&nbsp;<strong>任意</strong>&nbsp;顺序返回。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>changed = [1,3,4,2,6,8]
<b>输出：</b>[1,3,4]
<b>解释：</b>一个可能的 original 数组为 [1,3,4] :
- 将 1 乘以 2 ，得到 1 * 2 = 2 。
- 将 3 乘以 2 ，得到 3 * 2 = 6 。
- 将 4 乘以 2 ，得到 4 * 2 = 8 。
其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>changed = [6,3,0,1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>changed = [1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：排序 + 计数 + 遍历**

我们先判断数组 `changed` 的长度n是否为奇数，若是，则直接返回空数组。

然后对数组 `changed` 进行排序，并且用哈希表或数组 `cnt` 统计数组 `changed` 中每个元素出现的次数。

接下来遍历数组 `changed`，对于数组 `changed` 中的每个元素x，我们首先判断哈希表 `cnt` 中是否存在x，若不存在，则直接跳过该元素。否则，我们判断 `cnt` 中是否存在x × 2，若不存在，则直接返回空数组。否则，我们将x加入答案数组 `ans` 中，并且将 `cnt` 中x和x × 2的出现次数分别减1。

遍历结束后，我们判断答案数组 `ans` 的长度是否为\frac{n}{2}，若是，则返回 `ans`，否则返回空数组。

时间复杂度O(n × log n)，空间复杂度O(n)。其中n为数组 `changed` 的长度。

### **Java**

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[] {};
        }
        Arrays.sort(changed);
        int[] cnt = new int[changed[n - 1] + 1];
        for (int x : changed) {
            ++cnt[x];
        }
        int[] ans = new int[n / 2];
        int i = 0;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            if (x * 2 >= cnt.length || cnt[x * 2] <= 0) {
                return new int[] {};
            }
            ans[i++] = x;
            cnt[x]--;
            cnt[x * 2]--;
        }
        return i == n / 2 ? ans : new int[] {};
    }
}
```
