package com.sigo.gestao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "status_report")
public class StatusReport {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
}
