package change.scripts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParseMenu {
    public static void main(String[] args) {
        List<String> lines = readLines("change/scripts/input/menu.txt");
        List<LineData> lineData = parseLines(lines);
        Map<String, String> num2LevelMap = lineData.stream().collect(Collectors.toMap(LineData::getA, LineData::getD));
        System.out.println(num2LevelMap);
        Map<String, List<LineData>> collect = lineData.stream().collect(Collectors.groupingBy(LineData::getD));
//        System.out.println(collect);
    }

    private static class LineData {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        @Override
        public String toString() {
            return "LineData{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    ", c='" + c + '\'' +
                    ", d='" + d + '\'' +
                    ", e='" + e + '\'' +
                    '}';
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }

        public String getC() {
            return c;
        }

        public String getD() {
            return d;
        }

        public String getE() {
            return e;
        }

        public LineData(String[] fields) {
            this.a = fields[0].trim();
            this.b = fields[1].trim();
            this.c = fields[2].trim();
            this.d = fields[3].trim();
            this.e = fields[4].trim();
        }
        // getter 和 setter 方法省略
    }

    public static List<LineData> parseLines(List<String> lines) {
        List<LineData> result = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.substring(1, line.length() - 1).split("\\|"); // 去掉首尾的 "|" 符号
            result.add(new LineData(fields));
        }
        return result;
    }

    /**
     * 读取每一行文件
     * @param filePath
     * @return
     */
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
