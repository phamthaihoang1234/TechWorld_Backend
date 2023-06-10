package com.example.TechWorld.service;

import com.example.TechWorld.dto.model.MailInfomation;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendMailService {
    void run();

    void queue(String to, String subject, String body);

    void queue(MailInfomation mail);

    void send(MailInfomation mail) throws MessagingException, IOException;
}
