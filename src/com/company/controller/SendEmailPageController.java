package com.company.controller;

import com.company.model.BlackList;
import com.company.model.Email;
import com.company.model.User;
import com.company.view.MainPage;
import com.company.view.SendEmailPage;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class SendEmailPageController {

    private SendEmailPage sendEmailPage;
    private User receiverUser;
    private int receiverId;
    private Email email;
    private MainPage mainPage;
    private User loggedUser;

    public SendEmailPageController(Email email , MainPage mainPage , User loggedUser){
        setEmail(email);
        setMainPage(mainPage);
        setLoggedUser(loggedUser);
        sendEmailPage = new SendEmailPage();
        setOnAction();
    }

    private void setOnAction(){
        sendEmailPage.getSendBTN().setOnAction(e -> {
            editEmail();
        });

        sendEmailPage.getExitBTN().setOnAction(e -> {
            closeStage();
        });
    }


    private void editEmail(){
        Email email1 = getEmailWithEmailId(email.getEmailId());
        if (checkAllEmailFields()){
            if (checkReceiverExist(sendEmailPage.getToField().getText())){
                setReceiverUser(getReceiverWithUsername(sendEmailPage.getToField().getText()));
                setReceiverId(getReceiverUser().getUserId());
                if (checkLengthOfText()){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);

                    Thread thread = new Thread(() ->{
                        AtomicBoolean a = new AtomicBoolean(true);

                        sendEmailPage.getToField().setEditable(false);
                        sendEmailPage.getSubjectField().setEditable(false);
                        sendEmailPage.getTextArea().setEditable(false);
                        sendEmailPage.getSendBTN().setDisable(true);
                        sendEmailPage.getExitBTN().setDisable(true);
                        sendEmailPage.getUndoBTN().setDisable(false);

                        sendEmailPage.getUndoBTN().setOnAction(e -> {
                            a.set(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sendEmailPage.getToField().setEditable(true);
                                    sendEmailPage.getSubjectField().setEditable(true);
                                    sendEmailPage.getTextArea().setEditable(true);
                                    sendEmailPage.getSendBTN().setDisable(false);
                                    sendEmailPage.getExitBTN().setDisable(false);
                                    sendEmailPage.getUndoBTN().setDisable(true);
                                }
                            });
                        });

                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        if (a.get()) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    mainPage.getDraft().getItems().remove(email);

                                    email1.setSubject(sendEmailPage.getSubjectField().getText());
                                    email1.setText(sendEmailPage.getTextArea().getText());
                                    email1.setPostageDate(currentDate);
                                    email1.setIsDraft("false");
                                    if (checkIsSpam())
                                        email1.setIsSpam("true");

                                    email1.save();

                                    mainPage.getSent().getItems().add(new Email(email1.getEmailId(), getReceiverUser().getUserName(),
                                            sendEmailPage.getSubjectField().getText(), sendEmailPage.getTextArea().getText(), currentDate));

                                    sendEmailPage.getScene().getWindow().hide();
                                }
                            });
                        }
                    });
                    thread.start();
                }
            }
        }
    }

    private Email getEmailWithEmailId(int emailId){
        ArrayList<Email> emails = Email.getAllEmails();

        for(Email email : emails){
            if (email.getEmailId() == emailId)
                return email;
        }
        return null;
    }

    private boolean checkIsSpam(){
        ArrayList<BlackList> blackLists = BlackList.getAllBlackList();
        loggedUser.blackList = new ArrayList<>();
        for (int i = 0; i < blackLists.size(); i++) {
            getLoggedUser().blackList.add(blackLists.get(i).getBlackStr());
        }
        for (int i = 0; i < getLoggedUser().blackList.size(); i++) {
            if (sendEmailPage.getSubjectField().getText().contains(getLoggedUser().blackList.get(i)) ||
                    sendEmailPage.getTextArea().getText().contains(getLoggedUser().blackList.get(i)))
                return true;
        }
        return false;
    }


    private boolean checkAllEmailFields(){
        if (sendEmailPage.getToField().getText().isEmpty() && sendEmailPage.getSubjectField().getText().isEmpty() &&
                sendEmailPage.getTextArea().getText().isEmpty()){
            sendEmailPage.getComposeLBL().setText("ERROR : FILL THE BLANK");
            sendEmailPage.getComposeLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private boolean checkReceiverExist(String username){
        ArrayList<User> users = User.getAllUsers();

        for (User user1 : users) {
            if (user1.getUserName().equals(username)) {
                return true;
            }
        }
        sendEmailPage.getComposeLBL().setText("ERROR : NO USER FOUND");
        sendEmailPage.getComposeLBL().setTextFill(Color.RED);
        return false;
    }

    private boolean checkLengthOfText(){
        if (sendEmailPage.getTextArea().getText().length() > 200){
            sendEmailPage.getComposeLBL().setText("ERROR : TEXT TOO LONG 200/200");
            sendEmailPage.getComposeLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private User getReceiverWithUsername(String username){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }



    private void closeStage(){
        ( (Stage)getSendEmailPage().getExitBTN().getScene().getWindow()).close();
        MainPageController.sendEmailPage = null;
    }

    public SendEmailPage getSendEmailPage() {
        return sendEmailPage;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
