package com.company.model;

import com.company.controller.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class Email {

    private int emailId = -1;
    private int senderId;
    private int receiverId;
    private int loggedId = -1;
    private String subject;
    private String attachment;
    private String text;
    private String postageDate;
    private String usernameSend;
    private String usernameReceive;
    private String isDraft;
    private String isTrashSend;
    private String isTrashReceive;
    private String isSpam;



    public Email(int emailId , int senderId, int receiverId, String subject, String text, String postageDate ,
                 String isDraft , String isTrashSend ,String isTrashReceive , String isSpam) {
        this.emailId = emailId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.text = text;
        this.postageDate = postageDate;
        this.isDraft = isDraft;
        this.isTrashSend = isTrashSend;
        this.isTrashReceive = isTrashReceive;
        this.isSpam = isSpam;
    }

    public Email(int senderId, int receiverId, String subject, String text, String postageDate ,String isDraft ,
                 String isTrashSend , String isTrashReceive , String isSpam) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.text = text;
        this.postageDate = postageDate;
        this.isDraft = isDraft;
        this.isTrashSend = isTrashSend;
        this.isTrashReceive = isTrashReceive;
        this.isSpam = isSpam;
    }

    public Email(String usernameSend , String subject , String text , String postageDate){
        this.usernameSend = usernameSend;
        this.subject = subject;
        this.text = text;
        this.postageDate = postageDate;
    }

    public Email(int emailId , String usernameSend , String subject , String text , String postageDate){
        this.emailId = emailId;
        this.usernameSend = usernameSend;
        this.subject = subject;
        this.text = text;
        this.postageDate = postageDate;
    }


    public Email(int emailId , String usernameSend , String usernameReceive , String subject , String text , String postageDate){
        this.emailId = emailId;
        this.usernameSend = usernameSend;
        this.usernameReceive = usernameReceive;
        this.subject = subject;
        this.text = text;
        this.postageDate = postageDate;
    }



    public static ArrayList<Email> getAllEmails(){
        try {
            return DataBase.getAllEmails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.emailId == -1){
            try {
                emailId = DataBase.createEmail(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            try {
                DataBase.updateEmail(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(){
        try {
            DataBase.deleteEmail(this);
            emailId = -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public static ArrayList<Email> getAllTrashes(){
//        try {
//            DataBase.getAllTrashes();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

//    public void trash(){
//        if (this.loggedId == -1){
//            try {
//                loggedId = DataBase.createTrash(this);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }


    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() { return attachment; }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostageDate() {
        return postageDate;
    }

    public void setPostageDate(String postageDate) {
        this.postageDate = postageDate;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getUsernameSend() {
        return usernameSend;
    }

    public void setUsernameSend(String usernameSend) {
        this.usernameSend = usernameSend;
    }

    public String getUsernameReceive() {
        return usernameReceive;
    }

    public void setUsernameReceive(String usernameReceive) {
        this.usernameReceive = usernameReceive;
    }

    public int getLoggedId() {
        return loggedId;
    }

    public void setLoggedId(int loggedId) {
        this.loggedId = loggedId;
    }

    public String getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(String isDraft) {
        this.isDraft = isDraft;
    }

    public String getIsTrashSend() {
        return isTrashSend;
    }

    public void setIsTrashSend(String isTrashSend) {
        this.isTrashSend = isTrashSend;
    }

    public String getIsTrashReceive() {
        return isTrashReceive;
    }

    public void setIsTrashReceive(String isTrashReceive) {
        this.isTrashReceive = isTrashReceive;
    }

    public String getIsSpam() {
        return isSpam;
    }

    public void setIsSpam(String isSpam) {
        this.isSpam = isSpam;
    }
}
