package com.company.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddTagPage extends VBox {
    private Label errorLBL;
    private TextField textField;
    private Button confirmBTN;


    public AddTagPage(){
        setErrorLBL(new Label(""));
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
