# [面试题 17.09. 第 k 个数](https://leetcode.cn/problems/get-kth-magic-number-lcci)

[中文文档](/lcci/17.09.Get%20Kth%20Magic%20Number/README.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入: </strong>k = 5

<strong>输出: </strong>9

</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（小根堆）**

用一个小根堆维护当前最小的数，每次取出最小的数，然后乘以 $3$, $5$, $7$，分别加入堆中，直到取出第 $k$ 个数。

时间复杂度 $O(k\times \log k)$，空间复杂度 $O(k)$。

**方法二：动态规划**

方法一的做法足以通过本题，但如果在面试中，面试官可能要求我们实现一个复杂度更低的算法。因此，我们有必要掌握一种更优的算法。

我们定义数组 $dp$，其中 $dp[i]$ 表示第 $i$ 个数，答案即为 $dp[k]$。

定义三个指针 $p_3$, $p_5$, $p_7$，表示下一个数是当前指针指向的数乘以对应的质因数，初始值都为 $1$。

当 $2\le i \le k$ 时，令 $dp[i] = \min(dp[p_3]\times 3, dp[p_5]\times 5, dp[p_7]\times 7)$，然后分别比较 $dp[i]$ 和 $dp[p_3]\times 3, dp[p_5]\times 5, dp[p_7]\times 7$，如果相等，则将对应的指针加 $1$。

时间复杂度 $O(k)$，空间复杂度 $O(k)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int[] FACTORS = new int[] {3, 5, 7};

    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        Set<Long> vis = new HashSet<>();
        q.offer(1L);
        vis.add(1L);
        while (--k > 0) {
            long cur = q.poll();
            for (int f : FACTORS) {
                long nxt = cur * f;
                if (!vis.contains(nxt)) {
                    q.offer(nxt);
                    vis.add(nxt);
                }
            }
        }
        long ans = q.poll();
        return (int) ans;
    }
}
```

```java
class Solution {
    public int getKthMagicNumber(int k) {
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 1);
        int p3 = 1, p5 = 1, p7 = 1;
        for (int i = 2; i <= k; ++i) {
            int a = dp[p3] * 3, b = dp[p5] * 5, c = dp[p7] * 7;
            int v = Math.min(Math.min(a, b), c);
            dp[i] = v;
            if (v == a) {
                ++p3;
            }
            if (v == b) {
                ++p5;
            }
            if (v == c) {
                ++p7;
            }
        }
        return dp[k];
    }
}
```













### **C**

```c
#define min(a,b) (((a) < (b)) ? (a) : (b))

int getKthMagicNumber(int k) {
    int *dp = (int *) malloc(sizeof(int) * k);
    dp[0] = 1;
    int index[3] = {0, 0, 0};
    for (int i = 1; i < k; i++) {
        int a = dp[index[0]] * 3;
        int b = dp[index[1]] * 5;
        int c = dp[index[2]] * 7;
        int num = min(a, min(b, c));
        dp[i] = num;
        if (a == num) {
            index[0]++;
        }
        if (b == num) {
            index[1]++;
        }
        if (c == num) {
            index[2]++;
        }
    }
    int res = dp[k - 1];
    free(dp);
    return res;
}
```

### **TypeScript**







### **...**

```

```


