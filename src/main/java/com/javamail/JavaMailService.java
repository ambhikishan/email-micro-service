package com.javamail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
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
    private static final String PASSWORD = "yeoi jxtb cxex knhv";

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
    public String generateOtpEmailHtml(String otpCode) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>OTP Verification</title>
        </head>
        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;">
            
            <table role="presentation" width="100%%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" style="padding: 40px 10px;">
                        
                        <table role="presentation" width="600" border="0" cellspacing="0" cellpadding="0" style="background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); overflow: hidden;">
                            
                            <tr>
                                <td style="background-color: #0056b3; padding: 20px; text-align: center;">
                                    <h1 style="color: #ffffff; margin: 0; font-size: 24px;">Your App Name</h1>
                                </td>
                            </tr>

                            <tr>
                                <td style="padding: 40px 30px;">
                                    <h2 style="color: #333333; margin-top: 0;">Verification Required</h2>
                                    <p style="color: #666666; font-size: 16px; line-height: 1.5;">
                                        Hello,
                                    </p>
                                    <p style="color: #666666; font-size: 16px; line-height: 1.5;">
                                        You requested a password reset or login attempt. Please use the verification code below to proceed.
                                    </p>
                                    
                                    <div style="margin: 30px 0; text-align: center;">
                                        <span style="display: inline-block; font-size: 32px; font-weight: bold; color: #0056b3; letter-spacing: 5px; background-color: #e7f3ff; padding: 15px 30px; border-radius: 5px; border: 1px dashed #0056b3;">
                                            %s
                                        </span>
                                    </div>

                                    <p style="color: #666666; font-size: 14px;">
                                        This code is valid for <strong>5 minutes</strong>. Do not share this code with anyone.
                                    </p>
                                    
                                    <p style="color: #666666; font-size: 14px; margin-top: 30px;">
                                        If you did not request this code, please ignore this email or contact support.
                                    </p>
                                </td>
                            </tr>
                            
                            <tr>
                                <td style="background-color: #f8f9fa; padding: 20px; text-align: center; border-top: 1px solid #eeeeee;">
                                    <p style="color: #999999; font-size: 12px; margin: 0;">
                                        &copy; 2025 Your Company Name. All rights reserved.
                                    </p>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
            
        </body>
        </html>
        """.formatted(otpCode); // Injects the OTP into the %s placeholder
    }

    }

