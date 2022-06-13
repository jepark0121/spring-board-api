package com.example.springboardapi.board.service;

import com.example.springboardapi.board.model.Mail;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Log4j2
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    private static final String SENDER_EMAIL = "upskilling@bbubbush.com";

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Mail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"UTF-8"); // use multipart (true)

            mimeMessageHelper.setSubject(mail.getSubject()); // Base64 encoding
            mimeMessageHelper.setText(mail.getText());
            mimeMessageHelper.setFrom(SENDER_EMAIL);
            mimeMessageHelper.setTo(SENDER_EMAIL);

            javaMailSender.send(mimeMessage);

            log.info("MailServiceImpl.sendMail() :: SUCCESS");
        } catch (Exception e) {
            log.info("MailServiceImpl.sendMail() :: FAILED");
            e.printStackTrace();
        }
    }
}
