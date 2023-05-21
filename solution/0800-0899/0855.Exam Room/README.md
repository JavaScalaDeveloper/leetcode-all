# [855. 考场就座](https://leetcode.cn/problems/exam-room)

## 题目描述

<p>在考场里，一排有&nbsp;<code>N</code>&nbsp;个座位，分别编号为&nbsp;<code>0, 1, 2, ..., N-1</code>&nbsp;。</p>

<p>当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)</p>

<p>返回&nbsp;<code>ExamRoom(int N)</code>&nbsp;类，它有两个公开的函数：其中，函数&nbsp;<code>ExamRoom.seat()</code>&nbsp;会返回一个&nbsp;<code>int</code>&nbsp;（整型数据），代表学生坐的位置；函数&nbsp;<code>ExamRoom.leave(int p)</code>&nbsp;代表坐在座位 <code>p</code> 上的学生现在离开了考场。每次调用&nbsp;<code>ExamRoom.leave(p)</code>&nbsp;时都保证有学生坐在座位&nbsp;<code>p</code>&nbsp;上。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;ExamRoom&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;seat&quot;,&quot;leave&quot;,&quot;seat&quot;], [[10],[],[],[],[],[4],[]]
<strong>输出：</strong>[null,0,9,4,2,null,5]
<strong>解释：</strong>
ExamRoom(10) -&gt; null
seat() -&gt; 0，没有人在考场里，那么学生坐在 0 号座位上。
seat() -&gt; 9，学生最后坐在 9 号座位上。
seat() -&gt; 4，学生最后坐在 4 号座位上。
seat() -&gt; 2，学生最后坐在 2 号座位上。
leave(4) -&gt; null
seat() -&gt; 5，学生最后坐在 5 号座位上。
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
	<li>在所有的测试样例中&nbsp;<code>ExamRoom.seat()</code>&nbsp;和&nbsp;<code>ExamRoom.leave()</code>&nbsp;最多被调用&nbsp;<code>10^4</code>&nbsp;次。</li>
	<li>保证在调用&nbsp;<code>ExamRoom.leave(p)</code>&nbsp;时有学生正坐在座位 <code>p</code> 上。</li>
</ol>

## 解法

**方法一：有序集合 + 哈希表**

考虑到每次seat()时都需要找到最大距离的座位，我们可以使用有序集合来保存座位区间。有序集合的每个元素为一个二元组(l, r)，表示l和r之间（不包括l和r）的座位可以坐学生。初始时有序集合中只有一个元素(-1, n)，表示(-1, n)之间的座位可以坐学生。

另外，我们使用两个哈希表 `left` 和 `right` 来维护每个有学生的座位的左右邻居学生，方便我们在leave(p)时合并两个座位区间。

时间复杂度O(\log n)，空间复杂度O(n)。其中n为考场的座位数。

### **Java**

```java
class ExamRoom {
    private TreeSet<int[]> ts = new TreeSet<>((a, b) -> {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a[0] - b[0] : d2 - d1;
    });
    private Map<Integer, Integer> left = new HashMap<>();
    private Map<Integer, Integer> right = new HashMap<>();
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        add(new int[] {-1, n});
    }

    public int seat() {
        int[] s = ts.first();
        int p = (s[0] + s[1]) >> 1;
        if (s[0] == -1) {
            p = 0;
        } else if (s[1] == n) {
            p = n - 1;
        }
        del(s);
        add(new int[] {s[0], p});
        add(new int[] {p, s[1]});
        return p;
    }

    public void leave(int p) {
        int l = left.get(p), r = right.get(p);
        del(new int[] {l, p});
        del(new int[] {p, r});
        add(new int[] {l, r});
    }

    private int dist(int[] s) {
        int l = s[0], r = s[1];
        return l == -1 || r == n ? r - l - 1 : (r - l) >> 1;
    }

    private void add(int[] s) {
        ts.add(s);
        left.put(s[1], s[0]);
        right.put(s[0], s[1]);
    }

    private void del(int[] s) {
        ts.remove(s);
        left.remove(s[1]);
        right.remove(s[0]);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
```
