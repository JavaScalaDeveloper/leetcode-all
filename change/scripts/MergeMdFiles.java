package change.scripts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class MergeMdFiles {
    private static final int EXPORT_SIZE=500;

    public static void main(String[] args) throws IOException {
        String directory = "D:\\WorkSpaces\\leetcode-all\\solution"; // 修改为目录路径
        String outputFile = "D:\\WorkSpaces\\leetcode-all\\change\\scripts\\out\\leetcode-"+EXPORT_SIZE+"-v2023.md"; // 修改为输出文件路径

        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("目录不存在或者不是一个目录");
            return;
        }

        FileOutputStream fos = new FileOutputStream(outputFile);
        mergeMdFiles(dir, fos);
        fos.close();
    }

    private static void mergeMdFiles(File dir, OutputStream os) throws IOException {
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            if (basicFileAttributes.isDirectory()) {
                mergeMdFiles(file, os);
            } else if (basicFileAttributes.isRegularFile() && file.getName().endsWith(".md")) {
//                忽略solution根目录的文件
                if (file.getPath().length() > 60) {
                    String num = file.getPath().substring(46, 50);
                    int no = Integer.parseInt(num);
//                    导出前2000道
                    if (no <= EXPORT_SIZE) {
                        FileInputStream fis = new FileInputStream(file);
                        copy(fis, os);
                        fis.close();
                    }

                }
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
