package Parsers;

import java.util.ArrayList;
import java.util.List;

public class ParserFactory {
    private final List<IParser> parsers = new ArrayList<>();

    public ParserFactory register(IParser parser) {
        parsers.add(parser);
        return this;
    }

    public IParser findParser(String fileName, String content) {
        return parsers.stream()
                .filter(parser -> parser.supports(fileName, content))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неподдерживаемый формат файла: " + fileName));
    }

    public static ParserFactory defaultFactory() {
        return new ParserFactory()
                .register(new ParserJSON())
                .register(new ParserXML())
                .register(new ParserTXT())
                .register(new ParserYAML())
                .register(new ParserFWE());
    }
}
