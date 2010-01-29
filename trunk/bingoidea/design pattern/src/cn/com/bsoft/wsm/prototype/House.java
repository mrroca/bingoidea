package cn.com.bsoft.wsm.prototype;

import java.util.*;

public class House
{
    private Vector<AbstractFurniture> vector;


    public House()
    {
        vector = new Vector<AbstractFurniture>();
    }


    public void addFurniture(AbstractFurniture furniture)
    {
        vector.addElement(furniture);
        System.out.println("现有家具....");
        Enumeration<AbstractFurniture> enumeration = vector.elements();
        while (enumeration.hasMoreElements())
        {
            AbstractFurniture f = (AbstractFurniture) enumeration.nextElement();
            f.draw();
        }
        System.out.println();
    }
}