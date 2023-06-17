package change.datastructure;

public class Reader4 {
    public char[] cache = new char[4];  // 缓存数组
    public int cacheStart = 0;  // 缓存数组中未使用的起始位置
    public int cacheLen = 0;  // 缓存数组中未使用的字符个数

    /**
     * API read4 可以从文件中读取 4 个连续的字符，并且将它们写入缓存数组 buf 中。
     * 返回值为实际读取的字符个数。
     */
    public int read4(char[] buf4) {
        return 0;  // 这里是为了演示而写的，具体实现需要根据具体场景而定
    }
}

