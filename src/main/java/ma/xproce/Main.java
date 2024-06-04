package ma.xproce;

import ma.xproce.FileReader.FileReaderStrategy;
import ma.xproce.FileReader.TextFileReader;
import ma.xproce.Sender.EmailSender;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "emails.txt"; // Path to your file
        FileReaderStrategy emailFileReader = new TextFileReader();
        List<String> emails = emailFileReader.readFile(filePath);

        // Sender email credentials
        String senderEmail = "your-email@gmail.com";
        String senderPassword = "your-password";
        EmailSender emailSender = new EmailSender(senderEmail, senderPassword);

        // Email subject and message
        String subject = "Subject of the Email";
        String message = "Hello, this is a test email from Java.";

        for (String email : emails) {
            emailSender.sendEmail(email, subject, message);
        }
    }
}
