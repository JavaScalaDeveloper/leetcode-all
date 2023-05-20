# [846. 一手顺子](https://leetcode.cn/problems/hand-of-straights)

## 题目描述

<p>Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 <code>groupSize</code> ，并且由 <code>groupSize</code> 张连续的牌组成。</p>

<p>给你一个整数数组 <code>hand</code> 其中 <code>hand[i]</code> 是写在第 <code>i</code> 张牌，和一个整数 <code>groupSize</code> 。如果她可能重新排列这些牌，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
<strong>输出：</strong>true
<strong>解释：</strong>Alice 手中的牌可以被重新排列为 <code>[1,2,3]，[2,3,4]，[6,7,8]</code>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,4,5], groupSize = 4
<strong>输出：</strong>false
<strong>解释：</strong>Alice 手中的牌无法被重新排列成几个大小为 4 的组。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hand.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= hand[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= groupSize &lt;= hand.length</code></li>
</ul>

<p><strong>注意：</strong>此题目与 1296 重复：<a href="https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/" target="_blank">https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/</a></p>

## 解法

**方法一：哈希表 + 排序**

我们先用哈希表 `cnt` 统计数组 `hand` 中每个数字出现的次数，然后对数组 `hand` 进行排序。

接下来，我们遍历数组 `hand`，对于数组中的每个数字 $v$，如果 $v$ 在哈希表 `cnt` 中出现的次数不为 $0$，则我们枚举 $v$ 到 $v+groupSize-1$ 的每个数字，如果这些数字在哈希表 `cnt` 中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果减 $1$ 后这些数字的出现次数为 $0$，则我们在哈希表 `cnt` 中删除这些数字。否则说明无法将数组划分成若干个长度为 $groupSize$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $groupSize$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `hand` 的长度。

**方法二：有序集合**

我们也可以使用有序集合统计数组 `hand` 中每个数字出现的次数。

接下来，循环取出有序集合中的最小值 $v$，然后枚举 $v$ 到 $v+groupSize-1$ 的每个数字，如果这些数字在有序集合中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果出现次数减 $1$ 后为 $0$，则将该数字从有序集合中删除，否则说明无法将数组划分成若干个长度为 $groupSize$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $groupSize$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `hand` 的长度。

### **Java**

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : hand) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(hand);
        for (int v : hand) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + groupSize; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    cnt.put(x, cnt.get(x) - 1);
                    if (cnt.get(x) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : hand) {
            tm.put(h, tm.getOrDefault(h, 0) + 1);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + groupSize; ++i) {
                if (!tm.containsKey(i)) {
                    return false;
                }
                if (tm.get(i) == 1) {
                    tm.remove(i);
                } else {
                    tm.put(i, tm.get(i) - 1);
                }
            }
        }
        return true;
    }
}
```
