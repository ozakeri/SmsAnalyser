package gap.com.smsapplication.utils;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import gap.com.smsapplication.Result;
import gap.com.smsapplication.constant.Constants;

public class Converter {

    private static List<Result> resultList = new ArrayList<>();

    @SuppressLint("DefaultLocale")
    public static List<Result> saderat(String message, String bankName, String date) {
        String[] lines = message.split("\\r?\\n");
        if (message.contains(Constants.Remaining)) {
            Result result = new Result();
            for (String ss : lines) {
                result.setBankName(bankName);
                if (ss.contains("-") && ss.contains(",")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                }
                if (ss.contains("+") && ss.contains(",") && !ss.contains("-")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setDeposit(String.format("%,d", Integer.parseInt(number)));
                }
                if (ss.contains(Constants.Remaining)) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                }
                result.setDate(date);

            }
            resultList.add(result);
        }
        return resultList;
    }

    @SuppressLint("DefaultLocale")
    public static List<Result> melli(String message, String bankName, String date) {
        String[] lines = message.split("\\r?\\n");
        if (message.contains(Constants.Remaining)) {
            Result result = new Result();
            for (String ss : lines) {
                result.setBankName(bankName);


                if (ss.contains("-") && ss.contains(",")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                }
                if (ss.contains("+") && ss.contains(",") && !ss.contains("-")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setDeposit(String.format("%,d", Integer.parseInt(number)));
                }

                if (ss.contains(Constants.Remaining)) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                }
                result.setDate(date);

            }
            resultList.add(result);
        }

        return resultList;
    }

    @SuppressLint("DefaultLocale")
    public static List<Result> qavamin(String message, String bankName, String date) {
        String[] lines = message.split("\\r?\\n");
        if (message.contains(Constants.Remaining)) {
            Result result = new Result();
            for (String ss : lines) {

                result.setBankName(bankName);
                if (ss.contains("-") && ss.contains(",")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                }
                if (ss.contains("+") && ss.contains(",")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setDeposit(String.format("%,d", Integer.parseInt(number)));
                }

                try {
                    ss = lines[5];
                    if (ss.contains(",")) {
                        String number = stripNonDigits(ss.replaceAll(",", ""));
                        result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
                result.setDate(date);
            }

            resultList.add(result);
        }
        return resultList;
    }

    @SuppressLint("DefaultLocale")
    public static List<Result> resalat(String message, String bankName, String date) {
        String[] lines = message.split("\\r?\\n");
        Result result = new Result();
        if (message.contains(Constants.Deposit) || message.contains(Constants.Harvest)) {
            for (String ss : lines) {
                result.setBankName(bankName);

                if (message.contains(Constants.Deposit)) {
                    if (ss.contains("مبلغ")) {
                        String number = stripNonDigits(ss.replaceAll(",", ""));
                        result.setDeposit(String.format("%,d", Integer.parseInt(number)));
                    }
                }

                if (message.contains(Constants.Harvest)) {
                    if (ss.contains("مبلغ")) {
                        String number = stripNonDigits(ss.replaceAll(",", ""));
                        result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                    }

                    if (ss.contains("ريال")) {
                        String number = stripNonDigits(ss.replaceAll(",", ""));
                        result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                    }
                }

                if (ss.contains("موجودي")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                }

                if (ss.contains(Constants.Remaining)) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                }

                result.setDate(date);
            }

            resultList.add(result);
        }
        return resultList;
    }

    @SuppressLint("DefaultLocale")
    public static List<Result> sepah(String message, String bankName, String date) {
        String[] lines = message.split("\\r?\\n");
        if (message.contains(Constants.Remaining)) {
            Result result = new Result();
            for (String ss : lines) {
                result.setBankName(bankName);
                if (ss.contains("-") && ss.contains(",")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setHarvest(String.format("%,d", Integer.parseInt(number)));
                }
                if (ss.contains("+") && ss.contains(",") && !ss.contains("-")) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setDeposit(String.format("%,d", Integer.parseInt(number)));
                }

                if (ss.contains(Constants.Remaining)) {
                    String number = stripNonDigits(ss.replaceAll(",", ""));
                    result.setRemaining(String.format("%,d", Integer.parseInt(number)));
                }
                result.setDate(date);
            }
            resultList.add(result);
        }

        return resultList;
    }

    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */) {
        final StringBuilder sb = new StringBuilder(
                input.length() /* also inspired by seh's comment */);
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
