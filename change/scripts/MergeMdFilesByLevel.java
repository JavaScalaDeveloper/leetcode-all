package change.scripts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MergeMdFilesByLevel {
    //    前1500道
    private static final int MAX_NUM = 1500;
    private static final String OUTPUT_PATH = "D:\\WorkSpaces\\leetcode-all\\change\\scripts\\out\\"; // 修改为输出文件路径
    //题号->难度
    private static final Map<String, String> num2LevelMap = ParseMenu.getLineData().stream()
            .collect(Collectors.toMap(ParseMenu.LineData::getA, ParseMenu.LineData::getD));

    public static void main(String[] args) throws IOException {
        String directory = "D:\\WorkSpaces\\leetcode-all\\solution"; // 修改为目录路径

        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("目录不存在或者不是一个目录");
            return;
        }
        Map<String, FileOutputStream> fileOutputStreamsMap = new HashMap<>();
//        中等, 困难, 简单
        fileOutputStreamsMap.put("简单", new FileOutputStream(OUTPUT_PATH + "easy-0~" + MAX_NUM + "-v2023.md"));
        fileOutputStreamsMap.put("中等", new FileOutputStream(OUTPUT_PATH + "medium-0~" + MAX_NUM + "-v2023.md"));
        fileOutputStreamsMap.put("困难", new FileOutputStream(OUTPUT_PATH + "hard-0~" + MAX_NUM + "-v2023.md"));

        mergeMdFiles(dir, fileOutputStreamsMap);
        fileOutputStreamsMap.forEach((s, fileOutputStream) -> {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void mergeMdFiles(File dir, Map<String, FileOutputStream> fileOutputStreamsMap) throws IOException {
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            if (basicFileAttributes.isDirectory()) {
                mergeMdFiles(file, fileOutputStreamsMap);
            } else if (basicFileAttributes.isRegularFile() && file.getName().endsWith(".md")) {
                FileInputStream fis = new FileInputStream(file);
                String path = file.getPath();
                if (path.length() > 60) {
//                    题号
                    String num = path.substring(46, 50);
                    int intNum = Integer.parseInt(num);
                    if (intNum <= MAX_NUM) {
//                    难度
                        String level = num2LevelMap.get(num);
                        FileOutputStream os = fileOutputStreamsMap.get(level);
                        if (os != null) {
                            System.out.println(num + "-->" + level);
                            copy(fis, os);
                        }
                    }
                }
                fis.close();
            }
        }
    }

    private static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
    }

}
