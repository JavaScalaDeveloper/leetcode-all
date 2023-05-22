# [1115. 交替打印 FooBar](https://leetcode.cn/problems/print-foobar-alternately)

## 题目描述

<p>给你一个类：</p>

<pre>
class FooBar {
  public void foo() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print("foo");
&nbsp;   }
  }

  public void bar() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print("bar");
&nbsp; &nbsp; }
  }
}
</pre>

<p>两个不同的线程将会共用一个 <code>FooBar</code>&nbsp;实例：</p>

<ul>
	<li>线程 A 将会调用&nbsp;<code>foo()</code>&nbsp;方法，而</li>
	<li>线程 B 将会调用&nbsp;<code>bar()</code>&nbsp;方法</li>
</ul>

<p>请设计修改程序，以确保 <code>"foobar"</code> 被输出 <code>n</code> 次。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>"foobar"
<strong>解释：</strong>这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>"foobarfoobar"
<strong>解释：</strong>"foobar" 将被输出两次。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

**方法一：多线程 + 信号量**

我们用两个信号量f和b来控制两个线程的执行顺序，其中f初始值为1，而b初始值为0，表示线程A先执行。

当线程A执行时，首先会执行f的acquire操作，此时f的值变为0，线程A获得了f的使用权，可以执行foo函数，然后执行b的release操作，此时b的值变为1，线程B获得了b的使用权，可以执行bar函数。

当线程B执行时，首先会执行b的acquire操作，此时b的值变为0，线程B获得了b的使用权，可以执行bar函数，然后执行f的release操作，此时f的值变为1，线程A获得了f的使用权，可以执行foo函数。

因此，我们只需要循环n次，每次执行foo和bar函数时，先执行acquire操作，再执行release操作即可。

时间复杂度O(n)，空间复杂度O(1)。

### **Java**

```java
class FooBar {
    private int n;
    private Semaphore f = new Semaphore(1);
    private Semaphore b = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            f.acquire(1);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            b.release(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            b.acquire(1);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            f.release(1);
        }
    }
}
```
