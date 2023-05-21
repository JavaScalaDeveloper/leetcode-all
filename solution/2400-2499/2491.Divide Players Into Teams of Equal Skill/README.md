# [2491. 划分技能点相等的团队](https://leetcode.cn/problems/divide-players-into-teams-of-equal-skill)

## 题目描述

<p>给你一个正整数数组 <code>skill</code> ，数组长度为 <strong>偶数</strong> <code>n</code> ，其中 <code>skill[i]</code> 表示第 <code>i</code> 个玩家的技能点。将所有玩家分成 <code>n / 2</code> 个 <code>2</code> 人团队，使每一个团队的技能点之和 <strong>相等</strong> 。</p>

<p>团队的 <strong>化学反应</strong> 等于团队中玩家的技能点 <strong>乘积</strong> 。</p>

<p>返回所有团队的 <strong>化学反应</strong> 之和，如果无法使每个团队的技能点之和相等，则返回 <code>-1</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>skill = [3,2,5,1,3,4]
<strong>输出：</strong>22
<strong>解释：</strong>
将玩家分成 3 个团队 (1, 5), (2, 4), (3, 3) ，每个团队的技能点之和都是 6 。
所有团队的化学反应之和是 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>skill = [3,4]
<strong>输出：</strong>12
<strong>解释：</strong>
两个玩家形成一个团队，技能点之和是 7 。
团队的化学反应是 3 * 4 = 12 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>skill = [1,1,2,3]
<strong>输出：</strong>-1
<strong>解释：</strong>
无法将玩家分成每个团队技能点都相等的若干个 2 人团队。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= skill.length &lt;= 10<sup>5</sup></code></li>
	<li><code>skill.length</code> 是偶数</li>
	<li><code>1 &lt;= skill[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：排序**

要使得所有2人团队的技能点相等，最小值一定需要跟最大值匹配。因此，我们将数组 `skill` 排序，然后用双指针i和j分别指向数组的首位，两两匹配，判断其和是否均为同一个数。

若不是，说明技能点无法相等，直接返回-1，否则，将化学反应累加到答案中。

遍历结束，返回答案即可。

时间复杂度O(n × log n)，空间复杂度O(log n)。其中n是数组 `skill` 的长度。

**方法二：计数**

时间复杂度O(n)，空间复杂度O(n)。其中n是数组 `skill` 的长度。

### **Java**

```java
class Solution {
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int n = skill.length;
        int t = skill[0] + skill[n - 1];
        long ans = 0;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            if (skill[i] + skill[j] != t) {
                return -1;
            }
            ans += (long) skill[i] * skill[j];
        }
        return ans;
    }
}
```

```java
class Solution {
    public long dividePlayers(int[] skill) {
        int s = Arrays.stream(skill).sum();
        int m = skill.length >> 1;
        if (s % m != 0) {
            return -1;
        }
        int t = s / m;
        int[] d = new int[1010];
        long ans = 0;
        for (int v : skill) {
            if (d[t - v] > 0) {
                ans += (long) v * (t - v);
                --d[t - v];
                --m;
            } else {
                ++d[v];
            }
        }
        return m == 0 ? ans : -1;
    }
}
```
