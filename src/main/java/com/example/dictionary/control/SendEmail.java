package com.example.dictionary.control;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
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
        String USER_NAME = "chenfei386@163.com";
        // 发件人电子邮箱
        String Recipient = "chenfei386@163.com";
        // 指定发送邮件的主机为
        String host = "smtp.163.com";
        // 授权
        String MAIL_SMTP_AUTH = "true";
        // 授权密码
        String PWD_CODE="FYLGCSPUFPPFZOPD";
        // 标题
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String NOTIFICATION_MAIL_TITLE = "外网IP地址:".concat(formatter.format(date));
        // 内容
        String NOTIFICATION_MAIL_CONTENT = "请查看：".concat(ip);

        Properties properties = new Properties();
        //获取163邮箱smtp服务器的地址，
        properties.setProperty("mail.host", host);
        //是否进行权限验证。
        properties.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH);
        //0.2确定权限（账号和密码）
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //填写自己的163邮箱的登录帐号和授权密码（不是登录密码）。
                return new PasswordAuthentication(USER_NAME,PWD_CODE);
            }
        };
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,authenticator);

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(USER_NAME));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(Recipient));

            // Set Subject: 头部头字段
            message.setSubject(NOTIFICATION_MAIL_TITLE);

            // 设置消息体
            //设置编码，防止发送的内容中文乱码。
            message.setContent(NOTIFICATION_MAIL_CONTENT, "text/html;charset=UTF-8");
            message.setText(NOTIFICATION_MAIL_CONTENT);

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
