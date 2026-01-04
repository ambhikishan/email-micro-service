package com.javamail;

import java.util.Stack;

public class HTML {
    public static String generateOtpEmailHtml(String otpCode) {
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

    public static String accountCreationHtml(){
        String htmlTemplate = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Verify Your Email</title>
                <style>
                    body { margin: 0; padding: 0; background-color: #f4f4f4; font-family: Arial, sans-serif; }
                    table { border-collapse: collapse; }
                    @media screen and (max-width: 600px) {
                        .container { width: 100% !important; }
                        .content { padding: 20px !important; }
                    }
                </style>
            </head>
            <body style="background-color: #f4f4f4; margin: 0; padding: 0;">
                <table width="100%" cellpadding="0" cellspacing="0" border="0" style="background-color: #f4f4f4; padding: 20px 0;">
                    <tr>
                        <td align="center">
                            <table class="container" width="600" cellpadding="0" cellspacing="0" border="0" style="background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                                <tr>
                                    <td align="center" style="background-color: #007bff; padding: 30px;">
                                        <h1 style="color: #ffffff; margin: 0; font-size: 24px;">ChatApp</h1>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="content" style="padding: 40px 30px; text-align: center;">
                                        <h2 style="color: #333333; margin-top: 0; font-size: 22px;">Account Created Successfully!</h2>
                                        <p style="color: #666666; font-size: 16px; line-height: 1.5; margin-bottom: 30px;">
                                            Welcome aboard! We are excited to have you join us. Before you can start using your account, we need to verify your email address.
                                        </p>
                                        <table width="100%" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td align="center">
                                                    <a href="{{verification_link}}" style="display: inline-block; padding: 14px 30px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 16px;">Verify My Email</a>
                                                </td>
                                            </tr>
                                        </table>
                                        <p style="color: #666666; font-size: 14px; line-height: 1.5; margin-top: 30px;">
                                            This link will expire in 24 hours. If you did not create an account with us, please ignore this email.
                                        </p>
                                        <p style="color: #999999; font-size: 12px; line-height: 1.5; margin-top: 30px; border-top: 1px solid #eeeeee; padding-top: 20px;">
                                            If the button above doesn't work, paste this link into your browser:<br>
                                            
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="background-color: #f8f9fa; padding: 20px; color: #999999; font-size: 12px;">
                                        &copy; 2026 ChatApp. All rights reserved.
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """;
        return   htmlTemplate;
    }
}
