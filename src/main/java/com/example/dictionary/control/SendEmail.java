package com.example.dictionary.control;


import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Component
public class SendEmail {
    public void sendEmail(String ip)
    {
        // 收件人电子邮箱
        String to = "chenfei386@163.com";
        // 发件人电子邮箱
        String from = "chenfei386@163.com";
        // 指定发送邮件的主机为
        String host = "pop3.163.com";
        // 授权
        String MAIL_SMTP_AUTH = "true";
        // 授权密码
        String password="FYLGCSPUFPPFZOPD";
        // 标题
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String NOTIFICATION_MAIL_TITLE = "外网IP地址:".concat(formatter.format(date));
        // 内容
        String NOTIFICATION_MAIL_CONTENT = "请查看：";

        Properties properties = new Properties();
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 设置消息体
            message.setText("This is actual message");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
