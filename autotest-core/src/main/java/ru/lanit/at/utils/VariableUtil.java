package ru.lanit.at.utils;


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
}