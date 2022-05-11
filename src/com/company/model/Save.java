package com.company.model;

import com.company.controller.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class Save {
    private int saveId = -1;
    private int tagId;
    private int emailId;


    public Save(int saveId, int tagId, int emailId) {
        this.saveId = saveId;
        this.tagId = tagId;
        this.emailId = emailId;
    }

    public Save(int tagId , int emailId){
        this.tagId = tagId;
        this.emailId = emailId;
    }



    public static ArrayList<Save> getAllSaves(){
        try {
            return DataBase.getAllSaves();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.saveId == -1){
            try {
                saveId = DataBase.createSave(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(){
        try {
            DataBase.deleteSave(this);
            saveId = -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public int getSaveId() {
        return saveId;
    }

    public void setSaveId(int saveId) {
        this.saveId = saveId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }
}
