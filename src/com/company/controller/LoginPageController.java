package com.company.controller;

import com.company.model.User;
import com.company.view.LoginPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class LoginPageController {
    // create a loginPage to Load loginPage here
    private LoginPage loginPage;

    static Stage registerStage = null;


    public LoginPageController(){
        //load loginPage
        loginPage = new LoginPage();
        //call onActions of buttons
        onActions();
    }


    private void onActions(){
        //Login button action = Open mainPge
        getLoginPage().getLoginBTN().setOnAction(e -> {
            openMainPage();
        });
        //register button action = Open registerPage
        getLoginPage().getRegisterBTN().setOnAction(e -> {
            openRegisterPage();
        });
        //Exit button action = exit program with exit code 0
        getLoginPage().getExitBTN().setOnAction(e -> Platform.exit());
    }

    //Open registerPage
    private void openRegisterPage(){
        // if register stage was not open ->
        if (registerStage == null){
            //open register page
            RegisterPageController root = new RegisterPageController();
            registerStage = new Stage();
            registerStage.setTitle("Gmail - Register User");
            registerStage.setWidth(436);
            registerStage.setHeight(530);
            // remove minimize and maximize and close of stage , user should click cancel button to exit this page
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root.getRegisterPage()));
            registerStage.show();
        }
    }

    //check All fields not empty
    private boolean checkAllFields(){
        if (loginPage.getUsernameField().getText().isEmpty() || loginPage.getPasswordField().getText().isEmpty()){
            loginPage.getErrorLBL().setText("ERROR : FILL THE BLANK");
            loginPage.getErrorLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    //open mainPage
    private void openMainPage(){
        loginPage.getErrorLBL().setText("");
        if (checkAllFields()){
            //get The desired user
            User user = getUserWithUserName(loginPage.getUsernameField().getText());
            //if user exist ->
            if (user != null){
                // create a hash of user password and check it with hash that saved on database
                if (checkPass(encryptThisString(loginPage.getPasswordField().getText()) , user)){
                    //load mainPage
                    loadMainPage(user);
                }else{
                    loginPage.getErrorLBL().setText("ERROR : WRONG PASSWORD");
                    loginPage.getErrorLBL().setTextFill(Color.RED);
                }

            }else{
                loginPage.getErrorLBL().setText("ERROR : NO USER FOUND");
                loginPage.getErrorLBL().setTextFill(Color.RED);
            }
        }
    }

    //get user with username if user was not delete
    // if user was deleted he/she can't login to the program but all their information remains in the database
    private User getUserWithUserName(String username){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users) {
            if (user.getIsDelete().equals("false")) {
                if (user.getUserName().equals(username))
                    return user;
            }
        }
        return null;
    }

    //check password of user
    private boolean checkPass(String password , User user){
        return password.equals(user.getPassword());
    }

    //convert password to SHA-512 hash
    private String encryptThisString(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    //load main page
    private void loadMainPage(User user){
        MainPageController controller = new MainPageController();
        controller.initUser(user);

        loginPage.getScene().getWindow().hide();

        Stage mainPageStage = new Stage();
        mainPageStage.setScene(new Scene(controller.getMainPage()));

        controller.getMainPage().getUsernameField().setText(user.getUserName());
        mainPageStage.setWidth(800);
        mainPageStage.setHeight(600);
        mainPageStage.setTitle("Gmail");
        mainPageStage.show();
    }

    //get login page
    public LoginPage getLoginPage() {
        return loginPage;
    }
}