import java.io.*;

public class OutputAsDoc {
    public static void main(String[] args) throws IOException {
        File src = new File(".\\Lesson02_ProgramListing\\src");
        File[] files = src.listFiles();
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                ".\\Lesson02_ProgramListing\\代码2.txt"));
        writer.write("GitHub地址：https://github.com/HuaZhouyang/Course_JavaProgramming");
        writer.newLine();
        for (File file : files) {
            if (file.isDirectory()) {
                writer.write("———————————————————————————————————————");
                writer.newLine();
                String unit = file.getName().replace("_", " ");
                writer.write(unit + ":");
                writer.newLine();
                File[] codes = file.listFiles();
                for (File code : codes) {
//                    writer.write("——————————————————————————————————");
                    writer.write(
                            "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    writer.newLine();
                    String name = code.getName();
                    writer.write(unit.charAt(5) + "-" + name.charAt(1) + " " + name.substring(3) + ":");
                    writer.newLine();
                    writer.newLine();

                    BufferedReader reader = new BufferedReader(new FileReader(code));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.isBlank()) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                    reader.close();
                }
            }
        }
        writer.close();
    }
}
