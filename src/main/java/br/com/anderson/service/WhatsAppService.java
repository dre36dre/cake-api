package br.com.anderson.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class WhatsAppService {

    private final String ACCOUNT_SID = "SEU_SID";
    private final String AUTH_TOKEN = "SEU_TOKEN";
    private final String FROM = "whatsapp:+14155238886"; // Twilio sandbox

    public void enviarMensagem(String numero, String texto) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + numero),
                new com.twilio.type.PhoneNumber(FROM),
                texto
        ).create();
    }
}
