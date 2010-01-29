package cn.com.bsoft.test;

import java.util.HashMap;
import java.util.Iterator;

public class Tests
{

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        HashMap<String, String> hm = new HashMap<String, String>();
        HashMap<String, String> hm2 = new HashMap<String, String>();
        hm.put("name1", "val1");
        hm.put("name2", "val2");
        hm.put("name3", "val3");
        hm2=(HashMap<String,String>)hm.clone();
        hm.put("name4", "val4");
        // hm.values();
        // hm.keySet();
        for (Iterator<String> iterator = hm.keySet().iterator(); iterator
            .hasNext();)
        {
            String s = iterator.next();
            System.out.println(s + "---"+hm2.get(s));

        }
    }

}
