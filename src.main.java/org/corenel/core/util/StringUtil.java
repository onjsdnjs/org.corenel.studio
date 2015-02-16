package org.corenel.core.util;

import org.apache.commons.lang.Validate;

public class StringUtil {

    public static String transformCharacter(String charValue, boolean useCase) {

        Validate.notEmpty(charValue);

        StringBuilder character = new StringBuilder(charValue.length());

        if (useCase) character.append(Character.toUpperCase(charValue.charAt(0)));
        else character.append(Character.toLowerCase(charValue.charAt(0)));

        character.append(charValue.substring(1));

        return character.toString();
    }

    public static String getMethod(String field) {

        char[] chars = field.toCharArray();

        if ((Character.isLowerCase(chars[0]) &&chars.length > 0) && (!Character.isUpperCase(chars[1]) || chars.length == 1)) {
            field = transformCharacter(field, true);
        }
        return field;
    }
}
