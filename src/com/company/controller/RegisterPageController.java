package com.company.controller;

import com.company.model.User;
import com.company.view.RegisterPage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterPageController {
    // create a registerPage to Load registerPage here
    private RegisterPage registerPage ;

    //Load registerPage & call onActions of buttons
    public RegisterPageController(){
        registerPage = new RegisterPage();
        onActions();
    }

    //Register Button & Cancel Button Actions
    private void onActions(){
        getRegisterPage().getRegisterBTN().setOnAction(e -> {
            createUser();
        });

        getRegisterPage().getCancelBTN().setOnAction(e -> {
            closeStage();
        });
    }

    //Close RegisterPage and Load LoginPage
    private void closeStage(){
        ( (Stage) getRegisterPage().getCancelBTN().getScene().getWindow()).close();
        LoginPageController.registerStage = null;
    }

    //First check fields not Empty by chekAllFields method , Second check username be unique , check user phone number
    //That contain only digits , Start with 09 and length of number == 11
    private void createUser(){
        if (checkAllFields() && checkUserUserName() && checkUserPhoneNumberFormat() && checkUserPhoneNumber()){
            //Convert DatePicker to String to save on database
            String birthday = registerPage.getBirthday().getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //convert password to SHA - 512 HASH
            String password = encryptThisString(registerPage.getPasswordField().getText());
            //create user
            User user = new User(registerPage.getUsername().getText() , password ,
                    registerPage.getName().getText() , registerPage.getLastName().getText() , birthday ,
                    registerPage.getPlaceOfBirth().getText() , registerPage.getAddress().getText() ,
                    registerPage.getPhoneNumber().getText() , "false");
            //Save user data on database
            user.save();
            //After user created , Clean the registerPage fields
            cleanPage();
        }
    }

    //Check All fields not empty
    private boolean checkAllFields(){
        if (registerPage.getName().getText().isEmpty() || registerPage.getLastName().getText().isEmpty() ||
                registerPage.getUsername().getText().isEmpty() || registerPage.getPasswordField().getText().isEmpty() ||
                registerPage.getPlaceOfBirth().getText().isEmpty() || registerPage.getAddress().getText().isEmpty() ||
                registerPage.getPhoneNumber().getText().isEmpty() || registerPage.getBirthday().getEditor().getText().isEmpty()){
            registerPage.getErrorLabel().setText("ERROR : FILL THE BLANK");
            registerPage.getErrorLabel().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    //Check username not exist
    private boolean checkUserUserName(){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserName().equals(registerPage.getUsername().getText())){
                registerPage.getErrorLabel().setText("ERROR : USERNAME ALREADY EXIST");
                registerPage.getErrorLabel().setTextFill(Color.RED);
                return false;
            }
        }
        return true;
    }

    //check username phone number format
    private boolean checkUserPhoneNumberFormat(){
        if (!registerPage.getPhoneNumber().getText().matches("[0-9]+") ||
                !registerPage.getPhoneNumber().getText().startsWith("09") ||
                registerPage.getPhoneNumber().getText().length() != 11){
            registerPage.getErrorLabel().setText("ERROR : WRONG PHONE NUMBER FORMAT");
            registerPage.getErrorLabel().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    //check user phone number not exist on Database
    private boolean checkUserPhoneNumber(){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getPhoneNumber().equals(registerPage.getPhoneNumber().getText())){
                registerPage.getErrorLabel().setText("ERROR : PHONE NUMBER ALREADY EXIST");
                registerPage.getErrorLabel().setTextFill(Color.RED);
                return false;
            }
        }
        return true;
    }

    // convert password to SHA-512 HASH
    private String encryptThisString(String input) {
        // getInstance() method is called with algorithm SHA-512
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        byte[] messageDigest = md.digest(input.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);

        // Add preceding 0s to make it 32 bit
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        // return the HashText
        return hashtext;
    }

    private void cleanPage(){
        registerPage.getErrorLabel().setText("");
        registerPage.getName().setText("");
        registerPage.getLastName().setText("");
        registerPage.getUsername().setText("");
        registerPage.getPasswordField().setText("");
        registerPage.getPlaceOfBirth().setText("");
        registerPage.getAddress().setText("");
        registerPage.getPhoneNumber().setText("");
        registerPage.getBirthday().getEditor().setText("");
    }


    //get register page
    public RegisterPage getRegisterPage() {
        return registerPage;
    }
}