package FileType;

import java.io.*;
import java.util.Scanner;

public class TXTFile {

    public String readFile(String filename) {
        String str = new String();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fileReader);
        str = scanner.nextLine();
        return str;
    }
    public void write(String fileName, Double str) throws IOException {

        try (final FileWriter writer = new FileWriter(fileName, false))
        {
                final String s = Double.toString(str);
                writer.write(s);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
