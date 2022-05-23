package com.example.springboardapi.board.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    private static final String SENDER_EMAIL = "upskilling@bbubbush.com";

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String title) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String subject = "[게시글 삭제] “" + title + "” 이(가) 삭제 되었습니다.";
        String text = simpleDateFormat.format(new Date()) + "에 해당 게시글이 삭제되었습니다.";
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"UTF-8"); // use multipart (true)

            mimeMessageHelper.setSubject(subject); // Base64 encoding
            mimeMessageHelper.setText(text);
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
