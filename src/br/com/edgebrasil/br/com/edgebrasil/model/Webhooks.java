package br.com.edgebrasil.br.com.edgebrasil.model;

/**
 * Created by DIEGO on 17/05/2017.
 * ProjectName TesteMoip.
 */
public class Webhooks {
    private String requestTo;
    private Integer codeStatus;

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

}
