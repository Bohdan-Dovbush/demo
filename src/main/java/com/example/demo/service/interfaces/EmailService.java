package com.example.demo.service.interfaces;

import com.example.demo.validation.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
