package cn.com.bsoft.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;

import cn.com.bsoft.model.DoctorScheduler;

public class Test2
{
    public static void main(String[] args) throws ClassNotFoundException,
        InstantiationException, IllegalArgumentException,
        InvocationTargetException, SecurityException, NoSuchFieldException,
        IllegalAccessException
    {
        DoctorScheduler ds = new DoctorScheduler();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("doctorcode", "abc");
        map.put("hospitalcode", "20");
        Class<?> c = Class.forName("cn.com.bsoft.model.DoctorScheduler");
        for (Entry<String, String> entry : map.entrySet())
        {
            Field field = c.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(ds, entry.getValue());

        }
        Method[] mh = c.getDeclaredMethods();
        System.out.println(mh.length);
        System.out.println(ds.getDoctorcode());
        System.out.println(ds.getHospitalcode());
    }
}
