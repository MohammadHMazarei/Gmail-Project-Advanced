package com.company.view;

import com.company.model.Email;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ShowEmailPage extends BorderPane {
    private TableView<Email> showEmail;


    public ShowEmailPage(){
        setShowEmail(new TableView<>());

        TableColumn<Email , String> showEmailCol = new TableColumn<>("Email Id");
        TableColumn<Email , String> showEmailFromCol = new TableColumn<>("From");
        TableColumn<Email , String> showEmailToCol = new TableColumn<>("To");
        TableColumn<Email , String> showEmailSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String> showEmailTextCol = new TableColumn<>("Text");
        TableColumn<Email , String> showEmailDateCol = new TableColumn<>("Date");

        showEmailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        showEmailFromCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        showEmailToCol.setCellValueFactory(new PropertyValueFactory<>("usernameReceive"));
        showEmailSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        showEmailTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        showEmailDateCol.setCellValueFactory(new PropertyValueFactory<>("postageDate"));

        showEmail.getColumns().addAll(showEmailCol , showEmailFromCol , showEmailToCol , showEmailSubjectCol , showEmailTextCol , showEmailDateCol);
        showEmail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.setCenter(showEmail);
    }



    public TableView<Email> getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(TableView<Email> showEmail) {
        this.showEmail = showEmail;
    }
}
