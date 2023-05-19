package change.scripts;

import java.io.*;

public class MergeMdFiles {

    public static void main(String[] args) throws IOException {
        String directory = "D:\\WorkSpaces\\leetcode-all\\solution"; // 修改为目录路径
        String outputFile = "D:\\WorkSpaces\\leetcode-all\\change\\scripts\\out\\output-file.md"; // 修改为输出文件路径

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
        for (File file : files) {
            if (file.isDirectory()) {
                mergeMdFiles(file, os);
            } else if (file.isFile() && file.getName().endsWith(".md")) {
                FileInputStream fis = new FileInputStream(file);
                copy(fis, os);
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
