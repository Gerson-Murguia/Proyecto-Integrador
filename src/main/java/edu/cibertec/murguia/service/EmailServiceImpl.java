package edu.cibertec.murguia.service;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static edu.cibertec.murguia.constant.EmailConstant.*;

@Service
public class EmailServiceImpl {

    @Value("${spring.mail.username}")
    private String byUsername;
    @Value("${spring.mail.password}")
    private String byPassword;

    @Async
    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, byUsername, byPassword);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, 465);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(Smtp_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }

    private Message createEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        //copia a mi correo personal
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CC_EMAIL));
        message.setSubject(EMAIL_SUBJECT);
        message.setSentDate(new Date());
        message.saveChanges();
        message.setText("Hola " + firstName + ", \n\n La nueva clave es: " + password + "\n\n Equipo de Soporte - Verificacion Facial \n\n "+ "No guardamos tu clave, ni la compartimos con nadie, por tu seguridad");
        return message;
    }
}
