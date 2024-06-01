package app.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

public class MailHandler {

    // Sender details
    String sender = "nifjel@gmail.com";
    String company = "Fog Byggemarked";

    // Mail template IDs
    String confirm = "d-66f65ce11bfc45d5a3089f71a10c376c";
    String receipt = "d-bd98a8b37830426f80bab6ea93ad46f7";

    // Recipient for show purposes only
    String recipient = "nifjel@gmail.com";

    public void paymentEmail( String name ) throws IOException {

        Email from = new Email(this.sender);
        from.setName(this.company);

        Mail mail = new Mail();
        mail.setFrom(from);

        String API_KEY = System.getenv("SENDGRID_API_KEY");

        Personalization personalization = new Personalization();

        personalization.addTo(new Email(this.recipient));
        personalization.addDynamicTemplateData("name", "Anders Henningsen");
        mail.addPersonalization(personalization);

        mail.addCategory("carportapp");

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");

            mail.templateId = this.confirm;
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending mail");
            throw ex;
        }
    }

    public void receiptEmail( String name, String orderinfo) throws IOException {

        Email from = new Email(this.sender);
        from.setName(this.company);

        Mail mail = new Mail();
        mail.setFrom(from);

        String API_KEY = System.getenv("SENDGRID_API_KEY");

        Personalization personalization = new Personalization();

        personalization.addTo(new Email(this.recipient));
        personalization.addDynamicTemplateData("name", "");
        personalization.addDynamicTemplateData("orderinfo", "carport");
        mail.addPersonalization(personalization);

        mail.addCategory("carportapp");

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");

            mail.templateId = this.receipt;
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending mail");
            throw ex;
        }
    }
}
