package com.company.model;

import java.util.ArrayList;

public class MailBox {

    public ArrayList<Email> inbox;
    public ArrayList<Email> spams;
    public ArrayList<Email> drafts;
    public ArrayList<Email> sent;
    public ArrayList<Email> trash;
    public ArrayList<BlackList> blackList;
    public ArrayList<Tag> tag;



    public MailBox(){
        inbox = new ArrayList<>();
        spams = new ArrayList<>();
        drafts = new ArrayList<>();
        sent = new ArrayList<>();
        trash = new ArrayList<>();
        blackList = new ArrayList<>();
        tag = new ArrayList<>();
    }
}
