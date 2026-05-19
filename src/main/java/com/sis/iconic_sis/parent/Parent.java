package com.sis.iconic_sis.parent;

import com.sis.iconic_sis.phone_contact.PhoneContact;
import jakarta.persistence.*;

@Entity
public class Parent {
    public enum ParentRole {
        FATHER, MOTHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String nrc;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ParentRole role;

    private String job;

    @OneToOne
    private PhoneContact phoneContact;

    public Parent(String name, String nrc, ParentRole role, String job, PhoneContact phoneContact) {
        this.name = name;
        this.nrc = nrc;
        this.role = role;
        this.job = job;
        this.phoneContact = phoneContact;
    }

    public Parent() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public PhoneContact getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(PhoneContact phoneContact) {
        this.phoneContact = phoneContact;
    }

    public ParentRole getRole() {
        return role;
    }

    public void setRole(ParentRole role) {
        this.role = role;
    }
}

