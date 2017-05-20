package br.com.edgebrasil.br.com.edgebrasil.util;

import br.com.edgebrasil.br.com.edgebrasil.model.Webhooks;

/**
 * Created by DIEGO on 17/05/2017.
 * ProjectName TesteMoip.
 */
public class Converter {
    public Webhooks getWebhooksFromString(String linha){
        Webhooks webhooks = new Webhooks();
        webhooks.setRequestTo(linha.split("request_to=\"")[1].split("\"")[0]);
        webhooks.setCodeStatus(Integer.valueOf(linha.split("response_status=\"")[1].split("\"")[0]));
        return webhooks;
    }
}
