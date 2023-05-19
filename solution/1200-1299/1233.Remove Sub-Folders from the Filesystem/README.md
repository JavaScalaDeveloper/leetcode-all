# [1233. 删除子文件夹](https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem)

[English Version](/solution/1200-1299/1233.Remove%20Sub-Folders%20from%20the%20Filesystem/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一位系统管理员，手里有一份文件夹列表 <code>folder</code>，你的任务是要删除该列表中的所有 <strong>子文件夹</strong>，并以 <strong>任意顺序</strong> 返回剩下的文件夹。</p>

<p>如果文件夹&nbsp;<code>folder[i]</code>&nbsp;位于另一个文件夹&nbsp;<code>folder[j]</code>&nbsp;下，那么&nbsp;<code>folder[i]</code>&nbsp;就是&nbsp;<code>folder[j]</code>&nbsp;的 <strong>子文件夹</strong> 。</p>

<p>文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：<font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'/'</span></span></font></font>&nbsp;后跟一个或者多个小写英文字母。</p>

<ul>
	<li>例如，<code>"/leetcode"</code>&nbsp;和&nbsp;<code>"/leetcode/problems"</code>&nbsp;都是有效的路径，而空字符串和&nbsp;<code>"/"</code>&nbsp;不是。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
<strong>输出：</strong>["/a","/c/d","/c/f"]
<strong>解释：</strong>"/a/b" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>folder = ["/a","/a/b/c","/a/b/d"]
<strong>输出：</strong>["/a"]
<strong>解释：</strong>文件夹 "/a/b/c" 和 "/a/b/d" 都会被删除，因为它们都是 "/a" 的子文件夹。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> folder = ["/a/b/c","/a/b/ca","/a/b/d"]
<strong>输出:</strong> ["/a/b/c","/a/b/ca","/a/b/d"]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= folder.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= folder[i].length &lt;= 100</code></li>
	<li><code>folder[i]</code>&nbsp;只包含小写字母和 <code>'/'</code></li>
	<li><code>folder[i]</code>&nbsp;总是以字符 <code>'/'</code>&nbsp;起始</li>
	<li><code>folder</code>&nbsp;每个元素都是 <strong>唯一</strong> 的</li>
</ul>

## 解法

**方法一：排序**

我们先将数组 `folder` 按照字典序排序，然后遍历数组，对于当前遍历到的文件夹 $f$，如果它的长度大于等于答案数组中最后一个文件夹的长度，并且它的前缀包含答案数组的最后一个文件夹再加上一个 `/`，则说明 $f$ 是答案数组中最后一个文件夹的子文件夹，我们不需要将其加入答案数组中。否则，我们将 $f$ 加入答案数组中。

遍历结束后，答案数组中的文件夹即为题目要求的答案。

时间复杂度 $O(n \times \log n \times m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为数组 `folder` 的长度和数组 `folder` 中字符串的最大长度。

**方法二：字典树**

我们可以使用字典树存储数组 `folder` 中的所有文件夹。字典树的每个节点包含 `children` 字段，用于存储当前节点的子节点，以及 `fid` 字段，用于存储当前节点对应的文件夹在数组 `folder` 中的下标。

对于数组 `folder` 中的每个文件夹 $f$，我们先将 $f$ 按照 `/` 分割成若干个子串，然后从根节点开始，依次将子串加入字典树中。接下来，我们从根节点开始搜索字典树，如果当前节点的 `fid` 字段不为 `-1`，则说明当前节点对应的文件夹是答案数组中的一个文件夹，我们将其加入答案数组并且返回。否则，我们递归地搜索当前节点的所有子节点，最终返回答案数组。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别为数组 `folder` 的长度和数组 `folder` 中字符串的最大长度。

### **Java**

```java
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; ++i) {
            int m = ans.get(ans.size() - 1).length();
            int n = folder[i].length();
            if (m >= n || !(ans.get(ans.size() - 1).equals(folder[i].substring(0, m)) && folder[i].charAt(m) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }
}
```

```java
class Trie {
    private Map<String, Trie> children = new HashMap<>();
    private int fid = -1;

    public void insert(int fid, String f) {
        Trie node = this;
        String[] ps = f.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.fid = fid;
    }

    public List<Integer> search() {
        List<Integer> ans = new ArrayList<>();
        dfs(this, ans);
        return ans;
    }

    private void dfs(Trie root, List<Integer> ans) {
        if (root.fid != -1) {
            ans.add(root.fid);
            return;
        }
        for (var child : root.children.values()) {
            dfs(child, ans);
        }
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for (int i = 0; i < folder.length; ++i) {
            trie.insert(i, folder[i]);
        }
        List<String> ans = new ArrayList<>();
        for (int i : trie.search()) {
            ans.add(folder[i]);
        }
        return ans;
    }
}
```
