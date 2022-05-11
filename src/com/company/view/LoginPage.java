package com.company.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginPage extends BorderPane {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLBL;
    private Button loginBTN;
    private Button registerBTN;
    private Button exitBTN;


    public LoginPage(){
        setUsernameField(new TextField());
        setPasswordField(new PasswordField());
        setErrorLBL(new Label(""));
        setLoginBTN(new Button("Login"));
        setRegisterBTN(new Button("Register"));
        setExitBTN(new Button("Exit"));

        Label usernameLBL = new Label("Username : ");
        Label passwordLBL = new Label("Password : ");

        errorLBL.setPrefWidth(217);
        errorLBL.setPrefHeight(18);
        errorLBL.setLayoutX(207);
        errorLBL.setLayoutY(128);
        usernameLBL.setLayoutX(206);
        usernameLBL.setLayoutY(168);
        usernameField.setLayoutX(290);
        usernameField.setLayoutY(167);
        passwordLBL.setLayoutX(206);
        passwordLBL.setLayoutY(209);
        passwordField.setLayoutX(290);
        passwordField.setLayoutY(208);
        registerBTN.setPrefWidth(125);
        registerBTN.setPrefHeight(26);
        registerBTN.setLayoutX(207);
        registerBTN.setLayoutY(250);
        loginBTN.setPrefWidth(125);
        loginBTN.setPrefHeight(26);
        loginBTN.setLayoutX(350);
        loginBTN.setLayoutY(250);
        exitBTN.setPrefWidth(268);
        exitBTN.setPrefHeight(26);
        exitBTN.setLayoutX(207);
        exitBTN.setLayoutY(287);

        Group group = new Group(errorLBL , usernameLBL , usernameField , passwordLBL , passwordField , registerBTN ,
                loginBTN , exitBTN);

        this.setCenter(group);
    }


    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Label getErrorLBL() {
        return errorLBL;
    }

    public void setErrorLBL(Label errorLBL) {
        this.errorLBL = errorLBL;
    }

    public Button getLoginBTN() {
        return loginBTN;
    }

    public void setLoginBTN(Button loginBTN) {
        this.loginBTN = loginBTN;
    }

    public Button getRegisterBTN() {
        return registerBTN;
    }

    public void setRegisterBTN(Button registerBTN) {
        this.registerBTN = registerBTN;
    }

    public Button getExitBTN() {
        return exitBTN;
    }

    public void setExitBTN(Button exitBTN) {
        this.exitBTN = exitBTN;
    }
}
