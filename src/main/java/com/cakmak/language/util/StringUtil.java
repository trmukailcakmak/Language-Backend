package com.cakmak.language.util;

import java.util.Locale;

public class StringUtil {

    public static final String EMPTY = "";

    private StringUtil() {
    }

    public static boolean isNullOrEmpty(String text) {
        if (text != null && !text.trim().equals(EMPTY)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static String setEmptyIfNull(String value) {

        return value != null ? value : EMPTY;
    }

    public static boolean isNotEmpty(String value) {

        return !EMPTY.equals(value);
    }

    public static String safeTrim(String val) {
        return val != null ? val.trim() : val;
    }

    /**
     * @param value       which will be padded
     * @param padKey      pad key
     * @param totalLength length of padded value
     * @return padded value
     */
    public static String leftPad(String value, char padKey, int totalLength) {
        String padded = value;
        if (!isNullOrEmpty(value) && !isNullOrEmpty(padKey + EMPTY) && value.length() < totalLength) {
            StringBuilder sample = new StringBuilder(EMPTY);
            for (int i = 0; i < totalLength; i++) {
                sample.append(padKey).append(EMPTY);
            }

            padded = sample.substring(value.length()) + value;
        }

        return padded;
    }

    /**
     * @param value     which will be fixed
     * @param fixLength length of new value
     * @return This method fixes your value from left
     */
    public static String fixLengthLeft(String value, int fixLength) {
        String trimmed = value;
        if (!isNullOrEmpty(value) && value.length() > fixLength) {
            trimmed = trimmed.substring(value.length() - fixLength);
        }

        return trimmed;
    }

    /**
     * @param value sentence which will split with " "
     * @return makes whole sentence first letter capital
     */
    public static String makeFirstLetterCapital(String value) {
        String result = value.toLowerCase(Locale.forLanguageTag("tr-TR"));
        String[] stringArray = result.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : stringArray) {
            String firstChar = s.substring(0, 1).toUpperCase();
            builder.append(firstChar).append(s.substring(1)).append(" ");
        }

        return builder.toString().trim();
    }

    public static String handleNullStringTrim(String myString) {
        if (myString == null) {
            return "";
        } else {
            return myString.trim();
        }
    }

    public static String toUpperCase(String input) {
        return StringUtil.isNullOrEmpty(input) ? input : input.toUpperCase();
    }

    public static String toLowerCase(String input) {
        return StringUtil.isNullOrEmpty(input) ? input : input.toLowerCase();
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replace("+", "");
        phoneNumber = phoneNumber.replace("-", "");
        return phoneNumber;
    }

    public static String maskPin(String text) {
        text = maskPinCode(text);
        return text;
    }

    public static String maskPinCode(String text) {
        return text != null ?
                text.replaceAll("\"pinCode\":.*\"\\d{6}\"", "\"pinCode\": \"***\"").replaceAll("\"pin_number\":.*\".{6}\"", "\"pin_number\": \"***\"").replaceAll("\"oldPin\":.*\".{6}\"", "\"oldPin\": \"***\"").replaceAll("\"password\":.*\".{6}\"", "\"password\": \"***\"")
                : null;
    }

    public static String maskPassword(String text) {
        return text != null ?
                text.replaceAll("\"password\":.*\".*\"", "\"password\": \"***\"")
                : null;
    }

    public static String maskPinNumber(String text) {
        if (text != null) {
            return text.replaceAll("\"pin_number\":\".{6}\"", "\"pin_number\": \"***\"");
        }
        return text;
    }

    public static String maskPhone(String phone) {

        return isNullOrEmpty(phone) ? phone
                : maskString(phone, phone.length() - 7, phone.length() - 2, '*');
    }


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private static String maskString(String strText, int start, int end, char maskChar) {

        try {
            if (isNullOrEmpty(strText)) {
                return strText;
            }

            if (start < 0) {
                start = 0;
            }

            if (end > strText.length()) {
                end = strText.length();
            }

            if (start > end) {
                int tmp = start;
                start = end;
                end = tmp;
            }

            int maskLength = end - start;

            if (maskLength == 0) {
                return strText;
            }

            StringBuilder sbMaskString = new StringBuilder(maskLength);

            for (int i = 0; i < maskLength; i++) {
                sbMaskString.append(maskChar);
            }

            return strText.substring(0, start) + sbMaskString.toString()
                    + strText.substring(start + maskLength);

        } catch (Exception e) {
            return strText;
        }

    }


}
