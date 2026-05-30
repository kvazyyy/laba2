package Entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class MissionExtensionBlock {
    private final String blockName;
    private final Map<String, String> values = new LinkedHashMap<>();

    public MissionExtensionBlock(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockName() { return blockName; }
    public Map<String, String> getValues() { return values; }
    public void put(String key, String value) { values.put(key, value); }
}
