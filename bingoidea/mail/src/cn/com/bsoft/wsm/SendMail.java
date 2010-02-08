package cn.com.bsoft.wsm;

//SendMail.java
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;
import javax.activation.*;

import org.apache.commons.codec.binary.Base64;

public class SendMail
{
    public static void send(String customMailBoxAddress, String username,
        String password, String serverMailBoxAddress, String subject,
        String attachmentPath, String attachmentName)
    {
        // 这里面使用新浪作为发送邮件的邮件服务器，其他的 smtp 服务器可以到相关网 站
        // 上查到。
        String host = "smtp.163.com";
        // 发送方邮箱地址（如 BlogJava2006@blog.com.cn . ）
        String from = customMailBoxAddress;
        // 收件人邮箱地址
        String to = serverMailBoxAddress;
        // 发送者的邮箱用户名
        String user = username;
        // 发送者的邮箱密码
        String ps = password;
        Properties props = new Properties();
        // 设置发送邮件的邮件服务器的属性（这里使用新浪的 smtp 服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有 这一条）
        props.put("mail.smtp.auth", "true");
        // 用刚刚设置好的 props 对象构建一个 session
        Session session = Session.getDefaultInstance(props);
        // 有了这句便可以在发送邮件的过程中在 console处显示过程信息，供调试使 用（有的时候网络连通性不够好，发送邮件可能会有延迟，在这里面会有所提示，所以最好是加上这句，避免盲目的等待）
        session.setDebug(true);
        // 定义消息对象
        MimeMessage message = new MimeMessage(session);
        try
        {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                to));
            message.setSubject(subject);
            // 向 multipart 对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(" 邮件的具体内容在此 ");
            multipart.addBodyPart(contentPart);
            // 添加附件
            BodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            // 注意：下面定义的 enc 对象用来处理中文附件名，否则名称是中文的附件 在 邮箱里面显示的会是乱码，
            Base64 enc = new Base64();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setFileName("=?GBK?B?"
                    + enc.encode(attachmentName.getBytes()) + "?=");
            multipart.addBodyPart(messageBodyPart);
            // 将 multipart 对象放到 message 中
            message.setContent(multipart);
            // 发送邮件
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, ps);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        SendMail.send("smtp.163.com", "chentan04296912", "19850429", "60.191.54.179", "hello", "", "");
    }
}
