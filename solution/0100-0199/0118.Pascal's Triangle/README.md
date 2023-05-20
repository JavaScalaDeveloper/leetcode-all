# [118. 杨辉三角](https://leetcode.cn/problems/pascals-triangle)

## 题目描述

<p>给定一个非负整数 <em><code>numRows</code>，</em>生成「杨辉三角」的前 <em><code>numRows</code> </em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0118.Pascal%27s%20Triangle/images/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> numRows = 5
<strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> numRows = 1
<strong>输出:</strong> [[1]]
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= numRows <= 30</code></li>
</ul>

## 解法

先设置每一行首尾元素为 1，其它元素为 0。然后根据杨辉三角，设置每一行其它元素即可。

### **Java**

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < i + 1; ++j) {
                int v = j == 0 || j == i ? 1 : ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1);
                t.add(v);
            }
            ans.add(t);
        }
        return ans;
    }
}
```
