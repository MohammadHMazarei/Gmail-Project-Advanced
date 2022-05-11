package com.company.controller;

import com.company.model.Email;
import com.company.model.Save;
import com.company.model.Tag;
import com.company.view.ShowTagPage;

import java.util.ArrayList;

public class ShowTagPageController {
    private ShowTagPage showTagPage;
    private Email selectedEmail;


    public ShowTagPageController(Email selectedEmail){
        setSelectedEmail(selectedEmail);
        showTagPage = new ShowTagPage();
        setOnAction();
    }

    private void setOnAction(){
        showTags();
        showTagPage.getDeleteBTN().setOnAction(e ->{
            delete();
        });
    }

    private void showTags(){
        ArrayList<Save> saves = Save.getAllSaves();

        for (int i = 0; i < saves.size(); i++) {
            if (saves.get(i).getEmailId() == selectedEmail.getEmailId()){
                Tag tag = getTagWithId(saves.get(i).getTagId());

                showTagPage.getTag().getItems().add(new Tag(tag.getTagId() , tag.getTagName()));
            }
        }
    }

    public Tag getTagWithId(int id){
        ArrayList<Tag> getAllTags = Tag.getAllTags();
        for (int i = 0; i < getAllTags.size(); i++) {
            if (getAllTags.get(i).getTagId() == id)
                return getAllTags.get(i);
        }
        return null;
    }

    private void delete(){
        Tag selectedTag = showTagPage.getTag().getSelectionModel().getSelectedItem();
        if (selectedTag != null){
            showTagPage.getTag().getItems().remove(selectedTag);

            Save save = getSaveWithTagId(selectedTag.getTagId());

            save.delete();
        }
    }

    private Save getSaveWithTagId(int id){
        ArrayList<Save> saves = Save.getAllSaves();
        for (int i = 0; i < saves.size(); i++) {
            if (saves.get(i).getTagId() == id){
                return saves.get(i);
            }
        }
        return null;
    }




    public ShowTagPage getShowTagPage() {
        return showTagPage;
    }

    public void setShowTagPage(ShowTagPage showTagPage) {
        this.showTagPage = showTagPage;
    }

    public Email getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(Email selectedEmail) {
        this.selectedEmail = selectedEmail;
    }
}
