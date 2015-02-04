package org.corenel.core.util;

public class StringUtil {

    public static String changeFirstCharacter(String str, boolean capitalize) {

        if (str == null || str.length() == 0)
            return str;

        StringBuilder sb = new StringBuilder(str.length());

        if (capitalize)
            sb.append(Character.toUpperCase(str.charAt(0)));
        else
            sb.append(Character.toLowerCase(str.charAt(0)));

        sb.append(str.substring(1));

        return sb.toString();
    }

    public static String getMethodName(String fieldName) {

        char[] nameChar = fieldName.toCharArray();

        if (nameChar.length > 0
                && Character.isLowerCase(nameChar[0])
                && (nameChar.length == 1 || !Character.isUpperCase(nameChar[1]))) {
            fieldName = changeFirstCharacter(fieldName, true);
        }
        return fieldName;
    }
}
