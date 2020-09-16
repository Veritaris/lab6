package Client;

import java.util.Scanner;

public class ConsoleReader {
    public String line;
    private final Scanner consoleScanner = new Scanner(System.in);

    public ConsoleReader() {

    }

    public String readLine() {
        line = consoleScanner.nextLine();
        return line;
    }
}
