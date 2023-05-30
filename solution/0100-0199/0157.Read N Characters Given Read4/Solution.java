package com.solution._0157;

import change.datastructure.Reader4;

/**
 * The read4 API is defined in the parent class Reader4.
 * int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        char[] cache = new char[4];
        int index = 0;  // 目标缓存区当前位置
        while (index < n) {
            // 先从缓存数组中读取
            int len = Math.min(n - index, cacheLen);
            for (int i = 0; i < len; i++) {
                buf[index++] = cache[cacheStart + i];
            }
            cacheStart += len;
            cacheLen -= len;
            if (cacheLen == 0) {  // 缓存数组为空，需要读取文件
                cacheLen = read4(cache);
                cacheStart = 0;
                if (cacheLen == 0) {  // 文件中没有字符了，结束循环
                    break;
                }
            }
        }
        return index;
    }

}
