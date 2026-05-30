package Parsers;

import Entities.Mission;

public interface IParser {
    String getName();
    boolean supports(String fileName, String content);
    Mission parse(String fileName, String content);
}
