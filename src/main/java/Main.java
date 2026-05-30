import Analysis.AnalyzerFactory;
import Analysis.IAnalyzer;
import GUI.MainMenu;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        IAnalyzer analyzer = AnalyzerFactory.createDefault();
        MainMenu menu = new MainMenu(analyzer);
        menu.run(args);
    }
}
