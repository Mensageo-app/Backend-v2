package com.mensageo.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HospitalNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false, insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTimestamp;


    @ManyToOne
    private Product product;

    @ManyToOne
    private Hospital hospital;


    public long getId() {
        return id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
