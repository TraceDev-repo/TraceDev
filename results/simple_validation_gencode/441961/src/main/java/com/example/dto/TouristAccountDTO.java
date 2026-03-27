package com.example.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO that carries tourist account data for presentation.
 */
public class TouristAccountDTO implements Serializable {
    private String accountId;
    private String fullName;
    private String email;
    private String nationality;
    private Date registrationDate;

    // Constructors
    public TouristAccountDTO() {}

    public TouristAccountDTO(String accountId, String fullName, String email, String nationality, Date registrationDate) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.email = email;
        this.nationality = nationality;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "TouristAccountDTO{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}