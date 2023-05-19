package change.scripts;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String filePath = "D:\\WorkSpaces\\leetcode-all\\solution\\0000-0099\\0001.Two Sum\\README.md";
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }


//        String str = "caergblkmghfaersnecsblixetwey";
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            String result = str.substring(0, startIndex) + str.substring(endIndex);
            System.out.println(result);
        } else {
            System.out.println(str);
        }


    }
}
