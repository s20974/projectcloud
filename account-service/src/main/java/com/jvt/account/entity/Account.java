package com.jvt.account.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private String name;
    private String email;
    private String phone;
    private OffsetDateTime creationDate;

    @ElementCollection
    private List<Long> bills;

    public Account(String name, String email, String phone, OffsetDateTime creationDate, List<Long> bills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.creationDate = creationDate;
        this.bills = bills;
    }

    public Account(Long id, String name, String email, String phone, List<Long> bills) {
        this.accountId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bills = bills;
    }
}
