package ma.xproce;

import ma.xproce.FileReader.FileReaderStrategy;
import ma.xproce.FileReader.TextFileReader;
import ma.xproce.Sender.EmailSender;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.List;

public class Main {
    public static void main(String[] args) {



        String filePath = "src/main/java/ma/xproce/emails.txt";
        String pdfFilePath = "src/main/java/ma/xproce/cv.pdf";
        String configFilePath = "src/main/java/ma/xproce/config.txt";
        FileReaderStrategy emailFileReader = new TextFileReader();
        List<String> emails = emailFileReader.readFile(filePath);
        List<String> config = emailFileReader.readFile(configFilePath);


        String full_Name = config.get(0);
        String Myemail = config.get(1);
        String phone = config.get(2);
        String github = config.get(3);
        String addresse = config.get(4);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        // Sender email credentials
        String senderEmail = "7610c5001@smtp-brevo.com";
        String senderPassword = "B70qx8rv9z2b4NwH";
        EmailSender emailSender = new EmailSender(senderEmail, senderPassword);

        // Email subject and message
        String subject = "Demande de stage";





        for (String email : emails) {

            String domain = email.split("@")[1];

            String enterprise_name = domain.split("\\.")[0];

            String messageText = full_Name+"\n" +phone + "\n" + Myemail +"\n" + github + "\n" + addresse + "\n" + "\t\t\t\t\t\t\t\t\tCasablanca, le "+ formattedDate +"\n" + "\n" + "A L’attention de M. RH de l’entreprise " + enterprise_name + "\n" + "                          Objet : Demande de stage au sein du Service IT d’une période \n" + "Allant de 01/07/2024 au 30/08/2024\n" + "\t\t\t\t\t\t\t\n" +
                    "Etant en 4éme année d’ingénieur d’ETAT en informatique et réseaux option MAIGE, je serai ravie de passer mon stage PFA au sein de votre entreprise pour la période ci-dessus.\n" +
                    "Dotée d’un sens d’engagement et de responsabilité, d’une capacité d’adaptation et de résilience ; maitrisant ainsi les langages de programmation (C++, C#, JEE…) et la programmation mobile ainsi que des outils de base de données comme (MySQL, SQL Server, Oracle...), je saurai mener  à bien les missions qui me seront confiées.\n" +
                    "Dans l’attente de votre retour, je vous prie, Monsieur, d’agréer mes sincères salutations.\n" +
                    "\t\t\t\t\t\t\t\t\t"+full_Name;


            emailSender.sendEmail(email, subject, messageText, pdfFilePath);
        }
    }
}
