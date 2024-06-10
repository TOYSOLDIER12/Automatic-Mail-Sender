package ma.xproce.Sender;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSender implements EmailSenderInt {
    private String username;
    private String password;
    private Properties properties;

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp-relay.brevo.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp-relay.brevo.com");
    }
    @Override
    public void sendEmail(String recipientEmail, String subject, String messageText, String pdfFilePath) {
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageText);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is the attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pdfFilePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(pdfFilePath);
            multipart.addBodyPart(attachmentPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent to " + recipientEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        }
    }

