package com.oznhub.oznhubcollectdataserviceapi.service.process;

import java.io.IOException;

public interface EmailService {
    public boolean sendEmail(String toEmail, String subject, String body) throws IOException;
    public boolean sendVerificationCode(String toEmail, String subject, String body) throws IOException;
}
