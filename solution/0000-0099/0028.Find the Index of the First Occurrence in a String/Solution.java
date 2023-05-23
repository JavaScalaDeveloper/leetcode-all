package com.solution._0028;

public class Solution {
    public static void main(String[] args) {
        String s1 = "abcdefghijklmnopqrstuvwxyz";
        String s2 = "klm";
        int res1 = kmp(s1, s2);
        System.out.println(res1);
        int res2 = rabinKarp2(s1, s2);
        System.out.println(res2);
    }

    /*
    暴力遍历
     */
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        int len1 = haystack.length();
        int len2 = needle.length();
        int p = 0;
        int q = 0;
        while (p < len1) {
            if (haystack.charAt(p) == needle.charAt(q)) {
                if (len2 == 1) {
                    return p;
                }
                ++p;
                ++q;
            } else {
                p -= q - 1;
                q = 0;
            }

            if (q == len2) {
                return p - q;
            }
        }
        return -1;
    }

    /*
    KMP 算法的主要思路是利用已知信息来避免无用的字符比较。具体来说，KMP 算法利用“最长前缀后缀”这个概念来预处理模式串，从而确定每次比较的起点；
    当匹配失败时，KMP 算法可以直接跳过已经匹配过的字符，继续向右移动，从而提高查找效率。
    我们需要先预处理出模式串 needle 的 next 数组，用于确定每次匹配的起点。在主串 haystack 和模式串 needle 进行匹配时，我们维护两个指针 i
     和 j，分别指向主串和模式串中当前正在比较的字符位置。如果当前字符匹配成功，则将两个指针都向右移动一位；否则，根据已知信息，将模式串向右移动
     一定长度（具体跳过几个字符可以通过 next 数组得到）。
     */
    public static int kmp(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = getNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        return -1;
    }

    private static int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < needle.length() - 1) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /*
    Boyer-Moore 算法则采用了另一种不同的思路。它通过从后向前扫描模式串和主串，利用已知信息来跳过尽可能多的字符，从而提高匹配效率。
    Boyer-Moore 算法的核心是两个启发式规则：坏字符规则和好后缀规则。其中，坏字符规则利用了模式串中字符出现位置的信息，而好后缀规
    则则利用了子串匹配信息。
     */
    public static int boyerMoore(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] bc = buildBc(needle);
        int[] gs = buildGs(needle);
        int i = 0, j = 0;
        while (i <= haystack.length() - needle.length()) {
            for (j = needle.length() - 1; j >= 0 && haystack.charAt(i + j) == needle.charAt(j); j--) ;
            if (j < 0) {
                return i;
            }
            i += Math.max(gs[j], j - bc[haystack.charAt(i + j)]);
        }
        return -1;
    }

    private static int[] buildBc(String needle) {
        int[] bc = new int[256];
        for (int i = 0; i < 256; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < needle.length(); i++) {
            bc[needle.charAt(i)] = i;
        }
        return bc;
    }

    private static int[] buildGs(String needle) {
        int[] suffix = new int[needle.length()];
        int[] gs = new int[needle.length()];
        int lastPrefixPos = needle.length();
        for (int i = needle.length() - 1; i >= 0; i--) {
            if (isPrefix(needle, i + 1)) {
                lastPrefixPos = i + 1;
            }
            suffix[i] = lastPrefixPos - i + needle.length() - 1;
        }
        for (int i = 0; i < needle.length() - 1; i++) {
            int j = suffix[i + 1] - needle.length() + 1;
            if (isSuffix(needle, j)) {
                gs[i] = j;
            } else {
                gs[i] = gs[i + 1];
            }
        }
        gs[needle.length() - 1] = suffix[0];
        return gs;
    }

    private static boolean isPrefix(String needle, int p) {
        for (int i = p, j = 0; i < needle.length(); i++, j++) {
            if (needle.charAt(i) != needle.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSuffix(String needle, int p) {
        for (int i = 0, j = p; j < needle.length(); i++, j++) {
            if (needle.charAt(i) != needle.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /*
    Rabin-Karp算法也是一种常用的字符串匹配算法，它利用哈希函数来检测对比串是否和主串匹配。它的思想是将对比串和主串中的子串都通过哈希函数转换
    成数值形式，然后在数值上进行比较，从而判断两者是否相同。
    具体来说，Rabin-Karp算法使用一个哈希函数 H 来映射字符串为一个数字，通常使用取模运算和幂运算来计算哈希值。在检查每个子串与目标子串是否匹配
    时，我们首先计算出目标子串的哈希值 T，然后在主串中逐个计算子串的哈希值，如果某个子串的哈希值与 T 相同，则判断这个子串和目标子串是否相同。
    在 Rabin-Karp 算法中，我们首先要预处理出目标子串的哈希值 T，然后从主串 haystack 的第一个子串开始计算每个子串的哈希值。
    当检查到某个子串的哈希值与 T 相同时，需要进一步检查该子串是否与目标子串相同。如果相同，就返回该子串的起始位置；否则，继续计算下一个子串的哈希值。
    需要注意的是，在计算哈希值时，为了避免哈希冲突，我们需要选择合适的哈希函数和哈希表大小。同时，我们还需要使用适当的哈希函数来降低错误匹配的
    概率，例如在以上代码中，我们使用了一个经过优化的哈希函数，以减少哈希碰撞的概率。
     */
    public static int rabinKarp(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int M = needle.length(), N = haystack.length();
        long patternHash = hashCode(needle);
        long textHash = hashCode(haystack.substring(0, M));
        if (patternHash == textHash && needle.equals(haystack.substring(0, M))) {
            return 0;
        }
        for (int i = M; i < N; i++) {
            textHash = (textHash - (long) Math.pow(31, M - 1) * haystack.charAt(i - M)) * 31 + haystack.charAt(i);
            if (textHash == patternHash && needle.equals(haystack.substring(i - M + 1, i + 1))) {
                return i - M + 1;
            }
        }
        return -1;
    }

    private static long hashCode(String str) {
        int p = 31; // 使用质数 31，可以减少哈希碰撞的概率
        int MOD = 1000000007; // 取模运算的模数，需要选一个足够大的质数
        long hashValue = 0;
        long pPow = 1;
        for (int i = 0; i < str.length(); i++) {
            hashValue = (hashValue + (str.charAt(i) - 'a' + 1) * pPow) % MOD;
            pPow = (pPow * p) % MOD;
        }
        return hashValue;
    }

    static final int MOD = 1000000007;

    public static int rabinKarp2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        if (m > n) {
            return -1;
        }
        long h = 1, p = 31; // 取模运算的模数，需要选一个足够大的质数
        long patternHash = 0, textHash = 0;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * p + needle.charAt(i)) % MOD;
            textHash = (textHash * p + haystack.charAt(i)) % MOD;
            h = (h * p) % MOD;
        }
        if (patternHash == textHash && needle.equals(haystack.substring(0, m))) {
            return 0;
        }
        for (int i = m; i < n; i++) {
            textHash = (textHash - (haystack.charAt(i - m) * h) % MOD + MOD) % MOD;
            textHash = (textHash * p + haystack.charAt(i)) % MOD;
            if (textHash == patternHash && needle.equals(haystack.substring(i - m + 1, i + 1))) {
                return i - m + 1;
            }
        }
        return -1;
    }

}
