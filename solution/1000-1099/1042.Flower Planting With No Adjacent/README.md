# [1042. 不邻接植花](https://leetcode.cn/problems/flower-planting-with-no-adjacent)

## 题目描述

<p>有 <code>n</code> 个花园，按从&nbsp;<code>1</code>&nbsp;到 <code>n</code> 标记。另有数组 <code>paths</code> ，其中 <code>paths[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;描述了花园&nbsp;<code>x<sub>i</sub></code> 到花园&nbsp;<code>y<sub>i</sub></code> 的双向路径。在每个花园中，你打算种下四种花之一。</p>

<p>另外，所有花园 <strong>最多</strong> 有 <strong>3</strong> 条路径可以进入或离开.</p>

<p>你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。</p>

<p><em>以数组形式返回 <strong>任一</strong> 可行的方案作为答案&nbsp;<code>answer</code>，其中&nbsp;<code>answer[i]</code>&nbsp;为在第&nbsp;<code>(i+1)</code>&nbsp;个花园中种植的花的种类。花的种类用 &nbsp;1、2、3、4 表示。保证存在答案。</em></p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, paths = [[1,2],[2,3],[3,1]]
<strong>输出：</strong>[1,2,3]
<strong>解释：</strong>
花园 1 和 2 花的种类不同。
花园 2 和 3 花的种类不同。
花园 3 和 1 花的种类不同。
因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, paths = [[1,2],[3,4]]
<strong>输出：</strong>[1,2,1,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
<strong>输出：</strong>[1,2,3,4]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li>每个花园 <strong>最多</strong> 有 <strong>3</strong> 条路径可以进入或离开</li>
</ul>

## 解法

**方法一：枚举**

我们先根据数组paths构建图g，其中g[x]表示与花园x相邻的花园列表。

接下来，对于每个花园x，我们先找出与x相邻的花园y，并将y花园中种植的花的种类标记为已使用。然后我们从花的种类1开始枚举，直到找到一个未被使用的花的种类c，将c标记为x花园中种植的花的种类，然后继续枚举下一个花园。

枚举结束后，返回答案即可。

时间复杂度O(n + m)，空间复杂度O(n + m)。其中n和m分别是花园的数量和路径的数量。

### **Java**

```java
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        int[] ans = new int[n];
        boolean[] used = new boolean[5];
        for (int x = 0; x < n; ++x) {
            Arrays.fill(used, false);
            for (int y : g[x]) {
                used[ans[y]] = true;
            }
            for (int c = 1; c < 5; ++c) {
                if (!used[c]) {
                    ans[x] = c;
                    break;
                }
            }
        }
        return ans;
    }
}
```
