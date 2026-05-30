package GUI;

import Analysis.AnalysisResult;
import Analysis.IAnalyzer;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private final IAnalyzer analyzer;

    public MainMenu(IAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void run(String[] args) {
        if (args.length > 0) {
            runArguments(args);
            return;
        }
        runInteractive();
    }

    private void runArguments(String[] args) {
        if ("--batch".equalsIgnoreCase(args[0])) {
            if (args.length < 2) {
                printUsage();
                return;
            }
            String type = args.length >= 3 ? args[2] : "summary";
            List<AnalysisResult> results = analyzer.analyzeBatch(Path.of(args[1]), type);
            for (AnalysisResult result : results) {
                printResult(result);
            }
            return;
        }

        String type = args.length >= 2 ? args[1] : "summary";
        printResult(analyzer.analyze(Path.of(args[0]), type));
    }

    private void runInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Стабилизированный анализатор миссий магов");
        System.out.println("Поддерживаемые форматы: JSON, XML, TXT, YAML, LOG");
        System.out.println("Поддерживаемые отчеты: summary, detailed");
        while (true) {
            System.out.print("Введите путь к файлу миссии или exit: ");
            String path = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(path)) {
                return;
            }
            System.out.print("Тип отчета (summary/detailed, Enter = summary): ");
            String type = scanner.nextLine().trim();
            if (type.isBlank()) {
                type = "summary";
            }
            try {
                printResult(analyzer.analyze(Path.of(path), type));
            } catch (RuntimeException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
            }
        }
    }

    private void printResult(AnalysisResult result) {
        System.out.println("Формат распознан: " + result.getFormat());
        System.out.println("Тип отчета: " + result.getReportType());
        System.out.println();
        System.out.println(result.getReport());
    }

    private void printUsage() {
        System.out.println("Использование:");
        System.out.println("  java -jar mission-analyzer-lab2-2.0.jar <file> [summary|detailed]");
        System.out.println("  java -jar mission-analyzer-lab2-2.0.jar --batch <folder> [summary|detailed]");
    }
}
