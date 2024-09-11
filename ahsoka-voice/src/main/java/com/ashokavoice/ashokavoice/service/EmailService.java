package com.ashokavoice.ashokavoice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoConfirmacion(String correo,Integer token){
        String asunto="confirmacion de cuenta";
        String cuerpo="Tu codigo de confirmacion es: "+token;
        try{
            MimeMessage mensaje=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mensaje,true);
            helper.setTo(correo);
            helper.setSubject(asunto);
            helper.setText(cuerpo,true);
            mailSender.send(mensaje);
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
}
