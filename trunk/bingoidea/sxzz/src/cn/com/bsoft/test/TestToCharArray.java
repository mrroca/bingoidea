package cn.com.bsoft.test;

import static cn.com.bsoft.util.SystemPrint.*;


public class TestToCharArray
{
    public static void main(String[] args)
    {
//        String s = "a阿f";
//        s.toCharArray();
//        char[] schar = new char[1];
//        byte[] temp = {};
//        try
//        {
//            temp = s.getBytes("UTF-8");
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        println(schar.length);
//        // for(int i=0 ; i<schar.length ;i++){
//        // println(schar[i]);
//        // }
//        for (int i = 0; i < temp.length; i++)
//        {
//            // println(temp[i]);
//        }
//        schar[0] = "600".charAt(1);// (char)temp.length;
//        // println(String.valueOf(schar).getBytes().length);
//        try
//        {
//            println(schar[0]);
//            String tempstr = new String(
//                String.valueOf(schar).getBytes("UTF-8"), "UTF-8");
//            char[] mchar = new char[1];
//            mchar = tempstr.toCharArray();
//            println(mchar[0]);
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//
//        // println(new String(schar));
//        // println(new String(temp));
        String[] s = {"123"};
        println(s[0].getBytes().length);
        String ss = " 12312 123213 123213 ";
        println(ss);
        println(ss.trim());

    }
}
