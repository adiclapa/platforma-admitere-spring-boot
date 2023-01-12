package com.code.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailSenderService {
    @Autowired
    private   JavaMailSender mailSender;

    //toEmail- email destinatie, Subject-subiectul e-mailului, Password-parola care a fost generata pentru un cont nou
    //Trebuie instantiata o clasa de tip EmailSenderService
    public void sendSimpleEmail(String toEmail,
                                String Subject,
                                String Password
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admitereatmc114c@gmail.com");
        message.setTo(toEmail);
        message.setText("Parola dumneavoastra este: " + Password);
        message.setSubject(Subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }


}
