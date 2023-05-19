# [673. 最长递增子序列的个数](https://leetcode.cn/problems/number-of-longest-increasing-subsequence)

[English Version](/solution/0600-0699/0673.Number%20of%20Longest%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个未排序的整数数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，&nbsp;<em>返回最长递增子序列的个数</em>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;这个数列必须是 <strong>严格</strong> 递增的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,3,5,4,7]
<strong>输出:</strong> 2
<strong>解释:</strong> 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [2,2,2,2,2]
<strong>输出:</strong> 5
<strong>解释:</strong> 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

这是[最长递增子序列](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)的变形题。

**方法一：动态规划**

除了原有的 `dp` 数组之外，另加了 `cnt` 数组记录以 `nums[i]` 结尾的最长子序列的个数。

时间复杂度 $O(n^2)$。

**方法二：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

本题我们使用树状数组 `tree[x]` 来维护以 x 结尾的最长上升子序列的长度，以及该长度对应的子序列个数。

时间复杂度 $O(n\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, ans = 0, n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        int n = m.size();
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int v : nums) {
            int x = m.get(v);
            int[] t = tree.query(x - 1);
            tree.update(x, t[0] + 1, Math.max(t[1], 1));
        }
        return tree.query(n)[1];
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;
    private int[] d;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        d = new int[n + 1];
    }

    public void update(int x, int val, int cnt) {
        while (x <= n) {
            if (c[x] < val) {
                c[x] = val;
                d[x] = cnt;
            } else if (c[x] == val) {
                d[x] += cnt;
            }
            x += lowbit(x);
        }
    }

    public int[] query(int x) {
        int val = 0;
        int cnt = 0;
        while (x > 0) {
            if (val < c[x]) {
                val = c[x];
                cnt = d[x];
            } else if (val == c[x]) {
                cnt += d[x];
            }
            x -= lowbit(x);
        }
        return new int[] {val, cnt};
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

















### **...**

```

```


