# [1952. 三除数](https://leetcode.cn/problems/three-divisors)

## 题目描述

<p>给你一个整数 <code>n</code> 。如果 <code>n</code> <strong>恰好有三个正除数</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code> 。</p>

<p>如果存在整数 <code>k</code> ，满足 <code>n = k * m</code> ，那么整数 <code>m</code> 就是 <code>n</code> 的一个 <strong>除数</strong> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>false
<strong>解释：</strong>2 只有两个除数：1 和 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>true
<strong>解释：</strong>4 有三个除数：1、2 和 4 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：枚举**

一个数n一定有1和n两个正除数，因此只需要枚举2到n-1之间的数，看它们是否是n的正除数即可，是则累加计数器，最后判断计数器是否为1即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为给定的整数。

**方法二：枚举优化**

我们可以枚举1到\sqrt{n}之间的数i，如果n能被i整除，并且\frac{n}{i}不等于i，那么计数器累加2，否则计数器累加1。最后判断计数器是否为3即可。

时间复杂度O(\sqrt{n})，空间复杂度O(1)。其中n为给定的整数。

### **Java**

```java
class Solution {
    public boolean isThree(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

```java
class Solution {
    public boolean isThree(int n) {
        int cnt = 0;
        for (int i = 1; i <= n / i; ++i) {
            if (n % i == 0) {
                cnt += n / i == i ? 1 : 2;
            }
        }
        return cnt == 3;
    }
}
```
