# [1803. 统计异或值在范围内的数对有多少](https://leetcode.cn/problems/count-pairs-with-xor-in-a-range)

## 题目描述

<p>给你一个整数数组 <code>nums</code> （下标 <strong>从 0 开始</strong> 计数）以及两个整数：<code>low</code> 和 <code>high</code> ，请返回 <strong>漂亮数对</strong> 的数目。</p>

<p><strong>漂亮数对</strong> 是一个形如 <code>(i, j)</code> 的数对，其中 <code>0 &lt;= i &lt; j &lt; nums.length</code> 且 <code>low &lt;= (nums[i] XOR nums[j]) &lt;= high</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,4,2,7], low = 2, high = 6
<strong>输出：</strong>6
<strong>解释：</strong>所有漂亮数对 (i, j) 列出如下：
    - (0, 1): nums[0] XOR nums[1] = 5 
    - (0, 2): nums[0] XOR nums[2] = 3
    - (0, 3): nums[0] XOR nums[3] = 6
    - (1, 2): nums[1] XOR nums[2] = 6
    - (1, 3): nums[1] XOR nums[3] = 3
    - (2, 3): nums[2] XOR nums[3] = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,8,4,2,1], low = 5, high = 14
<strong>输出：</strong>8
<strong>解释：</strong>所有漂亮数对 (i, j) 列出如下：
​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
    - (0, 3): nums[0] XOR nums[3] = 11
    - (0, 4): nums[0] XOR nums[4] = 8
    - (1, 2): nums[1] XOR nums[2] = 12
    - (1, 3): nums[1] XOR nums[3] = 10
    - (1, 4): nums[1] XOR nums[4] = 9
    - (2, 3): nums[2] XOR nums[3] = 6
    - (2, 4): nums[2] XOR nums[4] = 5</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= low &lt;= high &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：0-1 字典树**

对于这种区间[low, high]统计的问题，我们可以考虑将其转换为统计[0, high]和[0, low - 1]的问题，然后相减即可得到答案。

在这道题中，我们可以统计有多少数对的异或值小于high+1，然后再统计有多少数对的异或值小于low，相减的结果就是异或值在区间[low, high]之间的数对数量。

另外，对于数组异或计数问题，我们通常可以使用“0-1 字典树”来解决。

字典树的节点定义如下：

-   `children[0]` 和 `children[1]` 分别表示当前节点的左右子节点；
-   `cnt` 表示以当前节点为结尾的数的数量。

在字典树中，我们还定义了以下两个函数：

其中一个函数是insert(x)，表示将数x插入到字典树中。该函数将数字x按照二进制位从高到低的顺序，插入到“0-1 字典树”中。如果当前二进制位为0，则插入到左子节点，否则插入到右子节点。然后将节点的计数值cnt加1。

另一个函数是search(x, limit)，表示在字典树中查找与x异或值小于limit的数量。该函数从字典树的根节点 `node` 开始，遍历x的二进制位，从高到低，记当前x的二进制位的数为v。如果当前limit的二进制位为1，此时我们可以直接将答案加上与x的当前二进制位v相同的子节点的计数值cnt，然后将当前节点移动到与x的当前二进制位v不同的子节点，即 `node = node.children[v ^ 1]`。继续遍历下一位。如果当前limit的二进制位为0，此时我们只能将当前节点移动到与x的当前二进制位v相同的子节点，即 `node = node.children[v]`。继续遍历下一位。遍历完x的二进制位后，返回答案。

有了以上两个函数，我们就可以解决本题了。

我们遍历数组 `nums`，对于每个数x，我们先在字典树中查找与x异或值小于high+1的数量，然后在字典树中查找与x异或值小于low的数对数量，将两者的差值加到答案中。接着将x插入到字典树中。继续遍历下一个数x，直到遍历完数组 `nums`。最后返回答案即可。

时间复杂度O(n \times \log M)，空间复杂度O(n \times \log M)。其中n为数组 `nums` 的长度，而M为数组 `nums` 中的最大值。本题中我们直接取\log M = 16。

### **Java**

```java
class Trie {
    private Trie[] children = new Trie[2];
    private int cnt;

    public void insert(int x) {
        Trie node = this;
        for (int i = 15; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
            ++node.cnt;
        }
    }

    public int search(int x, int limit) {
        Trie node = this;
        int ans = 0;
        for (int i = 15; i >= 0 && node != null; --i) {
            int v = (x >> i) & 1;
            if (((limit >> i) & 1) == 1) {
                if (node.children[v] != null) {
                    ans += node.children[v].cnt;
                }
                node = node.children[v ^ 1];
            } else {
                node = node.children[v];
            }
        }
        return ans;
    }
}

class Solution {
    public int countPairs(int[] nums, int low, int high) {
        Trie trie = new Trie();
        int ans = 0;
        for (int x : nums) {
            ans += trie.search(x, high + 1) - trie.search(x, low);
            trie.insert(x);
        }
        return ans;
    }
}
```
