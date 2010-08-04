package com.bsoft.szdc.send.util;

import java.util.List;

public class WordFilter
{

    public static String filterWord(String str)
    {
        String val = (str == null ? "" : str);
        if (val.indexOf("<") != -1)
        {
            val = val.replaceAll("<", "≤");
        }
        if (val.indexOf(">") != -1)
        {
            val = val.replaceAll(">", "≥");
        }
        if (val.indexOf("'") != -1)
        {
            val = val.replaceAll("\\'", "＇");
        }
        return val;
    }


    public static boolean filterColumn(String columnname, List<?> filters)
    {
        return filters.contains(columnname);
    }
}
