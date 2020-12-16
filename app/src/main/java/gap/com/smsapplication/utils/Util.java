package gap.com.smsapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String dateFormat = "yyyy-MM-dd- HH:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);


    public static String latinNumberToPersian(String input) {
        String output = input.replaceAll("0", "٠");
        output = output.replaceAll("1", "١");
        output = output.replaceAll("2", "٢");
        output = output.replaceAll("3", "٣");
        output = output.replaceAll("4", "۴");
        output = output.replaceAll("5", "۵");
        output = output.replaceAll("6", "۶");
        output = output.replaceAll("7", "٧");
        output = output.replaceAll("8", "٨");
        output = output.replaceAll("9", "٩");
        return output;
    }

    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

    public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }
}
