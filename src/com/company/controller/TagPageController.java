package com.company.controller;

import com.company.model.Tag;
import com.company.model.User;
import com.company.view.MainPage;
import com.company.view.TagPage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TagPageController {
    private TagPage tagPage;
    private MainPage mainPage;
    private User loggedUser;
    private Tag selectedTag;


    public TagPageController(User loggedUser , MainPage mainPage , Tag selectedTag){
        tagPage = new TagPage();
        setMainPage(mainPage);
        setLoggedUser(loggedUser);
        setSelectedTag(selectedTag);
        setonActions2();
    }

    public TagPageController(User loggedUser , MainPage mainPage){
        tagPage = new TagPage();
        setMainPage(mainPage);
        setLoggedUser(loggedUser);
        setOnAction();
    }




    private void setOnAction(){
        tagPage.getConfirmBTN().setOnAction(e -> {
            confirm();
        });
    }

    private void setonActions2(){
        tagPage.getConfirmBTN().setOnAction(e -> {
            edit();
        });
    }


    private void confirm(){
        if (checkField()) {
            if (checkTagNOTExist(tagPage.getTextField().getText())) {
                Tag tag = new Tag(tagPage.getTextField().getText(), getLoggedUser().getUserId());
                tag.save();

                Tag tag1 = getTagWithWord(tag.getTagName());

                getMainPage().getTag().getItems().add(new Tag(tag1.getTagId(), tag1.getTagName()));
            }
        }
    }

    private void edit(){
        if (checkField()){
            getMainPage().getTag().getItems().remove(getSelectedTag());

            Tag tag = getTagWithWord(getSelectedTag().getTagName());
            tag.setTagName(tagPage.getTextField().getText());
            tag.save();

            getMainPage().getTag().getItems().add(new Tag(tag.getTagId() , tag.getTagName()));
        }
    }

    private boolean checkField(){
        if (tagPage.getTextField().getText().isEmpty()){
            tagPage.getErrorLBL().setText("ERROR : FILL THE BLANK");
            tagPage.getErrorLBL().setTextFill(Color.RED);
            return false;
        }
        return true;
    }

    private boolean checkTagNOTExist(String field){
        ArrayList<Tag> tags = Tag.getAllTags();
        for (int i = 0; i < tags.size(); i++) {
            if (loggedUser.getUserId() != tags.get(i).getSenderId())
                continue;
            if (tags.get(i).getTagName().equals(field)){
                tagPage.getErrorLBL().setText("ERROR : TAG ALREADY EXIST");
                tagPage.getErrorLBL().setTextFill(Color.RED);
                return false;
            }
        }
        return true;
    }


    private Tag getTagWithWord(String word){
        ArrayList<Tag> tags = Tag.getAllTags();

        for (int i = 0; i < tags.size(); i++) {
            if (loggedUser.getUserId() != tags.get(i).getSenderId())
                continue;
            if (tags.get(i).getTagName().equals(word)){
                return tags.get(i);
            }
        }
        return null;
    }



    public TagPage getTagPage() {
        return tagPage;
    }

    public void setTagPage(TagPage tagPage) {
        this.tagPage = tagPage;
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

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }
}
