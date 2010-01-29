package cn.com.bsoft.test;

import cn.com.bsoft.util.SystemInitImpl;

public class Test
{
    public static void main(String[] args)
    {
        SystemInitImpl.init();

//        UserDaoImpl dao = (UserDaoImpl) SystemInitImpl.createFactory().getBean(
//            "userDAO");
//
//        List<User> list = null;
//        try
//        {
//            //list = dao.getAll();
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//        if (list != null)
//        {
//            for (int i = 0; i < list.size(); i++)
//            {
//                System.out.println(((User) list.get(i)).getUser());
//                System.out.println(((User) list.get(i)).getPassword());
//            }
//        }
//
//        User user = (User) SystemInitImpl.createFactory().getBean("user");
//        user.setUser("david");
//        user.setPassword("admin");
//        try
//        {
//            //dao.deleteMessage(user);
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
        // MessageDaoImpl mdi = (MessageDaoImpl) SystemInit.createFactory()
        // .getBean("messageDAO");
        // List<Message> li = mdi.getAllMessage();
        // if (li != null)
        // {
        // for (int i = 0; i < li.size(); i++)
        // {
        // if (i == 10)
        // break;
        // // System.out.println(((Message) li.get(i)).getDoctorcode());
        // }
        // }
        // Message message = (Message) SystemInit.createFactory().getBean(
        // "message");
        // // message.setHospitalcode("3004");
        // // message.setDoctorcode("C050");
        // List<Message> lis = mdi.getMessageByCondition(message);
        // if (lis != null)
        // {
        // for (int i = 0; i < lis.size(); i++)
        // {
        // System.out.println(((Message) lis.get(i)).getWorkdate());
        // }
        // }
    }
}
