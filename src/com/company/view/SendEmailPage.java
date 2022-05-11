package com.company.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class SendEmailPage extends BorderPane{

    private Label composeLBL;
    private TextField toField;
    private TextField subjectField;
    private TextArea textArea;
    private Button sendBTN;
    private Button exitBTN;
    private Button undoBTN;


    public SendEmailPage(){

        Text toTXT = new Text("To : ");
        Text subjectTXT = new Text("Subject : ");
        Text text = new Text("Text : ");

        setComposeLBL(new Label());
        setToField(new TextField());
        setSubjectField(new TextField());
        setTextArea(new TextArea());
        setSendBTN(new Button("Send"));
        setExitBTN(new Button("Exit"));
        setUndoBTN(new Button("Undo"));
        toField.setPromptText("Username");
        subjectField.setPromptText("Subject");
        textArea.setPromptText("Number of characters allowed : 200");
        composeLBL.setLayoutX(40);
        composeLBL.setLayoutY(-36);
        toTXT.setLayoutY(18);
        subjectTXT.setLayoutY(52);
        text.setLayoutY(91);
        toField.setPrefWidth(210);
        toField.setPrefHeight(26);
        toField.setLayoutX(56);
        subjectField.setPrefWidth(209);
        subjectField.setPrefHeight(26);
        subjectField.setLayoutX(57);
        subjectField.setLayoutY(34);
        textArea.setPrefWidth(210);
        textArea.setPrefHeight(214);
        textArea.setLayoutX(56);
        textArea.setLayoutY(78);
        sendBTN.setPrefWidth(98);
        sendBTN.setPrefHeight(32);
        sendBTN.setLayoutX(168);
        sendBTN.setLayoutY(314);
        exitBTN.setPrefWidth(98);
        exitBTN.setPrefHeight(32);
        exitBTN.setLayoutX(55);
        exitBTN.setLayoutY(314);
        undoBTN.setPrefWidth(210);
        undoBTN.setLayoutX(55);
        undoBTN.setLayoutY(353);
        undoBTN.setDisable(true);

        Group all = new Group(toTXT , subjectTXT , text , getToField() , getSubjectField() , getTextArea() ,
                getSendBTN() , getExitBTN() , getUndoBTN()  , getComposeLBL());

        this.setCenter(all);
    }


    public Label getComposeLBL() {
        return composeLBL;
    }

    public void setComposeLBL(Label composeLBL) {
        this.composeLBL = composeLBL;
    }

    public TextField getToField() {
        return toField;
    }

    public void setToField(TextField toField) {
        this.toField = toField;
    }

    public TextField getSubjectField() {
        return subjectField;
    }

    public void setSubjectField(TextField subjectField) {
        this.subjectField = subjectField;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public Button getSendBTN() {
        return sendBTN;
    }

    public void setSendBTN(Button sendBTN) {
        this.sendBTN = sendBTN;
    }

    public Button getExitBTN() {
        return exitBTN;
    }

    public void setExitBTN(Button exitBTN) {
        this.exitBTN = exitBTN;
    }

    public Button getUndoBTN() {
        return undoBTN;
    }

    public void setUndoBTN(Button undoBTN) {
        this.undoBTN = undoBTN;
    }
}
