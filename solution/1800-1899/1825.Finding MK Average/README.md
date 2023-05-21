# [1825. 求出 MK 平均值](https://leetcode.cn/problems/finding-mk-average)

## 题目描述

<p>给你两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>k</code>&nbsp;，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 <b>MK 平均值</b>&nbsp;。</p>

<p><strong>MK 平均值</strong>&nbsp;按照如下步骤计算：</p>

<ol>
	<li>如果数据流中的整数少于 <code>m</code>&nbsp;个，<strong>MK 平均值</strong>&nbsp;为 <code>-1</code>&nbsp;，否则将数据流中最后 <code>m</code>&nbsp;个元素拷贝到一个独立的容器中。</li>
	<li>从这个容器中删除最小的 <code>k</code>&nbsp;个数和最大的 <code>k</code>&nbsp;个数。</li>
	<li>计算剩余元素的平均值，并 <strong>向下取整到最近的整数</strong>&nbsp;。</li>
</ol>

<p>请你实现&nbsp;<code>MKAverage</code>&nbsp;类：</p>

<ul>
	<li><code>MKAverage(int m, int k)</code>&nbsp;用一个空的数据流和两个整数 <code>m</code>&nbsp;和 <code>k</code>&nbsp;初始化&nbsp;<strong>MKAverage</strong>&nbsp;对象。</li>
	<li><code>void addElement(int num)</code>&nbsp;往数据流中插入一个新的元素&nbsp;<code>num</code>&nbsp;。</li>
	<li><code>int calculateMKAverage()</code>&nbsp;对当前的数据流计算并返回 <strong>MK 平均数</strong>&nbsp;，结果需 <strong>向下取整到最近的整数</strong> 。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
<strong>输出：</strong>
[null, null, null, -1, null, 3, null, null, null, 5]

<strong>解释：</strong>
MKAverage obj = new MKAverage(3, 1); 
obj.addElement(3);        // 当前元素为 [3]
obj.addElement(1);        // 当前元素为 [3,1]
obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
obj.addElement(10);       // 当前元素为 [3,1,10]
obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
                          // 删除最小以及最大的 1 个元素后，容器为 [3]
                          // [3] 的平均值等于 3/1 = 3 ，故返回 3
obj.addElement(5);        // 当前元素为 [3,1,10,5]
obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
                          // 删除最小以及最大的 1 个元素后，容器为 [5]
                          // [5] 的平均值等于 5/1 = 5 ，故返回 5
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k*2 &lt; m</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li><code>addElement</code> 与&nbsp;<code>calculateMKAverage</code>&nbsp;总操作次数不超过 <code>10<sup>5</sup></code> 次。</li>
</ul>

## 解法

**方法一：有序集合 + 队列**

我们可以维护以下数据结构或变量：

-   一个长度为m的队列q，其中队首元素为最早加入的元素，队尾元素为最近加入的元素；
-   三个有序集合，分别为lo,mid,hi，其中lo和hi分别存储最小的k个元素和最大的k个元素，而mid存储剩余的元素；
-   一个变量s，维护mid中所有元素的和；
-   部分编程语言（如 Java, Go）额外维护两个变量size1和size3，分别表示lo和hi中元素的个数。

调用addElement(num)函数时，顺序执行以下操作：

1. 如果lo为空，或者num \leq max(lo)，则将num加入lo中；否则如果hi为空，或者num \geq min(hi)，则将num加入hi中；否则将num加入mid中，同时将num的值加到s中。
1. 接下来将num加入队列q中，如果此时队列q的长度大于m，则将队首元素x从队列q中移除，接下来从lo,mid或hi中选择其中一个包含x的集合，将x从该集合中移除，如果该集合为mid，则将s减去x的值。
1. 如果lo的长度大于k，则循环将lo中的最大值max(lo)从lo中移除，将max(lo)加入mid中，同时将s加上max(lo)的值。
1. 如果hi的长度大于k，则循环将hi中的最小值min(hi)从hi中移除，将min(hi)加入mid中，同时将s加上min(hi)的值。
1. 如果lo的长度小于k，并且mid不为空，则循环将mid中的最小值min(mid)从mid中移除，将min(mid)加入lo中，同时将s减去min(mid)的值。
1. 如果hi的长度小于k，并且mid不为空，则循环将mid中的最大值max(mid)从mid中移除，将max(mid)加入hi中，同时将s减去max(mid)的值。

调用calculateMKAverage()函数时，如果q的长度小于m，则返回-1，否则返回\frac{s}{m - 2k}。

时间复杂度方面，每次调用addElement(num)函数的时间复杂度为O(\log m)，每次调用calculateMKAverage()函数的时间复杂度为O(1)。空间复杂度为O(m)。

### **Java**

```java
class MKAverage {

    private int m, k;
    private long s;
    private int size1, size3;
    private Deque<Integer> q = new ArrayDeque<>();
    private TreeMap<Integer, Integer> lo = new TreeMap<>();
    private TreeMap<Integer, Integer> mid = new TreeMap<>();
    private TreeMap<Integer, Integer> hi = new TreeMap<>();

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        if (lo.isEmpty() || num <= lo.lastKey()) {
            lo.merge(num, 1, Integer::sum);
            ++size1;
        } else if (hi.isEmpty() || num >= hi.firstKey()) {
            hi.merge(num, 1, Integer::sum);
            ++size3;
        } else {
            mid.merge(num, 1, Integer::sum);
            s += num;
        }
        q.offer(num);
        if (q.size() > m) {
            int x = q.poll();
            if (lo.containsKey(x)) {
                if (lo.merge(x, -1, Integer::sum) == 0) {
                    lo.remove(x);
                }
                --size1;
            } else if (hi.containsKey(x)) {
                if (hi.merge(x, -1, Integer::sum) == 0) {
                    hi.remove(x);
                }
                --size3;
            } else {
                if (mid.merge(x, -1, Integer::sum) == 0) {
                    mid.remove(x);
                }
                s -= x;
            }
        }
        for (; size1 > k; --size1) {
            int x = lo.lastKey();
            if (lo.merge(x, -1, Integer::sum) == 0) {
                lo.remove(x);
            }
            mid.merge(x, 1, Integer::sum);
            s += x;
        }
        for (; size3 > k; --size3) {
            int x = hi.firstKey();
            if (hi.merge(x, -1, Integer::sum) == 0) {
                hi.remove(x);
            }
            mid.merge(x, 1, Integer::sum);
            s += x;
        }
        for (; size1 < k && !mid.isEmpty(); ++size1) {
            int x = mid.firstKey();
            if (mid.merge(x, -1, Integer::sum) == 0) {
                mid.remove(x);
            }
            s -= x;
            lo.merge(x, 1, Integer::sum);
        }
        for (; size3 < k && !mid.isEmpty(); ++size3) {
            int x = mid.lastKey();
            if (mid.merge(x, -1, Integer::sum) == 0) {
                mid.remove(x);
            }
            s -= x;
            hi.merge(x, 1, Integer::sum);
        }
    }

    public int calculateMKAverage() {
        return q.size() < m ? -1 : (int) (s / (q.size() - k * 2));
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
```
