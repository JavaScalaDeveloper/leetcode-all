package com.solution._0068;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
    首先，将单词数组中的每个单词按顺序加入到当前行中，直到超过了 maxWidth 的长度。
    然后，根据单词数组中剩余的单词数量和需要填充的空格数量来计算每个单词之间需要填充的平均空格数和额外需要填充的空格数。
    最后，将当前行按照左对齐或均匀分配空格的方式进行格式化。
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left = 0; // 当前行的第一个单词在 words 数组中的下标
        while (left < words.length) {
            int right = left; // 当前行最后一个单词在 words 数组中的下标
            int lineLength = words[right].length(); // 当前行所有单词（含空格）的总长度
            while (right + 1 < words.length && lineLength + words[right + 1].length() + 1 <= maxWidth) {
                // 直到把下一个单词添加到当前行时行长度超过 maxWidth
                right++;
                lineLength += words[right].length() + 1; // 加上新单词的长度和一个空格的长度
            }
            // 计算空格的数量和单词之间的平均空格数
            int spaces = maxWidth - lineLength;
            int spaceCount = right - left; // 单词之间的空格数量
            String line = words[left];
            if (right == words.length - 1) { // 最后一行，单词之间不需要填充额外的空格
                for (int i = left + 1; i <= right; i++) {
                    line += " " + words[i];
                }
                for (int i = line.length(); i < maxWidth; i++) {
                    line += " ";
                }
            } else if (spaceCount == 0) { // 当前行只有一个单词
                for (int i = line.length(); i < maxWidth; i++) {
                    line += " ";
                }
            } else {
                int averageSpaceCount = spaces / spaceCount; // 单词之间平均需要填充的空格数
                int extraSpaceCount = spaces % spaceCount; // 需要额外填充的空格数
                for (int i = left + 1; i <= right; i++) {
                    int spaceLength = averageSpaceCount + (extraSpaceCount-- > 0 ? 1 : 0);
                    for (int j = 0; j < spaceLength; j++) {
                        line += " ";
                    }
                    line += words[i];
                }
            }
            result.add(line);
            left = right + 1; // 处理下一行
        }
        return result;
    }

}
