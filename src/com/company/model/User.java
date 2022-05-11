package com.company.model;

import com.company.controller.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int userId = -1;
    private String userName;
    private String password;
    public ArrayList<String> tag;
    public ArrayList<String> blackList;
    private String name;
    private String lastName;
    private String birthday;
    private String placeOfBirth;
    private String address;
    private String phoneNumber;
    private MailBox mailBox;
    private String isDelete;


    public User(){
        tag = new ArrayList<>();
        blackList = new ArrayList<>();
        mailBox = new MailBox();
    }

    public User(int userId , String userName, String password, String name, String lastName, String birthday, String placeOfBirth
            , String address, String phoneNumber , String isDelete) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
        this.setName(name);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setPlaceOfBirth(placeOfBirth);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setIsDelete(isDelete);
    }

    public User(String userName, String password, String name, String lastName, String birthday, String placeOfBirth
            , String address, String phoneNumber ,String isDelete) {
        this.setUserName(userName);
        this.setPassword(password);
        this.setName(name);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setPlaceOfBirth(placeOfBirth);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setIsDelete(isDelete);
    }


    public static ArrayList<User> getAllUsers(){
        try {
            return DataBase.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.userId == -1){
            try {
                userId = DataBase.createUser(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            try {
                DataBase.updateUser(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(){
        try {
            DataBase.deleteUser(this);
            userId = -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MailBox getMailBox() {
        return mailBox;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
