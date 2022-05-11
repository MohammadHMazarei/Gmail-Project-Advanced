package com.company.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterPage extends BorderPane {

    private TextField name;
    private TextField lastName;
    private TextField username;
    private PasswordField passwordField;
    private TextField placeOfBirth;
    private TextField Address;
    private TextField phoneNumber;
    private DatePicker birthday;
    private Button registerBTN;
    private Button cancelBTN;
    private Label errorLabel;


    public RegisterPage(){
        Label nameLBL = new Label("Name : ");
        Label lastNameLBL = new Label("Last name : ");
        Label usernameLBL = new Label("Username : ");
        Label passwordLBL = new Label("Password : ");
        Label placeOfBirthLBL = new Label("Place of birth : ");
        Label addressLBL = new Label("Address : ");
        Label phoneNumberLBL = new Label("Phone number : ");
        Label birthdayLBL = new Label("Birthday : ");
        setCancelBTN(new Button("Cancel"));
        cancelBTN.setPrefWidth(80);
        VBox labels = new VBox(nameLBL , lastNameLBL , usernameLBL , passwordLBL , placeOfBirthLBL , addressLBL ,
                phoneNumberLBL , birthdayLBL , getCancelBTN());
        labels.setAlignment(Pos.CENTER);
        labels.setSpacing(22.88);


        setName(new TextField());
        setLastName(new TextField());
        setUsername(new TextField());
        setPasswordField(new PasswordField());
        setPlaceOfBirth(new TextField());
        setAddress(new TextField());
        setPhoneNumber(new TextField());
        setBirthday(new DatePicker());
        setRegisterBTN(new Button("Register"));
        registerBTN.setPrefWidth(176);
        registerBTN.setPrefHeight(26);
        VBox fields = new VBox(getName() , getLastName() , getUsername() , getPasswordField()  ,
                getPlaceOfBirth() , getAddress() , getPhoneNumber()  , getBirthday() , getRegisterBTN());
        fields.setAlignment(Pos.CENTER);
        fields.setSpacing(15);


        HBox hBoxIn = new HBox(labels , fields);
        hBoxIn.setAlignment(Pos.CENTER);
        hBoxIn.setSpacing(9);
        setErrorLabel(new Label(""));

        VBox vBoxOut = new VBox(getErrorLabel() , hBoxIn);
        vBoxOut.setAlignment(Pos.CENTER);

        this.setCenter(vBoxOut);
    }




    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(TextField lastName) {
        this.lastName = lastName;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(TextField placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public TextField getAddress() {
        return Address;
    }

    public void setAddress(TextField address) {
        Address = address;
    }

    public TextField getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(TextField phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DatePicker getBirthday() {
        return birthday;
    }

    public void setBirthday(DatePicker birthday) {
        this.birthday = birthday;
    }

    public Button getRegisterBTN() {
        return registerBTN;
    }

    public void setRegisterBTN(Button registerBTN) {
        this.registerBTN = registerBTN;
    }

    public Button getCancelBTN() {
        return cancelBTN;
    }

    public void setCancelBTN(Button cancelBTN) {
        this.cancelBTN = cancelBTN;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }
}