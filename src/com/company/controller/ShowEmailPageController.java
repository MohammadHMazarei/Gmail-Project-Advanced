package com.company.controller;

import com.company.model.Email;
import com.company.model.Save;
import com.company.model.Tag;
import com.company.model.User;
import com.company.view.ShowEmailPage;

import java.util.ArrayList;

public class ShowEmailPageController {
    private ShowEmailPage showEmailPage;
    private Tag selectedTag;
    private User loggedUser;


    public ShowEmailPageController(Tag selectedTag , User loggedUser){
        setSelectedTag(selectedTag);
        setLoggedUser(loggedUser);
        showEmailPage = new ShowEmailPage();
        show();
    }

    private void show(){
        ArrayList<Save> saves = Save.getAllSaves();
        for (int i = 0; i < saves.size(); i++) {
            if (saves.get(i).getTagId() == selectedTag.getTagId()){
                Email email = getEmailWithEmailId(saves.get(i).getEmailId());

                User sender = getUserWithUserId(email.getSenderId());

                showEmailPage.getShowEmail().getItems().add(new Email(email.getEmailId() , sender.getUserName()
                        , loggedUser.getUserName() , email.getSubject() , email.getText() , email.getPostageDate()));
            }
        }
    }

    private Email getEmailWithEmailId(int id){
        ArrayList<Email> emails = Email.getAllEmails();
        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).getEmailId() == id)
                return emails.get(i);
        }
        return null;
    }

    private User getUserWithUserId(int id){
        ArrayList<User> users = User.getAllUsers();

        for (User user : users){
            if (user.getUserId() == id)
                return user;
        }
        return null;
    }


    public ShowEmailPage getShowEmailPage() {
        return showEmailPage;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
