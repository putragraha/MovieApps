package com.nsystem.ntheatre.util;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertDate {

    public static final String MMM_DD_COMMA_YYYY = "MMM dd, yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    @Nullable
    public static String to(String sourceDate, String oldFormat, String newFormat) throws ParseException {
        Date date = new SimpleDateFormat(oldFormat, Locale.ENGLISH).parse(sourceDate);

        if (date == null)
            return null;
        return new SimpleDateFormat(newFormat, Locale.ENGLISH).format(date);
    }

}
