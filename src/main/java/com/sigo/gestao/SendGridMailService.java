package com.sigo.gestao;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridMailService {

    private SendGrid sg;

    SendGridMailService(@Value("${SENDGRID_API_KEY}") String apiKey) {
        this.sg = new SendGrid(apiKey);
    }

    public void sendMail(String mailTo, String message) throws IOException {
        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody("{\"personalizations\":[{\"to\":[{\"email\":\""+mailTo+"\"}],\"subject\":\"Status da Produção\"}],\"from\":{\"email\":\"sigo@indtextbr.com.br\",\"name\":\"SIGO\"},\"content\":[{\"type\":\"text/plain\",\"value\": \""+ message +"\"}]}");
            Response response = sg.api(request);

//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
