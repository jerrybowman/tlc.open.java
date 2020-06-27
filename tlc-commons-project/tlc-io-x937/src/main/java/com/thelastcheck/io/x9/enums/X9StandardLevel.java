package com.thelastcheck.io.x9.enums;

import com.google.common.collect.ImmutableMap;
import com.thelastcheck.commons.base.collect.NullCheckingMap;

import java.util.Map;

public enum X9StandardLevel {
    X937_STANDARD_1994("01"),
    X937_STANDARD_2001("02"),
    X937_STANDARD_DSTU("03");

    private static Map<String, X9StandardLevel> codeMap;
    static {
        ImmutableMap.Builder<String, X9StandardLevel> builder = ImmutableMap.builder();
        for (X9StandardLevel type : values()) {
            builder.put(type.code, type);
        }
        codeMap = NullCheckingMap.decorate(builder.build());
    }

    private String code;
    X9StandardLevel(String typeCode) {
        code = typeCode;
    }
    public String getCode() { return code; }

    public static X9StandardLevel forCode(String code) {
        return codeMap.get(code);
    }

    }
