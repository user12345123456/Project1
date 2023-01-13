import Calc.Calculation;
import FileType.TXTFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TXTFile t=new TXTFile();
        String s=t.readFile("input.txt");
        Calculation calc=new Calculation();
        Double a=calc.eval(s);
        try {
            t.write("output.txt", a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}