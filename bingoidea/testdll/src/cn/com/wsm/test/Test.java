package cn.com.wsm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test
{

    public static void main(String[] args) throws IOException
    {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter characters,'q' to quit.");
        int i = 0 ;
        while (true)
        {
            c = (char) br.read();
            //System.out.println(c);
            System.out.println(i++);
            if (c == 'q')
            {
                break;
            }

        }
    }

}
