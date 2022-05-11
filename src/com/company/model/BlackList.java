package com.company.model;

import com.company.controller.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class BlackList {

    private int blackId = -1;
    private String blackStr;
    private int senderId;

    public BlackList(int blackId, String blackStr , int senderId) {
        this.setBlackId(blackId);
        this.setBlackStr(blackStr);
        this.setSenderId(senderId);
    }

    public BlackList(String blackStr , int senderId){
        this.setBlackStr(blackStr);
        this.setSenderId(senderId);
    }

    public BlackList(int blackId , String blackStr){
        this.setBlackId(blackId);
        this.setBlackStr(blackStr);
    }


    public static ArrayList<BlackList> getAllBlackList(){
        try {
            return DataBase.getAllBlackList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.blackId == -1){
            try {
                blackId = DataBase.createBlackList(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(){
        try {
            DataBase.deleteBlackList(this);
            blackId = -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public int getBlackId() {
        return blackId;
    }

    public void setBlackId(int blackId) {
        this.blackId = blackId;
    }

    public String getBlackStr() {
        return blackStr;
    }

    public void setBlackStr(String blackStr) {
        this.blackStr = blackStr;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
