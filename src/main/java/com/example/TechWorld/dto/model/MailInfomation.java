package com.example.TechWorld.dto.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfomation {

    String from;
    String to;

    String body;
    String subject;

    String attachments;

    public MailInfomation(String to, String subject, String body) {
        this.from = "Martfury Shop <martfury@gmail.com>";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
