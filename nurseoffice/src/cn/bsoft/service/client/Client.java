package cn.bsoft.service.client;

public class Client
{
    public static void main(String[] args)

    {

        ReadCardServer service = new ReadCardServerService().getReadCardServerPort();

        String s = service.readCard();

        System.out.println(s);

    }

}
