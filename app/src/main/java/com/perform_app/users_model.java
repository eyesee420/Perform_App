package com.perform_app;

public class users_model {
    public String fullname, age, phonenumber, email, password, doc_id;

    public users_model() {
    }

    public users_model(String fullname, String age, String phonenumber, String email, String password, String doc_id) {
        this.fullname = fullname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.doc_id = doc_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}