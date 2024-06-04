package ma.xproce.Sender;

public interface EmailSenderInt {
    public void sendEmail(String recipientEmail, String subject, String messageText, String pdfFilePath);
}
