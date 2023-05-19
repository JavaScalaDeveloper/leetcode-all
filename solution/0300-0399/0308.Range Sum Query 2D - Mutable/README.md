# [308. 二维区域和检索 - 可变](https://leetcode.cn/problems/range-sum-query-2d-mutable)

[English Version](/solution/0300-0399/0308.Range%20Sum%20Query%202D%20-%20Mutable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维矩阵 <code>matrix</code> ，处理以下类型的多个查询:</p>

<ol>
	<li><strong>更新</strong> <code>matrix</code> 中单元格的值。</li>
	<li>计算由&nbsp;<strong>左上角</strong> <code>(row1, col1)</code> 和&nbsp;<strong>右下角</strong> <code>(row2, col2)</code> 定义的 <code>matrix</code>&nbsp;内矩阵元素的&nbsp;<strong>和</strong>。</li>
</ol>

<p>实现 <code>NumMatrix</code> 类：</p>

<ul>
	<li><code>NumMatrix(int[][] matrix)</code> 用整数矩阵&nbsp;<code>matrix</code> 初始化对象。</li>
	<li><code>void update(int row, int col, int val)</code> <strong>更新</strong> <code>matrix[row][col]</code> 的值到 <code>val</code> 。</li>
	<li><code>int sumRegion(int row1, int col1, int row2, int col2)</code> 返回矩阵&nbsp;<code>matrix</code> 中指定矩形区域元素的 <strong>和</strong> ，该区域由 <strong>左上角</strong> <code>(row1, col1)</code> 和 <strong>右下角</strong> <code>(row2, col2)</code> 界定。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0308.Range%20Sum%20Query%202D%20-%20Mutable/images/summut-grid.jpg" style="height: 222px; width: 500px;" />
<pre>
<strong>输入</strong>
["NumMatrix", "sumRegion", "update", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
<strong>输出</strong>
[null, 8, null, 10]

<strong>解释</strong>
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // 返回 8 (即, 左侧红色矩形的和)
numMatrix.update(3, 2, 2); // 矩阵从左图变为右图
numMatrix.sumRegion(2, 1, 4, 3); // 返回 10 (即，右侧红色矩形的和)

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-10<sup>5</sup> &lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= row &lt; m</code></li>
	<li><code>0 &lt;= col &lt; n</code></li>
	<li><code>-10<sup>5</sup> &lt;= val &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= row1 &lt;= row2 &lt; m</code></li>
	<li><code>0 &lt;= col1 &lt;= col2 &lt; n</code></li>
	<li>最多调用<code>10<sup>4</sup></code> 次&nbsp;<code>sumRegion</code> 和 <code>update</code> 方法</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

对于本题，可以构建二维树状数组。

**方法二：线段树**

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 `log(width)`。更新某个元素的值，只需要更新 `log(width)` 个区间，并且这些区间都包含在一个包含该元素的大区间内。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 `[1, N]`；
-   线段树的每个叶子节点代表一个长度为 1 的元区间 `[x, x]`；
-   对于每个内部节点 `[l, r]`，它的左儿子是 `[l, mid]`，右儿子是 `[mid + 1, r]`, 其中 `mid = ⌊(l + r) / 2⌋` (即向下取整)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

树状数组：



线段树：



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

树状数组：

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}

class NumMatrix {
    private BinaryIndexedTree[] trees;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        trees = new BinaryIndexedTree[m];
        for (int i = 0; i < m; ++i) {
            BinaryIndexedTree tree = new BinaryIndexedTree(n);
            for (int j = 0; j < n; ++j) {
                tree.update(j + 1, matrix[i][j]);
            }
            trees[i] = tree;
        }
    }

    public void update(int row, int col, int val) {
        BinaryIndexedTree tree = trees[row];
        int prev = tree.query(col + 1) - tree.query(col);
        tree.update(col + 1, val - prev);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int s = 0;
        for (int i = row1; i <= row2; ++i) {
            BinaryIndexedTree tree = trees[i];
            s += tree.query(col2 + 1) - tree.query(col1);
        }
        return s;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
```

线段树：

```java
class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length;
        tr = new Node[n << 2];
        this.nums = nums;
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += query(u << 1, l, r);
        }
        if (r > mid) {
            v += query(u << 1 | 1, l, r);
        }
        return v;
    }
}

class NumMatrix {
    private SegmentTree[] trees;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        trees = new SegmentTree[m];
        for (int i = 0; i < m; ++i) {
            trees[i] = new SegmentTree(matrix[i]);
        }
    }

    public void update(int row, int col, int val) {
        SegmentTree tree = trees[row];
        tree.modify(1, col + 1, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int s = 0;
        for (int row = row1; row <= row2; ++row) {
            SegmentTree tree = trees[row];
            s += tree.query(1, col1 + 1, col2 + 1);
        }
        return s;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
```



树状数组：



线段树：





树状数组：



### **...**

```

```


