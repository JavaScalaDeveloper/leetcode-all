# [面试题 17. 打印从 1 到最大的 n 位数](https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

## 题目描述

<p>输入数字 <code>n</code>，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> n = 1
<strong>输出:</strong> [1,2,3,4,5,6,7,8,9]
</pre>

<p>&nbsp;</p>

<p>说明：</p>

<ul>
	<li>用返回一个整数列表来代替打印</li>
	<li>n 为正整数</li>
</ul>

## 解法

**方法一：模拟**

直接根据题意模拟即可。

时间复杂度 $O(10^n)$，空间复杂度 $O(1)$。

如果 $n$ 的值比较大，那么直接使用整数会溢出，因此可以使用字符串来模拟，参考以下代码中的 `print()` 函数。

<!-- tabs:start -->

### **Python3**



### **Java**

```java
class Solution {
    public int[] printNumbers(int n) {
        int[] ans = new int[(int) Math.pow(10, n) - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = i + 1;
        }
        return ans;
    }

    private StringBuilder s = new StringBuilder();
    private List<String> ans = new ArrayList<>();

    public List<String> print(int n) {
        for (int i = 1; i <= n; ++i) {
            dfs(0, i);
        }
        return ans;
    }

    private void dfs(int i, int j) {
        if (i == j) {
            ans.add(s.toString());
            return;
        }
        int k = i > 0 ? 0 : 1;
        for (; k < 10; ++k) {
            s.append(k);
            dfs(i + 1, j);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
```

















### **...**

```

```


