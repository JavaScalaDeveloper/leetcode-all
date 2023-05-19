# [533. 孤独像素 II](https://leetcode.cn/problems/lonely-pixel-ii)

[English Version](/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md)

## 题目描述

<p>给你一个大小为 <code>m x n</code> 的二维字符数组 <code>picture</code> ，表示一张黑白图像，数组中的 <code>'B'</code> 表示黑色像素，<code>'W'</code> 表示白色像素。另给你一个整数 <code>target</code> ，请你找出并返回符合规则的 <strong>黑色</strong> 孤独像素的数量。</p>

<p>黑色孤独像素是指位于某一特定位置 <code>(r, c)</code> 的字符 <code>'B'</code> ，其中：</p>

<ul>
	<li>行 <code>r</code> 和列 <code>c</code> 中的黑色像素恰好有 <code>target</code> 个。</li>
	<li>列 <code>c</code> 中所有黑色像素所在的行必须和行 <code>r</code> 完全相同。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/pixel2-1-grid.jpg" style="width: 493px; height: 333px;" />
<pre>
<strong>输入：</strong>picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
<strong>输出：</strong>6
<strong>解释：</strong>所有绿色的 'B' 都是我们所求的像素(第 1 列和第 3 列的所有 'B' )
以行 r = 0 和列 c = 1 的 'B' 为例：
- 规则 1 ，行 r = 0 和列 c = 1 都恰好有 target = 3 个黑色像素 
- 规则 2 ，列 c = 1 的黑色像素分别位于行 0，行 1 和行 2。和行 r = 0 完全相同。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/pixel2-2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>picture[i][j]</code> 为 <code>'W'</code> 或 <code>'B'</code></li>
	<li><code>1 &lt;= target &lt;= min(m, n)</code></li>
</ul>

## 解法

“哈希表”实现。

### **Java**

```java
class Solution {
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        Map<Integer, List<Integer>> cols = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    cols.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                }
            }
        }
        boolean[][] t = new boolean[m][m];
        for (int i = 0; i < m; ++i) {
            for (int k = i; k < m; ++k) {
                t[i][k] = i == k || all(picture[i], picture[k]);
                t[k][i] = t[i][k];
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == target) {
                for (int j = 0; j < n; ++j) {
                    List<Integer> col = cols.get(j);
                    if (col != null && col.size() == target) {
                        boolean check = true;
                        for (int k : col) {
                            check = check && t[i][k];
                        }
                        if (check) {
                            ++res;
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean all(char[] row1, char[] row2) {
        int n = row1.length;
        for (int j = 0; j < n; ++j) {
            if (row1[j] != row2[j]) {
                return false;
            }
        }
        return true;
    }
}
```
