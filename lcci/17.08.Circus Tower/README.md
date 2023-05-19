# [面试题 17.08. 马戏团人塔](https://leetcode.cn/problems/circus-tower-lcci)

[English Version](/lcci/17.08.Circus%20Tower/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
<strong>输出：</strong>6
<strong>解释：</strong>从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)</pre>
<p>提示：</p>
<ul>
	<li><code>height.length == weight.length &lt;= 10000</code></li>
</ul>

## 解法

**方法一：排序 + 离散化 + 树状数组**

我们现将所有人按照身高从小到大排序，若身高相同，则按照体重从大到小排序。这样我们可以将问题转换为求体重数组的最长递增子序列的问题。

最长递增子序列的问题可以使用动态规划求解，时间复杂度 $O(n^2)$。但是我们可以使用树状数组来优化求解过程，时间复杂度 $O(n \log n)$。

空间复杂度 $O(n)$。其中 $n$ 为人数。

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            this.c[x] = Math.max(this.c[x], val);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, this.c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {height[i], weight[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        Set<Integer> s = new HashSet<>();
        for (int[] e : arr) {
            s.add(e[1]);
        }
        List<Integer> alls = new ArrayList<>(s);
        Collections.sort(alls);
        Map<Integer, Integer> m = new HashMap<>(alls.size());
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(alls.size());
        int ans = 1;
        for (int[] e : arr) {
            int x = m.get(e[1]);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}
```
