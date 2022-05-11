package com.company.model;

import com.company.controller.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tag {

    private int tagId = -1;
    private String tagName;
    private int senderId;


    public Tag(int tagId, String tagName, int senderId) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.senderId = senderId;
    }

    public Tag(String tagName , int senderId){
        this.tagName = tagName;
        this.senderId = senderId;
    }

    public Tag(int tagId , String tagName){
        this.tagId = tagId;
        this.tagName = tagName;
    }


    public static ArrayList<Tag> getAllTags(){
        try {
            return DataBase.getAllTags();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.tagId == -1){
            try {
                tagId = DataBase.createTag(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            try {
                DataBase.updateTag(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(){
        try {
            DataBase.deleteTag(this);
            tagId = -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
