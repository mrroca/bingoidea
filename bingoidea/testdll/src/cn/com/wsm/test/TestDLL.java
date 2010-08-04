package cn.com.wsm.test;

public class TestDLL
{
    static
    {
        System.loadLibrary("ykt");
    }


    public native int read_card_name_id();


    public int getCardNum()
    {
        return read_card_name_id();
    }


    public static void main(String[] args)
    {
        TestDLL test = new TestDLL();

        System.out.println(test.getCardNum());

    }
}
