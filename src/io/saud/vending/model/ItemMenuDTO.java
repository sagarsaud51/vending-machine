package io.saud.vending.model;

import java.util.HashMap;
import java.util.Map;

public class ItemMenuDTO {

    private final Map<String, ItemDTO> codeItemMapper = new HashMap<>();
    public Map<String, ItemDTO> getCodeItemMapper() {
        return codeItemMapper;
    }


}
