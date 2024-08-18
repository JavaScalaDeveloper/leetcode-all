private Queue<Integer> buffer; // 缓冲区
private int maxSize; // 缓冲区最大容量
public synchronized void produce(int item){
while (buffer.size() == maxSize)  wait();
buffer.add(item);
System.out.println("Produced: " + item);
notifyAll();  
}
public synchronized int consume(){
while (buffer.size() == 0) wait();
int item = buffer.remove();
System.out.println("Consumed: " + item);
notifyAll();  
return item;
}

```java
class Solution {
public int longest(String s1, String s2) {
int m = s1.length(), n = s2.length();
int[][] dp = new int[m + 1][n + 1];
for (int i = 1; i <= m; ++i) {
 for (int j = 1; j <= n; ++j) {
if (s1.charAt(i - 1) == s2.charAt(j - 1)) 
 dp[i][j] = dp[i - 1][j - 1] + 1;
else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
 }
}
return dp[m][n];}
}
```