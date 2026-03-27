package com.example.entity;

import com.example.enums.TouristAccountStatus;
import java.util.Date;

/**
 * Core domain entity representing a tourist account.
 */
public class TouristAccount {
    private String accountId;
    private String fullName;
    private String email;
    private String nationality;
    private Date registrationDate;
    private TouristAccountStatus status;

    // Constructors
    public TouristAccount() {}

    public TouristAccount(String accountId, String fullName, String email, String nationality, Date registrationDate, TouristAccountStatus status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.email = email;
        this.nationality = nationality;
        this.registrationDate = registrationDate;
        this.status = status;
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

    public TouristAccountStatus getStatus() {
        return status;
    }

    public void setStatus(TouristAccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TouristAccount{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                '}';
    }
}