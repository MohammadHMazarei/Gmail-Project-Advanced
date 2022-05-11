package com.company.controller;

import com.company.model.*;
import com.company.view.MainPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainPageController {
    //Variables
    private MainPage mainPage;
    private User loggedUser;
    private User receiverUser;
    private int receiverId;
    static Stage userInfoPage = null;
    static Stage sendEmailPage = null;


    public MainPageController(){
        //create mainPage and load
        mainPage = new MainPage();
        //all mainPage button actions wrote in seOnAction function
        setOnActions();
    }

    //Initialization loggedUser for loading mainPage and hold loggedUser
    public void initUser(User loggedUser){
        this.loggedUser = loggedUser;
        //after logged User != null , show all emails of loggedUser and load all emails from emails_table in database
        showEmails();
    }



    private void setOnActions(){
        //confirm button action that located in Account Tab
        mainPage.getConfirmBTN().setOnAction(e -> {
            updateUserDuoEdit();
        });
        //Delete button action that located in Account Tab
        mainPage.getDeleteBTN().setOnAction(e -> {
            deleteAccount();
        });
        //Logout button action that located in Account Tab
        mainPage.getLogoutBTN().setOnAction(e ->{
            logOut();
        });
        //Send button action that located in Compose Tab (Undo button action and Tread in sendEmail function)
        mainPage.getSendBTN().setOnAction(e ->{
            sendEmail();
        });
        //Draft button action that located in Compose Tab
        mainPage.getDraftBTN().setOnAction(e -> {
            draftEmail();
        });
        //delete button action that located in Inbox Tab
        mainPage.getDeleteInboxEmailBTN().setOnAction(e -> {
            deleteInboxEmails();
        });
        //delete button action that located in Sent Tab
        mainPage.getDeleteSentEmailBTM().setOnAction(e -> {
            deleteSentEmails();
        });
        //Send Email(button text = Edit email) button action that located in Draft Tab
        mainPage.getEditDraftEmailBTN().setOnAction(e -> {
            sendMailOnDraft();
        });
        //delete button action that located in Draft Tab
        mainPage.getDeleteDraftEmailBTN().setOnAction(e -> {
            deleteDraftEmails();
        });
        //create black word button that located in BlackList Tab
        mainPage.getCreateBlack().setOnAction(e -> {
            openBlackListPage();
        });
        //delete button that located in Black List Tab
        mainPage.getDeleteBlack().setOnAction(e -> {
            deleteBlackList();
        });
        //delete button that located in Spam Tab
        mainPage.getDeleteSpamBTN().setOnAction(e -> {
            deleteSpamEmails();
        });
        //create tag button that located in Tag Tab
        mainPage.getCreateTagBTN().setOnAction(e -> {
            openTagPage();
        });
        //edit tag button that located in Tag Tab
        mainPage.getEditTagBTN().setOnAction(e -> {
            editTag();
        });
        //delete tag button that located in Tag Tab
        mainPage.getDeleteTagBTN().setOnAction(e -> {
            deleteTag();
        });
        //add tag button that located in Inbox Tab
        mainPage.getAddTag().setOnAction(e ->{
            openAddTagPage();
        });
        //show emails button that located in Tag Tab
        mainPage.getShowEmailBTN().setOnAction(e ->{
            openShowEmailPage();
        });
        //delete tag button that located in Inbox Tab
        mainPage.getInboxDeleteTagBTN().setOnAction(e ->{
            openShowTagPage();
        });
        //show email button that located in Inbox Tab
        mainPage.getShowBTN().setOnAction(e ->{
            openShowEmailInboxPage();
        });
        //show email button that located in Sent Tab
        mainPage.getShowEmailSentBTN().setOnAction(e ->{
            openShowEmailSentPage();
        });
        //show email button that located in Trash Tab
        mainPage.getShowEmailTrashBTN().setOnAction(e ->{
            openShowEmailTrashPage();
        });
        //show email button that located in Draft Tab
        mainPage.getShowEmailDraftBTN().setOnAction(e ->{
            openShowEmailDraftPage();
        });
        //show email button that located in Spam Tab
        mainPage.getShowEmailSpamBTN().setOnAction(e ->{
            openShowEmailSpamPage();
        });
    }

    //Update user
    private void updateUserDuoEdit(){
        if (checkAllFields() && checkUserPhoneNumberFormat() && checkUserPhoneNumber()){
            //Convert datePicker to string
            String birthday = mainPage.getBirthdayField().getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //Hash the password (SHA-512 algorithm)
            String password = encryptThisString(mainPage.getPasswordField().getText());
            String passwordKH = mainPage.getPasswordField().getText();
            loggedUser.setPassword(password);
            loggedUser.setName(mainPage.getNameField().getText());
            loggedUser.setLastName(mainPage.getLastNameField().getText());
            loggedUser.setBirthday(birthday);
            loggedUser.setPlaceOfBirth(mainPage.getPlaceOfBirthField().getText());
            loggedUser.setAddress(mainPage.getAddressField().getText());
            loggedUser.setPhoneNumber(mainPage.getPhoneNumberField().getText());
            //userId != -1 , then Update function from database work
            loggedUser.save();
            //clear page
            clearPage();
            //show Updated information in new page
            openUserInfoPage(passwordKH);
        }
    }

    //check fields not empty
    private boolean checkAllFields(){
        if (mainPage.getNameField().getText().isEmpty() || mainPage.getLastNameField().getText().isEmpty() ||
                mainPage.getPlaceOfBirthField().getText().isEmpty() || mainPage.getAddressField().getText().isEmpty() ||
                mainPage.getPhoneNumberField().getText().isEmpty() || mainPage.getBirthdayField().getEditor().getText().isEmpty()){
            mainPage.getErrorLabel().setText("ERROR : FILL THE BLANK");
            mainPage.getErrorLabel().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    //check user phone number format (should contain all digits , start with 09 & length = 11)
    private boolean checkUserPhoneNumberFormat(){
        if (!mainPage.getPhoneNumberField().getText().matches("[0-9]+") ||
                !mainPage.getPhoneNumberField().getText().startsWith("09") ||
                mainPage.getPhoneNumberField().getText().length() != 11){
            mainPage.getErrorLabel().setText("ERROR : WRONG PHONE NUMBER FORMAT");
            mainPage.getErrorLabel().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    //check user phone number that not exist except loggedUser
    private boolean checkUserPhoneNumber(){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserId() == loggedUser.getUserId())
                continue;
            else{
                if (user.getPhoneNumber().equals(mainPage.getPhoneNumberField().getText())){
                    mainPage.getErrorLabel().setText("ERROR : PHONE NUMBER ALREADY EXIST");
                    mainPage.getErrorLabel().setTextFill(Color.RED);
                    return false;
                }
            }
        }
        return true;
    }

    //Hash password -> SHA-512 algorithm
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

    //delete account , I set isDelete to true just for having our user information on database , cause we need user information
    private void deleteAccount(){
        //set isDelete = True
        loggedUser.setIsDelete("true");
        //userId != -1 then our user information update in database
        loggedUser.save();
        //close mainPage and load loginPage when user delete
        LoginPageController controller = new LoginPageController();

        mainPage.getScene().getWindow().hide();

        Stage loginPageStage = new Stage();
        loginPageStage.setScene(new Scene(controller.getLoginPage()));
        loginPageStage.setHeight(500);
        loginPageStage.setWidth(600);
        loginPageStage.setTitle("Gmail - Login");
        loginPageStage.show();
    }

    //clear page
    private void clearPage(){
        mainPage.getErrorLabel().setText("");
        mainPage.getPasswordField().setText("");
        mainPage.getNameField().setText("");
        mainPage.getLastNameField().setText("");
        mainPage.getBirthdayField().getEditor().setText("");
        mainPage.getPlaceOfBirthField().setText("");
        mainPage.getAddressField().setText("");
        mainPage.getPhoneNumberField().setText("");
    }

    //Open user information Page after click on confirm button in Account Tab
    private void openUserInfoPage(String pass){
        if (userInfoPage == null){
            UserInformationPageController root = new UserInformationPageController();
            root.initUser(loggedUser);

            //set User information to show
            root.getUserInformationPage().getNameField().setText(loggedUser.getName());
            root.getUserInformationPage().getLastNameField().setText(loggedUser.getLastName());
            root.getUserInformationPage().getUsernameField().setText(loggedUser.getUserName());
            root.getUserInformationPage().getPasswordField().setText(pass);
            root.getUserInformationPage().getPlaceOfBirthField().setText(loggedUser.getPlaceOfBirth());
            root.getUserInformationPage().getAddressField().setText(loggedUser.getAddress());
            root.getUserInformationPage().getPhoneNumberField().setText(loggedUser.getPhoneNumber());
            root.getUserInformationPage().getBirthdayField().setText(loggedUser.getBirthday());

            userInfoPage = new Stage();
            userInfoPage.setTitle("Gmail - User Information");
            userInfoPage.setWidth(500);
            userInfoPage.setHeight(537);
            userInfoPage.initStyle(StageStyle.UNDECORATED);
            userInfoPage.setScene(new Scene(root.getUserInformationPage()));
            userInfoPage.show();
        }
    }

    //log out function that close mainPage and load loginPage
    private void logOut(){
        LoginPageController controller = new LoginPageController();

        mainPage.getScene().getWindow().hide();

        Stage loginPageStage = new Stage();
        loginPageStage.setScene(new Scene(controller.getLoginPage()));
        loginPageStage.setHeight(500);
        loginPageStage.setWidth(600);
        loginPageStage.setTitle("Gmail - Login");
        loginPageStage.show();
    }

    //send email
    private void sendEmail(){
        //check all fields not empty
        if (checkAllEmailFields()){
            //check To(Receiver user) field exist
            if (checkReceiverExist(mainPage.getToField().getText())) {
                //set receiver user and receiver Id
                setReceiverUser(getReceiverWithUsername(mainPage.getToField().getText()));
                setReceiverId(getReceiverUser().getUserId());
                Email email;
                //check length of email text (should be 200 character)
                if (checkLengthOfText()) {
                    //get current date
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);
                    //Create new email
                    email = new Email(loggedUser.getUserId() , getReceiverId() , mainPage.getSubjectField().getText() ,
                            mainPage.getTextArea().getText() , currentDate , "false" , "false" , "false" , "false");
                    //save this new email on database
                    email.save();
                    //save fields information on variables
                    int emailId = email.getEmailId();
                    String username = getReceiverUser().getUserName();
                    String subject = mainPage.getSubjectField().getText();
                    String text = mainPage.getTextArea().getText();
                    // clean compose Tab
                    cleanPageCompose();
                    //start Thread for undo button for about 30 second
                    Thread thread = new Thread(() ->{
                        AtomicBoolean a = new AtomicBoolean(true);
                        //set all fields and button disable of uneditable except undo(set undo disable to false)
                        mainPage.getToField().setEditable(false);
                        mainPage.getSubjectField().setEditable(false);
                        mainPage.getTextArea().setEditable(false);
                        mainPage.getSendBTN().setDisable(true);
                        mainPage.getDraftBTN().setDisable(true);
                        mainPage.getUndoBTN().setDisable(false);
                        //undo button action
                        mainPage.getUndoBTN().setOnAction(event -> {
                            //set a to false
                            a.set(false);
                            //create new Thread to delete email from database and set all fields and button enable and set undo disable
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    email.delete();
                                    mainPage.getToField().setEditable(true);
                                    mainPage.getSubjectField().setEditable(true);
                                    mainPage.getTextArea().setEditable(true);
                                    mainPage.getSendBTN().setDisable(false);
                                    mainPage.getDraftBTN().setDisable(false);
                                    mainPage.getUndoBTN().setDisable(true);
                                }
                            });
                        });
                        //start 30 second sleep
                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        //if undo button wasn't click then start this Thread
                        if (a.get()) {
                            //create new Thread to delete email from database and set all fields and button enable and set undo disable
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //set email in sent table
                                    mainPage.getSent().getItems().add(new Email(emailId, username, subject, text, currentDate));
                                    mainPage.getToField().setEditable(true);
                                    mainPage.getSubjectField().setEditable(true);
                                    mainPage.getTextArea().setEditable(true);
                                    mainPage.getSendBTN().setDisable(false);
                                    mainPage.getDraftBTN().setDisable(false);
                                    mainPage.getUndoBTN().setDisable(true);
                                }
                            });
                        }
                    });
                    //start thread
                    thread.start();
                }
            }
        }
    }


    //get email from database by emailId
    private Email getEmailWithEmailId(int emailId){
        ArrayList<Email> emails = Email.getAllEmails();

        for(Email email : emails){
            if (email.getEmailId() == emailId)
                return email;
        }
        return null;
    }

    //get user from database by userId
    private User getUserWithUserId(int id){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserId() == id)
                return user;
        }
        return null;
    }

    //draft email
    private void draftEmail(){
        //check all fields and receiver exist
        if (checkToField() && checkReceiverExist(mainPage.getToField().getText())) {
            setReceiverUser(getReceiverWithUsername(mainPage.getToField().getText()));
            setReceiverId(getReceiverUser().getUserId());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            //This email is draft & we need to set isDraft variable to 'true'
            Email email = new Email(loggedUser.getUserId(), getReceiverId() , mainPage.getSubjectField().getText(), mainPage.getTextArea().getText(),
                    currentDate, "true" , "false" , "false" , "false");
            //emailId != -1 , then email update in database
            email.save();
            //set email in draft table
            mainPage.getDraft().getItems().add(new Email(email.getEmailId() , getReceiverUser().getUserName() ,
                    mainPage.getSubjectField().getText() , mainPage.getTextArea().getText() , currentDate));
            cleanPageCompose();
        }
    }
    //check To field (for draft a email , user should fill ToField)
    private boolean checkToField(){
        if (mainPage.getToField().getText().isEmpty()){
            mainPage.getComposeLBL().setText("ERROR : FILL THE (TO) BLANK");
            mainPage.getComposeLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }
    //check all email fields
    private boolean checkAllEmailFields(){
        if (mainPage.getToField().getText().isEmpty() && mainPage.getSubjectField().getText().isEmpty() &&
        mainPage.getTextArea().getText().isEmpty()){
            mainPage.getComposeLBL().setText("ERROR : FILL THE BLANK");
            mainPage.getComposeLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }
    //check receiver Exist
    private boolean checkReceiverExist(String username){
        ArrayList<User> users = User.getAllUsers();

        for (User user1 : users) {
            if (user1.getUserId() == loggedUser.getUserId() || user1.getIsDelete().equals("true"))
                continue;
            else {
                if (user1.getUserName().equals(username)) {
                    return true;
                }
            }
        }
        mainPage.getComposeLBL().setText("ERROR : NO USER FOUND");
        mainPage.getComposeLBL().setTextFill(Color.RED);
        return false;
    }
    //check length of email text
    private boolean checkLengthOfText(){
        if (mainPage.getTextArea().getText().length() > 200){
            mainPage.getComposeLBL().setText("ERROR : TEXT TOO LONG 200/200");
            mainPage.getComposeLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }
    //get user with username
    private User getReceiverWithUsername(String username){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }

    //clean page
    private void cleanPageCompose(){
        mainPage.getComposeLBL().setText("");
        mainPage.getToField().setText("");
        mainPage.getSubjectField().setText("");
        mainPage.getTextArea().setText("");
    }

    //get all emails , users , blacklist and tags from database and sort in their tables
    private void showEmails(){
        ArrayList<Email> emails = Email.getAllEmails();
        ArrayList<User> users = User.getAllUsers();
        ArrayList<BlackList> blackLists = BlackList.getAllBlackList();
        ArrayList<Tag> tags = Tag.getAllTags();

        Email email;
        MailBox mailBox = new MailBox();
        //search in emails to found loggedUser id to sort his/here emails
        for (Email value : emails) {
            if (value.getSenderId() == loggedUser.getUserId()) {
                for (User user : users) {
                    //check emails that logged User is sender
                    if (value.getReceiverId() == user.getUserId()) {
                        String username = user.getUserName();
                        // if isDraft = 'true' this emails should go to draft ArrayList
                        if (value.getIsDraft().equals("true")) {
                            email = new Email(value.getEmailId(), username, value.getSubject(),
                                    value.getText()
                                    , value.getPostageDate());
                            mailBox.drafts.add(email);
                        }
                        //if isTrashSend Or isTrashReceive = 'true' this emails should go to trash ArrayList
                        if (value.getIsTrashReceive().equals("true") || value.getIsTrashSend().equals("true")) {
                            mailBox.trash.add(new Email(value.getEmailId(), loggedUser.getUserName(), username,
                                    value.getSubject(), value.getText(), value.getPostageDate()));
                        }
                        if (value.getIsDraft().equals("false")) {
                            //if isTrashReceive was true , this email added to trash table and now should ignore it
                            if (value.getIsTrashReceive().equals("true")) {
                                continue;
                            }
                            // if isTrashSend and isTrashReceive = 'false' this emails should go to sent ArrayList
                            if (value.getIsTrashReceive().equals("false") && value.getIsTrashSend().equals("false")) {
                                email = new Email(value.getEmailId(), username, value.getSubject(),
                                        value.getText(), value.getPostageDate());
                                mailBox.sent.add(email);
                            }
                        }
                    }
                }
            }
        }
        // get blackList data from database and add to blackList Arraylist
        for (BlackList blackList : blackLists) {
            if (loggedUser.getUserId() == blackList.getSenderId()) {
                mailBox.blackList.add(blackList);
            }
        }
        //get tags data from database and add to tag ArrayLost
        for (Tag tag : tags) {
            if (loggedUser.getUserId() == tag.getSenderId()) {
                mailBox.tag.add(tag);
            }
        }
        for (Email value : emails) {
            //check emails that logged User is receiver
            if (value.getReceiverId() == loggedUser.getUserId()) {
                for (User user : users) {
                    if (value.getSenderId() == user.getUserId()) {
                        String username = user.getUserName();
                        if (value.getIsTrashSend().equals("true")) {
                            mailBox.trash.add(new Email(value.getEmailId(), username, loggedUser.getUserName(),
                                    value.getSubject(), value.getText(), value.getPostageDate()));
                        }
                        if (value.getIsDraft().equals("false")) {
                            if (value.getIsTrashSend().equals("true")) {
                                continue;
                            } else {
                                //check black words , if exist in text of email , this email is spam & should go to spam table
                                for (BlackList blackList : blackLists) {
                                    if (blackList.getSenderId() == loggedUser.getUserId()) {
                                        if (value.getSubject().contains(blackList.getBlackStr()) ||
                                                value.getText().contains(blackList.getBlackStr())) {
                                            email = new Email(value.getEmailId(), username, value.getSubject(),
                                                    value.getText(), value.getPostageDate());
                                            mailBox.spams.add(email);
                                            value.setIsSpam("true");
                                            value.save();
                                            break;
                                        }
                                    }
                                }
                                //if isSpam was false this email should go to inbox table
                                if (value.getIsSpam().equals("false")) {
                                    email = new Email(value.getEmailId(), username, value.getSubject(),
                                            value.getText(), value.getPostageDate());
                                    mailBox.inbox.add(email);
                                }
                            }
                        }
                    }
                }
            }
        }
        //add emails to tables
        //1.Sent emails 2.Inbox emails 3.Draft emails 4.Trash emails 5.Black words 6.Spam emails 7. Tag words
        for (int i = 0;i < mailBox.sent.size();i++){
            mainPage.getSent().getItems().add(mailBox.sent.get(i));
        }
        for (int i = 0; i < mailBox.inbox.size();i++){
            mainPage.getInbox().getItems().add(mailBox.inbox.get(i));
        }
        for (int i = 0; i < mailBox.drafts.size();i++){
            mainPage.getDraft().getItems().add(mailBox.drafts.get(i));
        }
        for (int i = 0; i < mailBox.trash.size();i++){
            mainPage.getTrash().getItems().add(mailBox.trash.get(i));
        }
        for (int i = 0; i < mailBox.blackList.size(); i++) {
            mainPage.getBlackList().getItems().add(mailBox.blackList.get(i));
        }
        for (int i = 0; i < mailBox.spams.size();i++){
            mainPage.getSpam().getItems().add(mailBox.spams.get(i));
        }
        for (int i = 0; i < mailBox.tag.size(); i++) {
            mainPage.getTag().getItems().add(mailBox.tag.get(i));
        }
    }
    //delete email from inbox by selecting email from inbox table
    private void deleteInboxEmails(){
        Email selectedEmail = mainPage.getInbox().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            //find email in database with emailId
            Email email = getEmailWithEmailId(selectedEmail.getEmailId());
            mainPage.getInbox().getItems().remove(selectedEmail);
            //just setIsTrashSend to true and we almost update our email in database and we don't delete it ,we just move email to trash table
            email.setIsTrashSend("true");
            //update email
            email.save();

            User userReceiver = getUserWithUserId(email.getSenderId());
            mainPage.getTrash().getItems().add(new Email(email.getEmailId() , userReceiver.getUserName() , loggedUser.getUserName() ,
                    email.getSubject() , email.getText() , email.getPostageDate()));
        }
    }
    //delete email from sent by selecting email from sent table
    private void deleteSentEmails(){
        Email selectedEmail = mainPage.getSent().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            //find email in database with emailId
            Email email = getEmailWithEmailId(selectedEmail.getEmailId());
            mainPage.getSent().getItems().remove(selectedEmail);
            //just setIsTrashReceive to true and we almost update our email in database and we don't delete it ,we just move email to trash table
            email.setIsTrashReceive("true");
            //update email
            email.save();

            User userSender = getUserWithUserId(email.getReceiverId());
            mainPage.getTrash().getItems().add(new Email(email.getEmailId() , loggedUser.getUserName() , userSender.getUserName(),
                    email.getSubject() , email.getText() , email.getPostageDate()));
        }
    }
    //delete email from draft table
    private void deleteDraftEmails(){
        Email selectedEmail = mainPage.getDraft().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            Email email = getEmailWithEmailId(selectedEmail.getEmailId());
            mainPage.getDraft().getItems().remove(selectedEmail);

            email.setIsDraft("false");
            email.setIsTrashSend("true");
            email.save();

            User userReceiver = getUserWithUserId(email.getSenderId());
            mainPage.getTrash().getItems().add(new Email(email.getEmailId() , userReceiver.getUserName() , loggedUser.getUserName() ,
                    email.getSubject() , email.getText() , email.getPostageDate()));
        }
    }
    //openSendEmailPage for editing and send a draft email
    private void openSendEmailPage(){
        Email selectedEmail = mainPage.getDraft().getSelectionModel().getSelectedItem();
        if (selectedEmail != null) {
            if (sendEmailPage == null) {
                SendEmailPageController root = new SendEmailPageController(selectedEmail , mainPage , loggedUser);

                sendEmailPage = new Stage();
                sendEmailPage.setTitle("Gmail - Send Email");
                sendEmailPage.setWidth(300);
                sendEmailPage.setHeight(500);
                //user should click on exit button to exit send email page (a compose page in draft)
                sendEmailPage.initStyle(StageStyle.UNDECORATED);
                sendEmailPage.setScene(new Scene(root.getSendEmailPage()));
                sendEmailPage.show();
            }
        }
    }

    private void sendMailOnDraft(){
        openSendEmailPage();
    }
    //open blacklist page to add black words
    private void openBlackListPage(){
        BlackListPageController controller = new BlackListPageController(loggedUser , mainPage);

        Stage blackListPageStage = new Stage();
        blackListPageStage.setTitle("Gmail - Add black word");
        blackListPageStage.setWidth(300);
        blackListPageStage.setHeight(500);
        blackListPageStage.resizableProperty().setValue(Boolean.FALSE);
        blackListPageStage.setScene(new Scene(controller.getBlackListPage()));
        blackListPageStage.show();
    }
    //delete black word from blacklist table
    //we can safe delete black word in database , this word useless
    private void deleteBlackList(){
        BlackList selectedBlackList = mainPage.getBlackList().getSelectionModel().getSelectedItem();
        if (selectedBlackList != null){
            BlackList blackList = getBlackListWithId(selectedBlackList.getBlackId());

            mainPage.getBlackList().getItems().remove(selectedBlackList);

            blackList.delete();
        }
    }
    //get black word with id
    private BlackList getBlackListWithId(int id){
        ArrayList<BlackList> blackLists = BlackList.getAllBlackList();

        for (int i = 0; i < blackLists.size(); i++) {
            if (blackLists.get(i).getBlackId() == id){
                return blackLists.get(i);
            }
        }
        return null;
    }
    //delete email in spam table
    private void deleteSpamEmails(){
        Email selectedEmail = mainPage.getSpam().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            Email email = getEmailWithEmailId(selectedEmail.getEmailId());
            mainPage.getSpam().getItems().remove(selectedEmail);

            email.setIsSpam("false");
            email.setIsTrashSend("true");
            email.save();

            User userReceiver = getUserWithUserId(email.getSenderId());
            mainPage.getTrash().getItems().add(new Email(email.getEmailId() , userReceiver.getUserName() , loggedUser.getUserName() ,
                    email.getSubject() , email.getText() , email.getPostageDate()));
        }
    }
    //open tag page to add tag words
    private void openTagPage(){
        TagPageController controller = new TagPageController(loggedUser , mainPage);

        Stage tagPageStage = new Stage();
        tagPageStage.setTitle("Gmail - Add Tag word");
        tagPageStage.setWidth(300);
        tagPageStage.setHeight(500);
        tagPageStage.resizableProperty().setValue(Boolean.FALSE);
        tagPageStage.setScene(new Scene(controller.getTagPage()));
        tagPageStage.show();
    }
    //edit tag , open tagPage
    private void editTag(){
        Tag selectedTag = mainPage.getTag().getSelectionModel().getSelectedItem();
        if (selectedTag != null){
            TagPageController controller = new TagPageController(loggedUser , mainPage , selectedTag);

            Stage tagPageStage = new Stage();
            tagPageStage.setTitle("Gmail - Edit Tag");
            tagPageStage.setWidth(300);
            tagPageStage.setHeight(500);
            tagPageStage.resizableProperty().setValue(Boolean.FALSE);
            tagPageStage.setScene(new Scene(controller.getTagPage()));
            tagPageStage.show();
        }
    }
    //delete tag
    private void deleteTag(){
        ArrayList<Save> saves = Save.getAllSaves();

        Tag selectedTag = mainPage.getTag().getSelectionModel().getSelectedItem();
        if (selectedTag != null){
            Tag tag = getTagWithWord(selectedTag.getTagName());
            //when tag delete from Tag tab it should delete from tag table and all emails that have this tag , tag
            //should remove from those emails
            for (int i = 0; i < saves.size(); i++) {
                if (tag.getTagId() == saves.get(i).getTagId()){
                    saves.get(i).delete();
                }
            }
            mainPage.getTag().getItems().remove(selectedTag);
            //delete tag from database
            tag.delete();
        }
    }

    private Tag getTagWithWord(String word){
        ArrayList<Tag> tags = Tag.getAllTags();

        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getTagName().equals(word)){
                return tags.get(i);
            }
        }
        return null;
    }
    //open addTage page
    private void openAddTagPage(){
        Email selectedEmail = mainPage.getInbox().getSelectionModel().getSelectedItem();
        if (selectedEmail != null) {
            AddTagPageController controller = new AddTagPageController(selectedEmail , mainPage);

            Stage addTagPageStage = new Stage();
            addTagPageStage.setTitle("Gmail - Add Tag");
            addTagPageStage.setWidth(300);
            addTagPageStage.setHeight(500);
            addTagPageStage.resizableProperty().setValue(Boolean.FALSE);
            addTagPageStage.setScene(new Scene(controller.getAddTagPage()));
            addTagPageStage.show();
        }
    }
    //open emails of tag page
    private void openShowEmailPage(){
        Tag selectedTag = mainPage.getTag().getSelectionModel().getSelectedItem();
        if (selectedTag != null){
            ShowEmailPageController controller = new ShowEmailPageController(selectedTag , loggedUser);

            Stage showEmailPageStage = new Stage();
            showEmailPageStage.setTitle("Gmail - Emails of Tag");
            showEmailPageStage.setWidth(700);
            showEmailPageStage.setHeight(600);
            showEmailPageStage.setScene(new Scene(controller.getShowEmailPage()));
            showEmailPageStage.show();
        }
    }
    //open tags of email page
    private void openShowTagPage(){
        Email selectedEmail = mainPage.getInbox().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowTagPageController controller = new ShowTagPageController(selectedEmail);

            Stage showTagPageStage = new Stage();
            showTagPageStage.setTitle("Gmail - Tags of Email");
            showTagPageStage.setWidth(700);
            showTagPageStage.setHeight(600);
            showTagPageStage.setScene(new Scene(controller.getShowTagPage()));
            showTagPageStage.show();
        }
    }
    //show email information in showPage for inbox table
    private void openShowEmailInboxPage(){
        Email selectedEmail = mainPage.getInbox().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowPageController controller = new ShowPageController(selectedEmail);

            Stage showPageStage = new Stage();
            showPageStage.setTitle("Gmail - Email Information");
            showPageStage.setWidth(450);
            showPageStage.setHeight(500);
            showPageStage.resizableProperty().setValue(Boolean.FALSE);
            showPageStage.setScene(new Scene(controller.getShowPage()));
            showPageStage.show();
        }
    }
    //show email information in showPage for sent table
    private void openShowEmailSentPage(){
        Email selectedEmail = mainPage.getSent().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowPageController controller = new ShowPageController(selectedEmail);

            Stage showPageStage = new Stage();
            showPageStage.setTitle("Gmail - Email Information");
            showPageStage.setWidth(450);
            showPageStage.setHeight(500);
            showPageStage.resizableProperty().setValue(Boolean.FALSE);
            showPageStage.setScene(new Scene(controller.getShowPage()));
            showPageStage.show();
        }
    }
    //show email information in showPage for trash table
    private void openShowEmailTrashPage(){
        Email selectedEmail = mainPage.getTrash().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowPageController controller = new ShowPageController(selectedEmail);

            Stage showPageStage = new Stage();
            showPageStage.setTitle("Gmail - Email Information");
            showPageStage.setWidth(450);
            showPageStage.setHeight(500);
            showPageStage.resizableProperty().setValue(Boolean.FALSE);
            showPageStage.setScene(new Scene(controller.getShowPage()));
            showPageStage.show();
        }
    }
    //show email information in showPage for draft table
    private void openShowEmailDraftPage(){
        Email selectedEmail = mainPage.getDraft().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowPageController controller = new ShowPageController(selectedEmail);

            Stage showPageStage = new Stage();
            showPageStage.setTitle("Gmail - Email Information");
            showPageStage.setWidth(450);
            showPageStage.setHeight(500);
            showPageStage.resizableProperty().setValue(Boolean.FALSE);
            showPageStage.setScene(new Scene(controller.getShowPage()));
            showPageStage.show();
        }
    }
    //show email information in showPage for spam table
    private void openShowEmailSpamPage(){
        Email selectedEmail = mainPage.getSpam().getSelectionModel().getSelectedItem();
        if (selectedEmail != null){
            ShowPageController controller = new ShowPageController(selectedEmail);

            Stage showPageStage = new Stage();
            showPageStage.setTitle("Gmail - Email Information");
            showPageStage.setWidth(450);
            showPageStage.setHeight(500);
            showPageStage.resizableProperty().setValue(Boolean.FALSE);
            showPageStage.setScene(new Scene(controller.getShowPage()));
            showPageStage.show();
        }
    }


    public MainPage getMainPage() {
        return mainPage;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}