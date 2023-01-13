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
        FileOutputStream outputStream = new FileOutputStream(fileName);
        // byte[] strToBytes = str.getBytes();
        outputStream.write(str.byteValue());

        outputStream.close();
    }
}
