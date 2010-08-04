package com.nurse.test;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.HeapMemoryBlock;

public class TestJnative
{

    private JNative n = null;


    public int getCardNum() throws IllegalAccessException, NativeException
    {
        Pointer num = new Pointer(new HeapMemoryBlock(25));
        System.loadLibrary("ykt");
        try
        {
            n = new JNative("ykt", "read_cardno");
            n.setRetVal(Type.INT);
            n.setParameter(0, num);
            n.invoke();
            if (n.getRetValAsInt() == 0)
            {
                System.out.println(num.getAsString());
            }
            return Integer.parseInt(n.getRetVal());
        }
        finally
        {
            if (n != null)
            {
                n.dispose();
                num.dispose();
            }
        }
    }
    public int getPersonInfo() throws IllegalAccessException, NativeException
    {
        Pointer pname = new Pointer(new HeapMemoryBlock(25));
        Pointer pid = new Pointer(new HeapMemoryBlock(25));
        System.loadLibrary("ykt");
        try
        {
            n = new JNative("ykt", "read_card_name_id");
            n.setRetVal(Type.INT);
            n.setParameter(0, pname);
            n.setParameter(1, pid);
            n.invoke();
            if (n.getRetValAsInt() == 0)
            {
                System.out.println(pname.getAsString());
                System.out.println(pid.getAsString());
            }
            return Integer.parseInt(n.getRetVal());
        }
        finally
        {
            if (n != null)
            {
                n.dispose();
                pname.dispose();
                pid.dispose();
            }
        }
    }


    public static void main(String[] args)
    {
        TestJnative tj = new TestJnative();
        try
        {
            tj.getPersonInfo();
            tj.getCardNum();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (NativeException e)
        {
            e.printStackTrace();
        }
    }
}
