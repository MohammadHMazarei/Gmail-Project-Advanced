package com.company.controller;

import com.company.model.Email;
import com.company.model.User;
import com.company.view.ShowPage;

import java.util.ArrayList;

public class ShowPageController {
    private ShowPage showPage;
    private Email selectedEmail;


    public ShowPageController(Email selectedEmail){
        showPage = new ShowPage();
        setSelectedEmail(selectedEmail);
        show();
    }

    private void show(){
        Email email = getEmailWithEmailId(selectedEmail.getEmailId());
        User sender = getUserWithUserId(email.getSenderId());
        User receiver = getUserWithUserId(email.getReceiverId());

        showPage.getFrom().setText(sender.getUserName());
        showPage.getTo().setText(receiver.getUserName());
        showPage.getSubject().setText(email.getSubject());
        showPage.getText().setText(email.getText());
        showPage.getDate().setText(email.getPostageDate());
    }

    private Email getEmailWithEmailId(int emailId){
        ArrayList<Email> emails = Email.getAllEmails();

        for(Email email : emails){
            if (email.getEmailId() == emailId)
                return email;
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



    public ShowPage getShowPage() {
        return showPage;
    }

    public Email getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(Email selectedEmail) {
        this.selectedEmail = selectedEmail;
    }
}
