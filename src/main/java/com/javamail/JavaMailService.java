package com.javamail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


import java.util.Properties;

import static com.javamail.HTML.accountCreationHtml;
import static com.javamail.HTML.generateOtpEmailHtml;

public class JavaMailService {
    // --- START OF CONFIGURATION ---
    // Update these fields with your email provider's details and your credentials

    // IMAP server host. Example for Gmail: "imap.gmail.com"
    // Example for Outlook: "outlook.office365.com"
    private static final String HOST = "smtp.gmail.com";

    // IMAP server port. 993 is standard for IMAPS (IMAP over SSL)
    private static final String PORT = "587";

    // Your full email address
    private static final String USERNAME = "testcodehibernate@gmail.com";

    // Your "App Password". See README.md for details.
    // DO NOT use your regular account password.
    private static final String PASSWORD = "odsf zrgf tfpz bmhg";

    public void sendMail(DTO otp) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Use TLS
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        // --- Session Creation ---
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(otp.getEmail()));
            message.setSubject("Email from javaMail");
            String htmlContent = generateOtpEmailHtml(otp.getOtp());
            message.setContent(htmlContent,"text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("email sent successfully");
        }

        // The HTML content string
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void sendAccountCreationMail(String email)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Use TLS
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        // --- Session Creation ---
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account created Successfully");
            String htmlContent = accountCreationHtml();
            message.setContent(htmlContent,"text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("email sent successfully");
        }

        // The HTML content string
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    }

