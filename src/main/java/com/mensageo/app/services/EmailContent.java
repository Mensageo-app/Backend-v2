package com.mensageo.app.services;

import java.time.LocalDateTime;

public class EmailContent {
    private String subject;
    private String body;
    private long hospitalNeedsId;
    private long makerId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getHospitalNeedsId() {
        return hospitalNeedsId;
    }

    public void setHospitalNeedsId(long hospitalNeedsId) {
        this.hospitalNeedsId = hospitalNeedsId;
    }

    public long getMakerId() {
        return makerId;
    }

    public void setMakerId(long makerId) {
        this.makerId = makerId;
    }
}
