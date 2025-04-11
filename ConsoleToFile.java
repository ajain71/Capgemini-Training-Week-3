import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class ConsoleToFile {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter("output.txt");
            String inputLine;
            System.out.println("Enter text (type 'exit' to quit):");
            while (!(inputLine = br.readLine()).equalsIgnoreCase("exit")) {
                fw.write(inputLine + "\n");
            }
            fw.close();
            br.close();
            isr.close();
            System.out.println("Input has been written to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}