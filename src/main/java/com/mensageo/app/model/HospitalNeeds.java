package com.mensageo.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HospitalNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(nullable = false)
//    private long hospitalId;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private long quantity;

    @ManyToOne(targetEntity = Hospital.class)
    private Hospital hospital;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTimestamp;

}
