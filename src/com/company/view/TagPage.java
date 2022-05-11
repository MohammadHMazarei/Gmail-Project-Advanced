package com.company.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TagPage extends VBox {

    private Label errorLBL;
    private Text text;
    private TextField textField;
    private Button confirmBTN;


    public TagPage(){
        setErrorLBL(new Label(""));
        setText(new Text("Tag Word"));
        setTextField(new TextField());
        textField.setPromptText("Tag Word");
        setConfirmBTN(new Button("Confirm"));

        this.getChildren().addAll(getErrorLBL() , getTextField() , getConfirmBTN());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(8);
    }


    public Label getErrorLBL() {
        return errorLBL;
    }

    public void setErrorLBL(Label errorLBL) {
        this.errorLBL = errorLBL;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public Button getConfirmBTN() {
        return confirmBTN;
    }

    public void setConfirmBTN(Button confirmBTN) {
        this.confirmBTN = confirmBTN;
    }
}
