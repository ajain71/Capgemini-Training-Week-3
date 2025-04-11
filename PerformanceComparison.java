import java.io.*;
public class PerformanceComparison {
    public static void main(String[] args) throws IOException {
        String text = "hello";
        int iterations = 1000000;
        long startSB = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(text);
        }
        long endSB = System.currentTimeMillis();
        System.out.println("Time taken by StringBuilder: " + (endSB - startSB) + " ms");
        long startSBuf = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbuf.append(text);
        }
        long endSBuf = System.currentTimeMillis();
        System.out.println("Time taken by StringBuffer: " + (endSBuf - startSBuf) + " ms");
        long startFR = System.currentTimeMillis();
        FileReader fr = new FileReader("largefile.txt");
        int ch;
        StringBuilder fileContentFR = new StringBuilder();
        while ((ch = fr.read()) != -1) {
            fileContentFR.append((char) ch);
        }
        String[] wordsFR = fileContentFR.toString().split("\\s+");
        System.out.println("FileReader Word Count: " + wordsFR.length);
        fr.close();
        long endFR = System.currentTimeMillis();
        System.out.println("Time taken by FileReader: " + (endFR - startFR) + " ms");
        long startISR = System.currentTimeMillis();
        InputStreamReader isr = new InputStreamReader(new FileInputStream("largefile.txt"));
        StringBuilder fileContentISR = new StringBuilder();
        while ((ch = isr.read()) != -1) {
            fileContentISR.append((char) ch);
        }
        String[] wordsISR = fileContentISR.toString().split("\\s+");
        System.out.println("InputStreamReader Word Count: " + wordsISR.length);
        isr.close();
        long endISR = System.currentTimeMillis();
        System.out.println("Time taken by InputStreamReader: " + (endISR - startISR) + " ms");
    }
}