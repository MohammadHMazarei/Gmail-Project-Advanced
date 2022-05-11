package com.company.controller;


import com.company.model.User;
import com.company.view.UserInformationPage;
import javafx.stage.Stage;

public class UserInformationPageController {
    //create a userInformaationPage to load information page
    private UserInformationPage userInformationPage;
    private User loggedUser;


    public UserInformationPageController(){
        //load userInformationPage
        userInformationPage = new UserInformationPage();
        setOnActions();
    }

    //Initialization loggedUser in loginPageController when mainPage is loading
    public void initUser(User loggedUser){
        this.loggedUser = loggedUser;
    }

    //exit button action = close userInformationPage
    private void setOnActions(){
        getUserInformationPage().getExitBTN().setOnAction(e -> {
            closeStage();
        });
    }

    //close stage
    private void closeStage(){
        ( (Stage)getUserInformationPage().getExitBTN().getScene().getWindow()).close();
        MainPageController.userInfoPage = null;
    }

    //get userInformationPage
    public UserInformationPage getUserInformationPage() {
        return userInformationPage;
    }
}
