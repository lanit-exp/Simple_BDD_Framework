package ru.lanit.at.utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

public class VariableUtil {

    /**
     * замена контекстных переменных на значения
     *
     * @param preBody текст
     * @param vars    контекстные переменные
     * @return значение
     */
    public static String replaceVars(String preBody, Map<String, Object> vars) {
        StringBuilder replacedText = new StringBuilder(preBody);

        final String patternStartVar = "${";
        final String patternEndVar = "}";

        while (true) {
            int fi = replacedText.indexOf(patternStartVar);
            if (fi == -1) {
                break;
            }
            int li = replacedText.indexOf(patternEndVar, fi);
            String var = replacedText.substring(fi + patternStartVar.length(), li);
            if (vars.containsKey(var)) {
                replacedText.replace(fi, li + 1, vars.get(var).toString());
            } else {
                break;
            }
        }
        return replacedText.toString();
    }

    /**
     * убирает лишние символы (обычно для json)
     *
     * @param in текст
     * @return текст
     */
    public static String extractBrackets(Object in) {
        if (in == null) {
            return "null";
        }
        String op = in.toString();
        String replace;
        String replace2;
        if (op.startsWith("[") && op.endsWith("]") && !op.contains(",")) {
            replace = StringUtils.replace(StringUtils.replace(op, "[", ""), "]", "");
            if (StringUtils.startsWith(replace, "\"") && StringUtils.endsWith(replace, "\"")) {
                replace2 = StringUtils.replace(replace, "\"", "");
                try {
                    if (replace2.matches("^[0-9]+$")) {
                        new BigDecimal(replace2);
                    }
                    return replace2;
                } catch (NumberFormatException n) {
                    return replace;
                }
            } else {
                return replace;
            }
        }
        if (op.startsWith("[[") && op.endsWith("]]") || op.startsWith("[") && op.endsWith("]")) {
            op = op.replaceFirst("\\[", "");
            int lastIndex = op.lastIndexOf("]");
            return op.substring(0, lastIndex);
        }
        return op;
    }
}