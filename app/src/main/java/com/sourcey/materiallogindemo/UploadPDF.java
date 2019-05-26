package com.sourcey.materiallogindemo;

/**
 * Created by HP on 07-May-19.
 */

public class UploadPDF {

    public String name;
    public String phone;
    public String email;
    public String address;
    public String enroll;
    public String branch;
    public String course;
    public String mno;
    public String fee;
    public String semester;

    public String url;


    public UploadPDF(){

    }

    public UploadPDF(String name, String phone, String email, String address, String enroll, String branch, String course, String mno, String fee, String backlog, String url) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.enroll = enroll;
        this.branch = branch;
        this.course = course;
        this.mno = mno;
        this.fee = fee;
        this.semester = backlog;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getBacklog() {
        return semester;
    }

    public void setBacklog(String backlog) {
        this.semester = backlog;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
