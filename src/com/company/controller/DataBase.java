package com.company.controller;

import com.company.model.*;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private static Connection connection;
    private static Statement statement;
    //make connection on my mysql account
    public static void makeConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/email" , "root"
                    , "M@z@rei2000");
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //close connection
    public static void closeConnection(){
        if (connection != null) {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //create user in database
    public static int createUser(User user) throws SQLException {
        makeConnection();

        statement.execute(String.format("insert into users (userName , password , name , lastName , birthday " +
                " , placeOfBirth , address , phoneNumber , isDelete) values ('%s' , '%s' , '%s' , '%s' , '%s' , '%s' ,'%s' ,'%s' , '%s')" ,
                user.getUserName() , user.getPassword() , user.getName() , user.getLastName() , user.getBirthday() ,
                user.getPlaceOfBirth() , user.getAddress() , user.getPhoneNumber() , user.getIsDelete()) , statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);

        closeConnection();

        return id;
    }
    //update user in database
    public static void updateUser(User user) throws SQLException {
        makeConnection();

        statement.execute(String.format("update users set password = '%s' , name = '%s' , lastName = '%s' " +
                        " , birthday = '%s' , placeOfBirth = '%s' , address = '%s' , phoneNumber = '%s' , isDelete = '%s' where userId = %d" ,
                user.getPassword() , user.getName() , user.getLastName() , user.getBirthday() , user.getPlaceOfBirth() ,
                user.getAddress() , user.getPhoneNumber() , user.getIsDelete() , user.getUserId()));

        closeConnection();
    }
    //delete user in database //V2.0 this method useless
    public static void deleteUser(User user) throws SQLException {
        makeConnection();

        statement.execute(String.format("delete from users where userId = %d" , user.getUserId()));

        closeConnection();
    }
    //get all users from database
    public static ArrayList<User> getAllUsers() throws SQLException {
        makeConnection();

        ResultSet re =statement.executeQuery("select * from users");

        ArrayList<User> users = new ArrayList<>();
        while (re.next()){
            users.add(new User(re.getInt("userId") , re.getString("userName") , re.getString("password") ,
                    re.getString("name") , re.getString("lastName") , re.getString("birthday") ,
                    re.getString("placeOfBirth") , re.getString("address") , re.getString("phoneNumber")
                    , re.getString("isDelete")));
        }

        closeConnection();

        return users;
    }

    //repeated methods for other tables in database -> Email , BlackList , Tag and Save

    public static int createEmail(Email email) throws SQLException {
        makeConnection();

        statement.execute(String.format("insert into emails (senderId , receiverId , subject , text , postageDate , " +
                        "isDraft , isTrashSend , isTrashReceive , isSpam) " + " values (%d , %d , '%s' , '%s' , '%s' , '%s' , '%s' , '%s' , '%s')"
                , email.getSenderId() , email.getReceiverId() , email.getSubject() , email.getText() , email.getPostageDate()
                , email.getIsDraft() , email.getIsTrashSend() , email.getIsTrashReceive() , email.getIsSpam()) , statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);

        closeConnection();

        return id;
    }

    public static void updateEmail(Email email) throws SQLException {
        makeConnection();

        statement.execute(String.format("update emails set senderId = %d , receiverId = %d , subject = '%s' , text = '%s' , " +
                "postageDate = '%s' , isDraft = '%s' , isTrashSend = '%s' , isTrashReceive = '%s' , isSpam = '%s' where emailId = %d" , email.getSenderId() ,
                email.getReceiverId() , email.getSubject() , email.getText() , email.getPostageDate() , email.getIsDraft()
                , email.getIsTrashSend() , email.getIsTrashReceive() , email.getIsSpam() , email.getEmailId()));

        closeConnection();
    }

    public static void deleteEmail(Email email) throws SQLException {
        makeConnection();

        statement.execute(String.format("delete from emails where emailId = %d" , email.getEmailId()));

        closeConnection();
    }

    public static ArrayList<Email> getAllEmails() throws SQLException {
        makeConnection();

        ResultSet re = statement.executeQuery("select * from emails");

        ArrayList<Email> emails = new ArrayList<>();
        while (re.next()){
            emails.add(new Email(re.getInt("emailId") , re.getInt("senderId") , re.getInt("receiverId") ,
                    re.getString("subject") , re.getString("text") , re.getString("postageDate")
                    , re.getString("isDraft") , re.getString("isTrashSend") , re.getString("isTrashReceive")
            , re.getString("isSpam")));
        }

        closeConnection();

        return emails;
    }

    public static int createBlackList(BlackList blackList) throws SQLException {
        makeConnection();

        statement.execute(String.format("insert into blackList (blackStr , senderId) values ('%s' , %d)" ,
                blackList.getBlackStr() , blackList.getSenderId()) , statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);

        closeConnection();

        return id;
    }

    public static ArrayList<BlackList> getAllBlackList() throws SQLException {
        makeConnection();

        ResultSet re = statement.executeQuery("select * from blacklist");

        ArrayList<BlackList> blackLists = new ArrayList<>();
        while (re.next()){
            blackLists.add(new BlackList(re.getInt("blackId") , re.getString("blackStr") , re.getInt("senderId")));
        }

        closeConnection();

        return blackLists;
    }

    public static void deleteBlackList(BlackList blackList) throws SQLException {
        makeConnection();

        statement.execute(String.format("delete from blacklist where blackId = %d" , blackList.getBlackId()));

        closeConnection();
    }

    public static int createTag(Tag tag) throws SQLException {
        makeConnection();

        statement.execute(String.format("insert into tags (tagName , senderId) values ('%s' , %d)" ,tag.getTagName() ,
                tag.getSenderId()) , statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);

        closeConnection();

        return id;
    }

    public static void updateTag(Tag tag) throws SQLException {
        makeConnection();

        statement.execute(String.format("update tags set tagName = '%s' , senderId = %d where tagId = %s" ,
                tag.getTagName() , tag.getSenderId() , tag.getTagId()));

        closeConnection();
    }

    public static void deleteTag(Tag tag) throws SQLException {
        makeConnection();

        statement.execute(String.format("delete from tags where tagId = %d" , tag.getTagId()));

        closeConnection();
    }

    public static ArrayList<Tag> getAllTags() throws SQLException {
        makeConnection();

        ResultSet re = statement.executeQuery("select * from tags");

        ArrayList<Tag> tags = new ArrayList<>();
        while (re.next()){
            tags.add(new Tag(re.getInt("tagId") , re.getString("tagName") , re.getInt("senderId")));
        }

        closeConnection();

        return tags;
    }

    public static int createSave(Save save) throws SQLException {
        makeConnection();

        statement.execute(String.format("insert into save (tagId , emailId) values (%d , %d)" , save.getTagId() ,
                save.getEmailId()) , statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);

        closeConnection();

        return id;
    }

    public static void deleteSave(Save save) throws SQLException {
        makeConnection();

        statement.execute(String.format("delete from save where saveId = %d" , save.getSaveId()));

        closeConnection();
    }

    public static ArrayList<Save> getAllSaves() throws SQLException {
        makeConnection();

        ResultSet re = statement.executeQuery("select * from save");

        ArrayList<Save> saves = new ArrayList<>();
        while (re.next()){
            saves.add(new Save(re.getInt("saveId") , re.getInt("tagId") , re.getInt("emailId")));
        }

        closeConnection();

        return saves;
    }

}