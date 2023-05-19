# [1769. 移动所有球到每个盒子所需的最小操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box)

[English Version](/solution/1700-1799/1769.Minimum%20Number%20of%20Operations%20to%20Move%20All%20Balls%20to%20Each%20Box/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个盒子。给你一个长度为 <code>n</code> 的二进制字符串 <code>boxes</code> ，其中 <code>boxes[i]</code> 的值为 <code>'0'</code> 表示第 <code>i</code> 个盒子是 <strong>空</strong> 的，而 <code>boxes[i]</code> 的值为 <code>'1'</code> 表示盒子里有 <strong>一个</strong> 小球。</p>

<p>在一步操作中，你可以将 <strong>一个</strong> 小球从某个盒子移动到一个与之相邻的盒子中。第 <code>i</code> 个盒子和第 <code>j</code> 个盒子相邻需满足 <code>abs(i - j) == 1</code> 。注意，操作执行后，某些盒子中可能会存在不止一个小球。</p>

<p>返回一个长度为 <code>n</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是将所有小球移动到第 <code>i</code> 个盒子所需的 <strong>最小</strong> 操作数。</p>

<p>每个 <code>answer[i]</code> 都需要根据盒子的 <strong>初始状态</strong> 进行计算。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>boxes = "110"
<strong>输出：</strong>[1,1,3]
<strong>解释：</strong>每个盒子对应的最小操作数如下：
1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>boxes = "001011"
<strong>输出：</strong>[11,8,5,4,3,4]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == boxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>boxes[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

**方法一：预处理 + 枚举**

我们可以预处理出每个位置 $i$ 左边的小球移动到 $i$ 的操作数，记为 $left[i]$；每个位置 $i$ 右边的小球移动到 $i$ 的操作数，记为 $right[i]$。那么答案数组的第 $i$ 个元素就是 $left[i] + right[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `boxes` 的长度。

我们还可以进一步优化空间复杂度，只用一个答案数组 $ans$ 以及若干个变量即可。

时间复杂度 $O(n)$，忽略答案数组的空间消耗，空间复杂度 $O(1)$。其中 $n$ 为 `boxes` 的长度。

### **Java**

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1, cnt = 0; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++cnt;
            }
            left[i] = left[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0; i >= 0; --i) {
            if (boxes.charAt(i + 1) == '1') {
                ++cnt;
            }
            right[i] = right[i + 1] + cnt;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = left[i] + right[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        for (int i = 1, cnt = 0; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++cnt;
            }
            ans[i] = ans[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0, s = 0; i >= 0; --i) {
            if (boxes.charAt(i + 1) == '1') {
                ++cnt;
            }
            s += cnt;
            ans[i] += s;
        }
        return ans;
    }
}
```

### **TypeScript**

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *minOperations(char *boxes, int *returnSize) {
    int n = strlen(boxes);
    int *left = malloc(sizeof(int) * n);
    int *right = malloc(sizeof(int) * n);
    memset(left, 0, sizeof(int) * n);
    memset(right, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        left[i] = left[i - 1] + count;
    }
    for (int i = n - 2, count = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        right[i] = right[i + 1] + count;
    }
    int *ans = malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        ans[i] = left[i] + right[i];
    }
    free(left);
    free(right);
    *returnSize = n;
    return ans;
}
```

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *minOperations(char *boxes, int *returnSize) {
    int n = strlen(boxes);
    int *ans = malloc(sizeof(int) * n);
    memset(ans, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        ans[i] = ans[i - 1] + count;
    }
    for (int i = n - 2, count = 0, sum = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        sum += count;
        ans[i] += sum;
    }
    *returnSize = n;
    return ans;
}
```
