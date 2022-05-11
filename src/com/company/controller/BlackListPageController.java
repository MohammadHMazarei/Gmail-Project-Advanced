package com.company.controller;

import com.company.model.BlackList;
import com.company.model.User;
import com.company.view.BlackListPage;
import com.company.view.MainPage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BlackListPageController {
    private BlackListPage blackListPage;
    private MainPage mainPage;
    private User loggedUser;


    public BlackListPageController(User loggedUser , MainPage mainPage){
        blackListPage = new BlackListPage();
        setMainPage(mainPage);
        setLoggedUser(loggedUser);
        setOnActions();
    }

    private void setOnActions(){
        blackListPage.getConfirmBTN().setOnAction(e -> {
            confirmBTN();
        });
    }

    private void confirmBTN(){
        if (checkField()){
            BlackList blackList = new BlackList(blackListPage.getTextField().getText() , getLoggedUser().getUserId());
            blackList.save();

            BlackList b1 = getBlackListWithWord(blackList.getBlackStr());

            getMainPage().getBlackList().getItems().add(new BlackList(b1.getBlackId(), b1.getBlackStr()));
        }
    }

    private boolean checkField(){
        if (blackListPage.getTextField().getText().isEmpty()){
            blackListPage.getErrorLBL().setText("ERROR : FILL THE BLANK");
            blackListPage.getErrorLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private BlackList getBlackListWithWord(String word){
        ArrayList<BlackList> blackLists = BlackList.getAllBlackList();

        for (int i = 0; i < blackLists.size(); i++) {
            if (loggedUser.getUserId() != blackLists.get(i).getSenderId())
                continue;
            if (blackLists.get(i).getBlackStr().equals(word)){
                return blackLists.get(i);
            }
        }
        return null;
    }


    public BlackListPage getBlackListPage() {
        return blackListPage;
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
