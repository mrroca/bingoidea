package cn.com.bsoft.wsm;

import javax.mail.*;

import org.apache.commons.codec.binary.Base64;

import java.util.*;
import java.io.*;
public class ReceiveMail
{
    // 处理任何一种邮件都需要的方法
    private static void handle(Message msg) throws Exception
    {
        System.out.println(" 邮件主题 :" + msg.getSubject());
        System.out.println(" 邮件作者 :" + msg.getFrom()[0].toString());
        System.out.println(" 发送日期 :" + msg.getSentDate());
    }


    // 处理文本邮件
//    private void handleText(Message msg) throws Exception
//    {
//        this.handle(msg);
//        System.out.println(" 邮件内容 :" + msg.getContent());
//    }


    // 处理 Multipart 邮件，包括了保存附件的功能
    private static void handleMultipart(Message msg) throws Exception
    {
        String disposition;
        BodyPart part;
        Multipart mp = (Multipart) msg.getContent();
        // Miltipart 的数量 , 用于除了多个 part, 比如多个附件
        int mpCount = mp.getCount();
        for (int m = 0; m < mpCount; m++)
        {
            handle(msg);
            part = mp.getBodyPart(m);
            disposition = part.getDisposition();
            // 判断是否有附件
            if (disposition != null && disposition.equals(Part.ATTACHMENT))
            {
                // 这个方法负责保存附件
                saveAttach(part);
            }
            else
            {
                // 不是附件，就只显示文本内容
                System.out.println(part.getContent());
            }
        }
    }


    private static void saveAttach(BodyPart part) throws Exception
    {
        // 得到未经处理的附件名字
        String temp = part.getFileName();
        // 除去发送邮件时，对中文附件名编码的头和尾，得到正确的附件名
        // （请参考发送邮件程序 SendMail 的附件名编码部分）
        String s = temp.substring(8, temp.indexOf("?="));
        // 文件名经过了 base64 编码 , 下面是解码
        String fileName = base64Decoder(s);
        System.out.println(" 有附件 :" + fileName);
        InputStream in = part.getInputStream();
        FileOutputStream writer = new FileOutputStream(new File(" c:\\123 "
                + "\\" + fileName));
        byte[] content = new byte[255];
        @SuppressWarnings("unused")
        int read = 0;
        while ((read = in.read(content)) != -1)
        {
            writer.write(content);
        }
        writer.close();
        in.close();
    }


    // base64 解码
    private static String base64Decoder(String s) throws Exception
    {
        Base64 decoder = new Base64();
        byte[] b = decoder.decode(s);
        return (new String(b));
    }


    public static void receive(String receiverMailBoxAddress, String username,
        String password)
    {
        // 本人用的是 yahoo 邮箱，故接受邮件使用 yahoo 的 pop3 邮件服务器
        String host = "pop.163.com";
        try
        {
            // 连接到邮件服务器并获得邮件
            Properties prop = new Properties();
            prop.put("mail.pop3.host", host);
            Session session = Session.getDefaultInstance(prop);
            Store store = session.getStore("pop3");
            store.connect(host, username, password);
            Folder inbox = store.getDefaultFolder().getFolder("INBOX");
            // 设置 inbox
            // 对象属性为可读写，这样可以控制在读完邮件后直接删除该附件inbox.open(Folder.READ_WRITE);
            Message[] msg = inbox.getMessages();
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            inbox.fetch(msg, profile);
            for (int i = 0; i < msg.length; i++)
            {
                // 标记此邮件的 flag 标志对象的 DELETED 位为 true, 可以在读完邮件后
                // 直接删除该附件，具体执行时间是在调用
                // inbox.close() 方法的时候
                msg[i].setFlag(Flags.Flag.DELETED, true);
                handleMultipart(msg[i]);
                System.out.println("****************************");
            }
            if (inbox != null)
            {
                // 参数为 true 表明阅读完此邮件后将其删除，更多的属性请参考 mail.jar
                // 的 API
                inbox.close(true);
            }
            if (store != null)
            {
                store.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        ReceiveMail.receive("pop.163.com", "chentan04296912", "19850429");
    }
}
