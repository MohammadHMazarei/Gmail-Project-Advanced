package com.company.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class UserInformationPage extends VBox {

    private Text nameField;
    private Text lastNameField;
    private Text usernameField;
    private Text passwordField;
    private Text placeOfBirthField;
    private Text addressField;
    private Text phoneNumberField;
    private Text BirthdayField;
    private Button exitBTN;


    public UserInformationPage(){
        Text nameTitle = new Text("Name : ");
        Text lastNameTitle = new Text("Last name : ");
        Text usernameTitle = new Text("Username : ");
        Text passwordTitle = new Text("Password : ");
        Text placeOfBirthTitle = new Text("Place of birth : ");
        Text addressTitle = new Text("Address : ");
        Text phoneNumberTitle = new Text("Phone number : ");
        Text birthdayTitle = new Text("Birthday : ");
        VBox title = new VBox(nameTitle , lastNameTitle , usernameTitle , passwordTitle , placeOfBirthTitle , addressTitle
        , phoneNumberTitle , birthdayTitle);
        title.setAlignment(Pos.CENTER);
        title.setSpacing(6);


        setNameField(new Text("Name"));
        setLastNameField(new Text("Last name"));
        setUsernameField(new Text("Username"));
        setPasswordField(new Text("Password"));
        setPlaceOfBirthField(new Text("Place of birth"));
        setAddressField(new Text("Address"));
        setPhoneNumberField(new Text("Phone number"));
        setBirthdayField(new Text("Birthday"));
        VBox field = new VBox(getNameField() , getLastNameField() , getUsernameField() , getPasswordField() ,
                getPlaceOfBirthField() , getAddressField() , getPhoneNumberField() , getBirthdayField());
        field.setAlignment(Pos.CENTER);
        field.setSpacing(6.8);

        HBox all = new HBox(title , field);
        all.setAlignment(Pos.CENTER);

        setExitBTN(new Button("Exit"));
        exitBTN.setAlignment(Pos.CENTER);

        HBox btn = new HBox(exitBTN);
        btn.setAlignment(Pos.CENTER);
        btn.setPrefWidth(82);

        this.getChildren().addAll(all , btn);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
    }


    public Text getNameField() {
        return nameField;
    }

    public void setNameField(Text nameField) {
        this.nameField = nameField;
    }

    public Text getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(Text lastNameField) {
        this.lastNameField = lastNameField;
    }

    public Text getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(Text usernameField) {
        this.usernameField = usernameField;
    }

    public Text getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(Text passwordField) {
        this.passwordField = passwordField;
    }

    public Text getPlaceOfBirthField() {
        return placeOfBirthField;
    }

    public void setPlaceOfBirthField(Text placeOfBirthField) {
        this.placeOfBirthField = placeOfBirthField;
    }

    public Text getAddressField() {
        return addressField;
    }

    public void setAddressField(Text addressField) {
        this.addressField = addressField;
    }

    public Text getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(Text phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public Text getBirthdayField() {
        return BirthdayField;
    }

    public void setBirthdayField(Text birthdayField) {
        BirthdayField = birthdayField;
    }

    public Button getExitBTN() {
        return exitBTN;
    }

    public void setExitBTN(Button exitBTN) {
        this.exitBTN = exitBTN;
    }
}
