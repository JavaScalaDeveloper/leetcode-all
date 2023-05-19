# [2115. 从给定原材料中找到所有可以做出的菜](https://leetcode.cn/problems/find-all-possible-recipes-from-given-supplies)

[English Version](/solution/2100-2199/2115.Find%20All%20Possible%20Recipes%20from%20Given%20Supplies/README_EN.md)

## 题目描述

<p>你有 <code>n</code>&nbsp;道不同菜的信息。给你一个字符串数组&nbsp;<code>recipes</code>&nbsp;和一个二维字符串数组&nbsp;<code>ingredients</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;道菜的名字为&nbsp;<code>recipes[i]</code>&nbsp;，如果你有它&nbsp;<strong>所有</strong>&nbsp;的原材料&nbsp;<code>ingredients[i]</code>&nbsp;，那么你可以&nbsp;<strong>做出</strong>&nbsp;这道菜。一道菜的原材料可能是&nbsp;<strong>另一道</strong>&nbsp;菜，也就是说&nbsp;<code>ingredients[i]</code>&nbsp;可能包含&nbsp;<code>recipes</code>&nbsp;中另一个字符串。</p>

<p>同时给你一个字符串数组&nbsp;<code>supplies</code>&nbsp;，它包含你初始时拥有的所有原材料，每一种原材料你都有无限多。</p>

<p>请你返回你可以做出的所有菜。你可以以 <strong>任意顺序</strong>&nbsp;返回它们。</p>

<p>注意两道菜在它们的原材料中可能互相包含。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
<b>输出：</b>["bread"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
<b>输出：</b>["bread","sandwich"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
<b>输出：</b>["bread","sandwich","burger"]
<strong>解释：</strong>
我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
我们可以做出 "burger" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 和 "sandwich" 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast"]
<b>输出：</b>[]
<strong>解释：</strong>
我们没法做出任何菜，因为我们只有原材料 "yeast" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == recipes.length == ingredients.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= ingredients[i].length, supplies.length &lt;= 100</code></li>
	<li><code>1 &lt;= recipes[i].length, ingredients[i][j].length, supplies[k].length &lt;= 10</code></li>
	<li><code>recipes[i], ingredients[i][j]</code>&nbsp;和&nbsp;<code>supplies[k]</code>&nbsp;只包含小写英文字母。</li>
	<li>所有&nbsp;<code>recipes</code> 和&nbsp;<code>supplies</code>&nbsp;中的值互不相同。</li>
	<li><code>ingredients[i]</code>&nbsp;中的字符串互不相同。</li>
</ul>

## 解法

**方法一：拓扑排序**

首先，我们可以将每道菜看成一个节点，每个节点的入度表示其所需的原材料数量。我们可以通过拓扑排序的方式，找到所有可以做出的菜。

### **Java**

```java
class Solution {
    public List<String> findAllRecipes(
        String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> g = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        for (int i = 0; i < recipes.length; ++i) {
            for (String v : ingredients.get(i)) {
                g.computeIfAbsent(v, k -> new ArrayList<>()).add(recipes[i]);
            }
            indeg.put(recipes[i], ingredients.get(i).size());
        }
        Deque<String> q = new ArrayDeque<>();
        for (String s : supplies) {
            q.offer(s);
        }
        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                String i = q.pollFirst();
                for (String j : g.getOrDefault(i, Collections.emptyList())) {
                    indeg.put(j, indeg.get(j) - 1);
                    if (indeg.get(j) == 0) {
                        ans.add(j);
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}
```
