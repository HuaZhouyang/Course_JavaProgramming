import java.io.*;

@TxtData(lessonName = "Lesson05_ProgramListing")
public class OutputAsTxt {
    public static void main(String[] args) throws IOException {
        new OutputAsTxt().outputAsTxt();
    }

    String lessonName;
    String txtName;
    String packageName;
    public void outputAsTxt() throws IOException {
        TxtData txtData = OutputAsTxt.class.getAnnotation(TxtData.class);
        lessonName = txtData.lessonName();
        txtName = "代码" + Integer.parseInt(lessonName.substring(6, 8));

        // initial output
        BufferedWriter writer = new BufferedWriter(new FileWriter(lessonName + "\\" + txtName + ".txt"));
        writer.write(txtName);
        writer.newLine();
        writer.newLine();
        writer.write("GitHub地址：https://github.com/HuaZhouyang/Course_JavaProgramming");
        writer.newLine();

        // input
        File src = new File(lessonName, "src");
        File[] packages = src.listFiles();
        for (File pac : packages) {
            if (!pac.isDirectory()) continue;

            // output codes respectively
            output(pac, writer);
        }
        writer.close();
    }

    private void output(File folder, BufferedWriter writer) throws IOException {
        writer.write("———————————————————————————————————————");
        writer.newLine();
        packageName = folder.getName().replace("_", " ");
        writer.write(packageName + ":");
        writer.newLine();

        File[] codes = folder.listFiles();
        for (File code : codes) {
            writer.write("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            writer.newLine();
            writer.write(code.getName() + ":"); // 开始写入各个代码
            writer.newLine();
            writer.newLine();

            BufferedReader reader = new BufferedReader(new FileReader(code));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                writer.write(line);
                writer.newLine();
            }

            reader.close();
        }
    }
}

