package change.scripts;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class AddLevel {
    static final String pathName = "D:\\WorkSpaces\\leetcode-all\\solution\\0000-0099";


    public static void main(String[] args) {
        // 获取当前目录名
        File currentDir = new File(pathName);
        String dirName = currentDir.getName();
        // 定义要添加的包名
        String packageName = "com.solution." + dirName;
        // 获取目录下的所有 .java 文件并修改其中的包名
        modifyPackagePrefix(new File(pathName), packageName);
    }

    /**
     * 遍历目录下的所有指定文件类型，并修改代码中的包名前缀
     *
     * @param directory   目录
     * @param packageName 要添加的包名前缀
     */
    public static void modifyPackagePrefix(File directory, String packageName) {
        if (directory.isDirectory()) {
            // 获取目录下的所有文件和子目录
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 如果是子目录，则递归调用 modifyPackagePrefix 方法
                        modifyPackagePrefix(file, packageName + "." + file.getName());
                    } else if (file.getName().endsWith(".java")) {
                        // 如果是 .java 文件，则修改其中的包名前缀
                        modifyJavaFilePackage(file, packageName);
                    }
                }
            }
        }
    }

    /**
     * 修改 Java 文件中的包名前缀
     *
     * @param javaFile    Java 文件
     * @param packageName 包名前缀
     */
    public static void modifyJavaFilePackage(File javaFile, String packageName) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 读取文件内容
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(javaFile), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 如果文件内容中没有 package 语句，则添加新的包名
                if (line.startsWith("package ")) {
                    line = "package " + packageName + ";";
                }
                stringBuilder.append(line).append('\n');
            }
            // 写入文件内容
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(javaFile), StandardCharsets.UTF_8));
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
