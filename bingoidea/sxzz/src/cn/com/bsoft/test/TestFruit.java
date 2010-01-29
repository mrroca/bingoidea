package cn.com.bsoft.test;

public class TestFruit
{

    public static void main(String[] args)
    {
        IFruit fruit = new Apple();
        fruit.productFruit();
        System.out.println(fruit.getClass().toString());
    }
}
