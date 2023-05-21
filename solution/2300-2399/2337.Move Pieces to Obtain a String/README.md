# [2337. 移动片段得到字符串](https://leetcode.cn/problems/move-pieces-to-obtain-a-string)

## 题目描述

<p>给你两个字符串 <code>start</code> 和 <code>target</code> ，长度均为 <code>n</code> 。每个字符串 <strong>仅</strong> 由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成，其中：</p>

<ul>
	<li>字符 <code>'L'</code> 和 <code>'R'</code> 表示片段，其中片段 <code>'L'</code> 只有在其左侧直接存在一个 <strong>空位</strong> 时才能向 <strong>左</strong> 移动，而片段 <code>'R'</code> 只有在其右侧直接存在一个 <strong>空位</strong> 时才能向 <strong>右</strong> 移动。</li>
	<li>字符 <code>'_'</code> 表示可以被 <strong>任意</strong> <code>'L'</code> 或 <code>'R'</code> 片段占据的空位。</li>
</ul>

<p>如果在移动字符串 <code>start</code> 中的片段任意次之后可以得到字符串 <code>target</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>start = "_L__R__R_", target = "L______RR"
<strong>输出：</strong>true
<strong>解释：</strong>可以从字符串 start 获得 target ，需要进行下面的移动：
- 将第一个片段向左移动一步，字符串现在变为 "<strong>L</strong>___R__R_" 。
- 将最后一个片段向右移动一步，字符串现在变为 "L___R___<strong>R</strong>" 。
- 将第二个片段向右移动散步，字符串现在变为 "L______<strong>R</strong>R" 。
可以从字符串 start 得到 target ，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>start = "R_L_", target = "__LR"
<strong>输出：</strong>false
<strong>解释：</strong>字符串 start 中的 'R' 片段可以向右移动一步得到 "_<strong>R</strong>L_" 。
但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>start = "_R", target = "R_"
<strong>输出：</strong>false
<strong>解释：</strong>字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == start.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>start</code> 和 <code>target</code> 由字符 <code>'L'</code>、<code>'R'</code> 和 <code>'_'</code> 组成</li>
</ul>

## 解法

**方法一：双指针**

替换操作实际上是将 `L` 往左移动（`L` 左边为 `_` 时才能移动），`R` 往右移动（`R` 右边是 `_` 时才能移动），但 `L` 无法穿过 `R`。所以，如果去掉 `start` 和 `target` 中的所有 `_`，剩下的字符应该是相同的，否则返回 `false`。

双指针遍历 `start` 和 `target`：

-   如果当前字符为 `L` 且i\lt j，那么这个 `L` 无法向右移动，返回 `false`；
-   如果当前字符为 `R` 且i\gt j，那么这个 `R` 无法向左移动，返回 `false`。

如果双指针均遍历到末尾，返回 `true`。

时间复杂度O(n)，其中n表示字符串 `start` 或 `target` 的长度。

相似题目：[777. 在 LR 字符串中交换相邻字符](/solution/0700-0799/0777.Swap%20Adjacent%20in%20LR%20String/README.md)

### **Java**

```java
class Solution {
    public boolean canChange(String start, String target) {
        List<int[]> a = f(start);
        List<int[]> b = f(target);
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            int[] x = a.get(i);
            int[] y = b.get(i);
            if (x[0] != y[0]) {
                return false;
            }
            if (x[0] == 1 && x[1] < y[1]) {
                return false;
            }
            if (x[0] == 2 && x[1] > y[1]) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> f(String s) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'L') {
                res.add(new int[] {1, i});
            } else if (s.charAt(i) == 'R') {
                res.add(new int[] {2, i});
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (true) {
            while (i < n && start.charAt(i) == '_') {
                ++i;
            }
            while (j < n && target.charAt(j) == '_') {
                ++j;
            }
            if (i == n && j == n) {
                return true;
            }
            if (i == n || j == n || start.charAt(i) != target.charAt(j)) {
                return false;
            }
            if (start.charAt(i) == 'L' && i < j || start.charAt(i) == 'R' && i > j) {
                return false;
            }
            ++i;
            ++j;
        }
    }
}
```
