package com.company.controller;

import com.company.model.Email;
import com.company.model.Save;
import com.company.model.Tag;
import com.company.view.AddTagPage;
import com.company.view.MainPage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AddTagPageController {
    private AddTagPage addTagPage;
    private Email selectedEmail;
    private MainPage mainPage;


    public AddTagPageController(Email selectedEmail , MainPage mainPage){
        addTagPage = new AddTagPage();
        setSelectedEmail(selectedEmail);
        setMainPage(mainPage);
        setOnActions();
    }

    private void setOnActions(){
        addTagPage.getConfirmBTN().setOnAction(e ->{
            confirmBTN();
        });
    }

    private void confirmBTN(){
        if (checkField()) {
            if (checkTagWithName(addTagPage.getTextField().getText())) {
                if (checkExist(addTagPage.getTextField().getText())) {
                    Tag tag = getTagWithTagName(addTagPage.getTextField().getText());

                    Save save = new Save(tag.getTagId() , selectedEmail.getEmailId());
                    save.save();
                    addTagPage.getTextField().setText("");
                    addTagPage.getErrorLBL().setText("");
                }
            }
        }
    }

    private boolean checkField(){
        if (addTagPage.getTextField().getText().isEmpty()){
            addTagPage.getErrorLBL().setText("ERROR : FILL THE BLANK");
            addTagPage.getErrorLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private boolean checkTagWithName(String tag) {
        ArrayList<Tag> tags = Tag.getAllTags();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getTagName().equals(tag)) {
                return true;
            }
        }
        addTagPage.getErrorLBL().setText("ERROR : NO TAG FOUND");
        addTagPage.getErrorLBL().setTextFill(Color.RED);
        return false;
    }

    private boolean checkExist(String tag){
        Tag tag1 = getTagWithTagName(tag);
        ArrayList<Save> saves = Save.getAllSaves();
        for (int i = 0; i < saves.size(); i++) {
            if (saves.get(i).getEmailId() == getSelectedEmail().getEmailId() && saves.get(i).getTagId() == tag1.getTagId()){
                addTagPage.getErrorLBL().setText("ERROR : TAG EXIST");
                addTagPage.getErrorLBL().setTextFill(Color.RED);
                return false;
            }
        }
        return true;
    }

    private Tag getTagWithTagName(String tagName){
        ArrayList<Tag> getAllTags = Tag.getAllTags();
        for (int i = 0; i < getAllTags.size(); i++) {
            if (getAllTags.get(i).getTagName().equals(tagName))
                return getAllTags.get(i);
        }
        return null;
    }




    public AddTagPage getAddTagPage() {
        return addTagPage;
    }

    public Email getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(Email selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
