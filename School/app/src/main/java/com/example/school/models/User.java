package com.example.school.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class User  {
@PrimaryKey(autoGenerate = true)
    private int id ;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getString() {
        return role;
    }

    public void setString(String role) {
        this.role = role;
    }

    @ColumnInfo(name = "firstName")
    private String firstName;
    @ColumnInfo(name = "lastName")

    private String lastName;
    @ColumnInfo(name = "password")

    private String password;
    @ColumnInfo(name = "mail")

    private String mail;
    @ColumnInfo(name = "phoneNumber")

    private int phoneNumber;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Ignore
    public User(int id, String firstName, String lastName, String mail, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }
    @Ignore
    public User(int id, String firstName, String lastName, String password, String mail, int phoneNumber, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
    @Ignore
    public User(String firstName, String lastName, String password, String mail, int phoneNumber, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", role=" + role +
                '}';
    }

    public User() {
    }
}
