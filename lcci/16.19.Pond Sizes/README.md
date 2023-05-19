# [面试题 16.19. 水域大小](https://leetcode.cn/problems/pond-sizes-lcci)

[English Version](/lcci/16.19.Pond%20Sizes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你有一个用于表示一片土地的整数矩阵<code>land</code>，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
[
  [0,2,1,0],
  [0,1,0,1],
  [1,1,0,1],
  [0,1,0,1]
]
<strong>输出：</strong> [1,2,4]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < len(land) <= 1000</code></li>
<li><code>0 < len(land[i]) <= 1000</code></li>
</ul>

## 解法

并查集。

并查集模板：

模板 1——朴素并查集：

模板 2——维护 size 的并查集：

模板 3——维护到祖宗节点距离的并查集：

### **Java**

```java
class Solution {
    private int[] p;
    private int[] size;

    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        p = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] != 0) {
                    continue;
                }
                int idx = i * n + j;
                if (i < m - 1 && land[i + 1][j] == 0) {
                    union(idx, (i + 1) * n + j);
                }
                if (j < n - 1 && land[i][j + 1] == 0) {
                    union(idx, i * n + j + 1);
                }
                if (i < m - 1 && j < n - 1 && land[i + 1][j + 1] == 0) {
                    union(idx, (i + 1) * n + j + 1);
                }
                if (i < m - 1 && j > 0 && land[i + 1][j - 1] == 0) {
                    union(idx, (i + 1) * n + j - 1);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < m * n; ++i) {
            if (land[i / n][i % n] != 0) {
                continue;
            }
            int root = find(i);
            if (!s.contains(root)) {
                s.add(root);
                t.add(size[root]);
            }
        }
        Collections.sort(t);
        int[] res = new int[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        size[pb] += size[pa];
        p[pa] = pb;
    }
}
```
