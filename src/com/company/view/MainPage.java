package com.company.view;

import com.company.model.BlackList;
import com.company.model.Email;
import com.company.model.Tag;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPage extends TabPane {

    private TextField nameField;
    private TextField lastNameField;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField placeOfBirthField;
    private TextField AddressField;
    private TextField phoneNumberField;
    private DatePicker birthdayField;
    private Button confirmBTN;
    private Button deleteBTN;
    private Button logoutBTN;
    private Label errorLabel;
    private Label composeLBL;
    private TextField toField;
    private TextField subjectField;
    private TextArea textArea;
    private Button sendBTN;
    private Button draftBTN;
    private Button undoBTN;
    private TableView<Email> sent;
    private Button showEmailSentBTN;
    private Button deleteSentEmailBTM;
    private TableView<Email> inbox;
    private Button showBTN;
    private Button addTag;
    private Button inboxDeleteTagBTN;
    private Button deleteInboxEmailBTN;
    private TableView<Email> trash;
    private Button showEmailTrashBTN;
    private TableView<Email> draft;
    private Button showEmailDraftBTN;
    private Button editDraftEmailBTN;
    private Button deleteDraftEmailBTN;
    private TableView<BlackList> blackList;
    private Button createBlack;
    private Button deleteBlack;
    private TableView<Email> spam;
    private Button showEmailSpamBTN;
    private Button deleteSpamBTN;
    private TableView<Tag> tag;
    private Button showEmailBTN;
    private Button createTagBTN;
    private Button editTagBTN;
    private Button deleteTagBTN;


    public MainPage(){
        // ------------------ Account design -------------------
        Label nameLBL = new Label("Name : ");
        Label lastNameLBL = new Label("Last name : ");
        Label usernameLBL = new Label("Username : ");
        Label passwordLBL = new Label("Password : ");
        Label placeOfBirthLBL = new Label("Place of birth : ");
        Label addressLBL = new Label("Address : ");
        Label phoneNumberLBL = new Label("Phone number : ");
        Label birthdayLBL = new Label("Birthday : ");
        Label logoutLBL = new Label("Logout : ");
        setDeleteBTN(new Button("Delete Account"));
        deleteBTN.setPrefWidth(125);
        VBox labels = new VBox(nameLBL , lastNameLBL , usernameLBL , passwordLBL , placeOfBirthLBL , addressLBL ,
                phoneNumberLBL , birthdayLBL , getDeleteBTN() , logoutLBL);
        labels.setAlignment(Pos.CENTER);
        labels.setSpacing(25.49);


        setNameField(new TextField());
        setLastNameField(new TextField());
        setUsernameField(new TextField());
        usernameField.setEditable(false);
        setPasswordField(new PasswordField());
        setPlaceOfBirthField(new TextField());
        setAddressField(new TextField());
        setPhoneNumberField(new TextField());
        setBirthdayField(new DatePicker());
        setConfirmBTN(new Button("Confirm Edit"));
        setLogoutBTN(new Button("Logout"));
        confirmBTN.setPrefWidth(218);
        confirmBTN.setPrefHeight(26);
        logoutBTN.setPrefWidth(218);
        logoutBTN.setPrefHeight(26);
        VBox fields = new VBox(getNameField() , getLastNameField() , getUsernameField() , getPasswordField()  ,
                getPlaceOfBirthField() , getAddressField() , getPhoneNumberField()  , getBirthdayField() , getConfirmBTN() , getLogoutBTN());
        fields.setAlignment(Pos.CENTER);
        fields.setSpacing(15);


        HBox hBoxIn = new HBox(labels , fields);
        hBoxIn.setAlignment(Pos.CENTER);
        hBoxIn.setSpacing(9);
        setErrorLabel(new Label(""));

        VBox vBoxOut = new VBox(getErrorLabel() , hBoxIn);
        vBoxOut.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBoxOut);

        Tab account = new Tab("Account");
        account.setContent(borderPane);
        this.getTabs().add(account);

        // ------------------ Compose design -------------------

        Text toTXT = new Text("To : ");
        Text subjectTXT = new Text("Subject : ");
        Text text = new Text("Text : ");

        setComposeLBL(new Label());
        setToField(new TextField());
        setSubjectField(new TextField());
        setTextArea(new TextArea());
        setSendBTN(new Button("Send"));
        setDraftBTN(new Button("Draft"));
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
        draftBTN.setPrefWidth(98);
        draftBTN.setPrefHeight(32);
        draftBTN.setLayoutX(55);
        draftBTN.setLayoutY(314);
        undoBTN.setPrefWidth(210);
        undoBTN.setLayoutX(55);
        undoBTN.setLayoutY(353);
        undoBTN.setDisable(true);

        Group all = new Group(toTXT , subjectTXT , text , getToField() , getSubjectField() , getTextArea() ,
                getSendBTN() , getDraftBTN() , getUndoBTN() , getComposeLBL());

        BorderPane borderPane1 = new BorderPane();
        borderPane1.setCenter(all);

        Tab compose = new Tab("Compose");
        compose.setContent(borderPane1);
        this.getTabs().add(compose);

        //--------------------- Inbox design ------------------------

        setInbox(new TableView<>());
        setDeleteInboxEmailBTN(new Button("Delete Email"));
        setAddTag(new Button("Add Tag"));
        setInboxDeleteTagBTN(new Button("Delete Tag"));
        setShowBTN(new Button("Show Email"));
        getAddTag().setMaxWidth(Double.MAX_VALUE);
        getInboxDeleteTagBTN().setMaxWidth(Double.MAX_VALUE);
        getShowBTN().setMaxWidth(Double.MAX_VALUE);

        TableColumn<Email , String > inboxEmailIdCol = new TableColumn<>("Email Id");
        TableColumn<Email , String > inboxFromCol = new TableColumn<>("From");
        TableColumn<Email , String > inboxSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String > inboxTextCol = new TableColumn<>("Text");
        TableColumn<Email , String > inboxDateCol = new TableColumn<>("Date");

        inboxEmailIdCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        inboxFromCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        inboxSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        inboxTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        inboxDateCol.setCellValueFactory(new PropertyValueFactory<>("postageDate"));

        inbox.getColumns().addAll(inboxEmailIdCol , inboxFromCol , inboxSubjectCol , inboxTextCol , inboxDateCol);
        inbox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox =  new VBox(showBTN , addTag , inboxDeleteTagBTN , deleteInboxEmailBTN);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setSpacing(8);

        BorderPane borderPane2 = new BorderPane();
        borderPane2.setCenter(inbox);
        borderPane2.setRight(vBox);

        Tab emailInbox = new Tab("Inbox");
        emailInbox.setContent(borderPane2);
        this.getTabs().add(emailInbox);

        //--------------------- Sent design ------------------------

        setSent(new TableView<>());
        setShowEmailSentBTN(new Button("Show Email"));
        setDeleteSentEmailBTM(new Button("Delete Email"));
        showEmailSentBTN.setMaxWidth(Double.MAX_VALUE);

        TableColumn<Email , String > sentEmailIdCol = new TableColumn<>("Email Id");
        TableColumn<Email , String> sentToCol = new TableColumn<>("To");
        TableColumn<Email , String > sentSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String > sentTextCol = new TableColumn<>("Text");
        TableColumn<Email , String > sentDateCol = new TableColumn<>("Date");

        sentEmailIdCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        sentToCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        sentSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sentTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        sentDateCol.setCellValueFactory(new PropertyValueFactory<>("postageDate"));

        sent.getColumns().addAll(sentEmailIdCol , sentToCol , sentSubjectCol , sentTextCol , sentDateCol);
        sent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox1 = new VBox(showEmailSentBTN , deleteSentEmailBTM);
        vBox1.setAlignment(Pos.BOTTOM_CENTER);
        vBox1.setSpacing(8);

        BorderPane borderPane3 =  new BorderPane();
        borderPane3.setCenter(sent);
        borderPane3.setRight(vBox1);

        Tab emailSent = new Tab("Sent");
        emailSent.setContent(borderPane3);
        this.getTabs().add(emailSent);

        //--------------------- Trash design ------------------------

        setTrash(new TableView<>());
        setShowEmailTrashBTN(new Button("Show Email"));

        TableColumn<Email , String> trashEmailCol = new TableColumn<>("Email Id");
        TableColumn<Email , String> trashFromCol = new TableColumn<>("From");
        TableColumn<Email , String> trashToCol = new TableColumn<>("To");
        TableColumn<Email , String> trashSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String> trashTextCol = new TableColumn<>("Text");
        TableColumn<Email , String> trashDateCol = new TableColumn<>("Date");

        trashEmailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        trashFromCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        trashToCol.setCellValueFactory(new PropertyValueFactory<>("usernameReceive"));
        trashSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        trashTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        trashDateCol.setCellValueFactory(new PropertyValueFactory<>("postageDate"));

        trash.getColumns().addAll(trashEmailCol , trashFromCol , trashToCol , trashSubjectCol , trashTextCol , trashDateCol);
        trash.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox v1 = new VBox(showEmailTrashBTN);
        v1.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane borderPane4 = new BorderPane();
        borderPane4.setCenter(trash);
        borderPane4.setRight(v1);

        Tab emailTrash = new Tab("Trash");
        emailTrash.setContent(borderPane4);
        this.getTabs().add(emailTrash);

        //--------------------- Draft design ------------------------

        setDraft(new TableView<>());
        setEditDraftEmailBTN(new Button("Edit Email"));
        setDeleteDraftEmailBTN(new Button("Delete Email"));
        setShowEmailDraftBTN(new Button("Show Email"));
        getShowEmailDraftBTN().setMaxWidth(Double.MAX_VALUE);
        getEditDraftEmailBTN().setMaxWidth(Double.MAX_VALUE);

        TableColumn<Email , String> draftEmailIdCol = new TableColumn<>("email Id");
        TableColumn<Email , String> draftToCol = new TableColumn<>("To");
        TableColumn<Email , String > draftSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String > draftTextCol = new TableColumn<>("Text");

        draftEmailIdCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        draftToCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        draftSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        draftTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));

        draft.getColumns().addAll(draftEmailIdCol , draftToCol , draftSubjectCol , draftTextCol);
        draft.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox2 = new VBox(showEmailDraftBTN , editDraftEmailBTN , deleteDraftEmailBTN);
        vBox2.setAlignment(Pos.BOTTOM_CENTER);
        vBox2.setSpacing(8);

        BorderPane borderPane5 = new BorderPane();
        borderPane5.setCenter(draft);
        borderPane5.setRight(vBox2);

        Tab emailDraft = new Tab("Draft");
        emailDraft.setContent(borderPane5);
        this.getTabs().add(emailDraft);

        //--------------------- blacklist design ------------------------

        setBlackList(new TableView<>());
        setCreateBlack(new Button("Create Black Word"));
        setDeleteBlack(new Button("Delete Black Word"));

        TableColumn<BlackList , String> blackListIdCol = new TableColumn<>("Black Word Id");
        TableColumn<BlackList , String> blackListWordCol = new TableColumn<>("Black Word");

        blackListIdCol.setCellValueFactory(new PropertyValueFactory<>("blackId"));
        blackListWordCol.setCellValueFactory(new PropertyValueFactory<>("blackStr"));

        blackList.getColumns().addAll(blackListIdCol , blackListWordCol);
        blackList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox3 = new VBox(createBlack , deleteBlack);
        vBox3.setAlignment(Pos.BOTTOM_CENTER);
        vBox3.setSpacing(8);

        BorderPane borderPane6 = new BorderPane();
        borderPane6.setCenter(blackList);
        borderPane6.setRight(vBox3);

        Tab emailBlackList = new Tab("Black List");
        emailBlackList.setContent(borderPane6);
        this.getTabs().add(emailBlackList);

        //--------------------- Spam design ------------------------

        setSpam(new TableView<>());
        setShowEmailSpamBTN(new Button("Show Email"));
        showEmailSpamBTN.setMaxWidth(Double.MAX_VALUE);
        setDeleteSpamBTN(new Button("Delete Email"));

        TableColumn<Email , String> spamEmailIdCol = new TableColumn<>("email Id");
        TableColumn<Email , String> spamToCol = new TableColumn<>("From");
        TableColumn<Email , String > spamSubjectCol = new TableColumn<>("Subject");
        TableColumn<Email , String > spamTextCol = new TableColumn<>("Text");

        spamEmailIdCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        spamToCol.setCellValueFactory(new PropertyValueFactory<>("usernameSend"));
        spamSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        spamTextCol.setCellValueFactory(new PropertyValueFactory<>("text"));

        spam.getColumns().addAll(spamEmailIdCol , spamToCol , spamSubjectCol , spamTextCol);
        spam.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox4 = new VBox(showEmailSpamBTN , deleteSpamBTN);
        vBox4.setAlignment(Pos.BOTTOM_CENTER);
        vBox4.setSpacing(8);

        BorderPane borderPane7 = new BorderPane();
        borderPane7.setCenter(spam);
        borderPane7.setRight(vBox4);

        Tab emailSpam = new Tab("Spam");
        emailSpam.setContent(borderPane7);
        this.getTabs().add(emailSpam);

        //--------------------- Tag design ------------------------

        setTag(new TableView<>());
        setShowEmailBTN(new Button("Show Emails With Tag"));
        setCreateTagBTN(new Button("Create Tag"));
        setEditTagBTN(new Button("Edit Tag"));
        setDeleteTagBTN(new Button("Delete Tag"));
        getCreateTagBTN().setMaxWidth(Double.MAX_VALUE);
        getEditTagBTN().setMaxWidth(Double.MAX_VALUE);
        getDeleteTagBTN().setMaxWidth(Double.MAX_VALUE);

        TableColumn<Tag , String> tagIdCol = new TableColumn<>("Tag Id");
        TableColumn<Tag , String> tagNameCol = new TableColumn<>("Tag Name");

        tagIdCol.setCellValueFactory(new PropertyValueFactory<>("tagId"));
        tagNameCol.setCellValueFactory(new PropertyValueFactory<>("tagName"));

        tag.getColumns().addAll(tagIdCol , tagNameCol);
        tag.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox5 = new VBox(showEmailBTN , createTagBTN , editTagBTN , deleteTagBTN);
        vBox5.setAlignment(Pos.BOTTOM_CENTER);
        vBox5.setSpacing(8);

        BorderPane borderPane8 = new BorderPane();
        borderPane8.setCenter(tag);
        borderPane8.setRight(vBox5);

        Tab emailTag = new Tab("Tag");
        emailTag.setContent(borderPane8);
        this.getTabs().add(emailTag);

        //--------------------------------------------------------------------------------------------------------------
        this.setSide(Side.TOP);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }


    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField getPlaceOfBirthField() {
        return placeOfBirthField;
    }

    public void setPlaceOfBirthField(TextField placeOfBirthField) {
        this.placeOfBirthField = placeOfBirthField;
    }

    public TextField getAddressField() {
        return AddressField;
    }

    public void setAddressField(TextField addressField) {
        AddressField = addressField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(TextField phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public DatePicker getBirthdayField() {
        return birthdayField;
    }

    public void setBirthdayField(DatePicker birthdayField) {
        this.birthdayField = birthdayField;
    }

    public Button getConfirmBTN() {
        return confirmBTN;
    }

    public void setConfirmBTN(Button confirmBTN) {
        this.confirmBTN = confirmBTN;
    }

    public Button getDeleteBTN() {
        return deleteBTN;
    }

    public void setDeleteBTN(Button deleteBTN) {
        this.deleteBTN = deleteBTN;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
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

    public TableView<Email> getSent() {
        return sent;
    }

    public void setSent(TableView<Email> sent) {
        this.sent = sent;
    }

    public Button getDeleteSentEmailBTM() {
        return deleteSentEmailBTM;
    }

    public void setDeleteSentEmailBTM(Button deleteSentEmailBTM) {
        this.deleteSentEmailBTM = deleteSentEmailBTM;
    }

    public TableView<Email> getInbox() {
        return inbox;
    }

    public void setInbox(TableView<Email> inbox) {
        this.inbox = inbox;
    }

    public Button getDeleteInboxEmailBTN() {
        return deleteInboxEmailBTN;
    }

    public void setDeleteInboxEmailBTN(Button deleteInboxEmailBTN) {
        this.deleteInboxEmailBTN = deleteInboxEmailBTN;
    }

    public TableView<Email> getTrash() {
        return trash;
    }

    public void setTrash(TableView<Email> trash) {
        this.trash = trash;
    }

    public TableView<Email> getDraft() {
        return draft;
    }

    public void setDraft(TableView<Email> draft) {
        this.draft = draft;
    }

    public Button getEditDraftEmailBTN() {
        return editDraftEmailBTN;
    }

    public void setEditDraftEmailBTN(Button editDraftEmailBTN) {
        this.editDraftEmailBTN = editDraftEmailBTN;
    }

    public Button getDeleteDraftEmailBTN() {
        return deleteDraftEmailBTN;
    }

    public void setDeleteDraftEmailBTN(Button deleteDraftEmailBTN) {
        this.deleteDraftEmailBTN = deleteDraftEmailBTN;
    }

    public Button getDraftBTN() {
        return draftBTN;
    }

    public void setDraftBTN(Button draftBTN) {
        this.draftBTN = draftBTN;
    }

    public TableView<BlackList> getBlackList() {
        return blackList;
    }

    public void setBlackList(TableView<BlackList> blackList) {
        this.blackList = blackList;
    }

    public Button getCreateBlack() {
        return createBlack;
    }

    public void setCreateBlack(Button createBlack) {
        this.createBlack = createBlack;
    }

    public Button getDeleteBlack() {
        return deleteBlack;
    }

    public void setDeleteBlack(Button deleteBlack) {
        this.deleteBlack = deleteBlack;
    }

    public TableView<Email> getSpam() {
        return spam;
    }

    public void setSpam(TableView<Email> spam) {
        this.spam = spam;
    }

    public Button getDeleteSpamBTN() {
        return deleteSpamBTN;
    }

    public void setDeleteSpamBTN(Button deleteSpamBTN) {
        this.deleteSpamBTN = deleteSpamBTN;
    }

    public TableView<Tag> getTag() {
        return tag;
    }

    public void setTag(TableView<Tag> tag) {
        this.tag = tag;
    }

    public Button getCreateTagBTN() {
        return createTagBTN;
    }

    public void setCreateTagBTN(Button createTagBTN) {
        this.createTagBTN = createTagBTN;
    }

    public Button getEditTagBTN() {
        return editTagBTN;
    }

    public void setEditTagBTN(Button editTagBTN) {
        this.editTagBTN = editTagBTN;
    }

    public Button getDeleteTagBTN() {
        return deleteTagBTN;
    }

    public void setDeleteTagBTN(Button deleteTagBTN) {
        this.deleteTagBTN = deleteTagBTN;
    }

    public Button getShowEmailBTN() {
        return showEmailBTN;
    }

    public void setShowEmailBTN(Button showEmailBTN) {
        this.showEmailBTN = showEmailBTN;
    }

    public Button getAddTag() {
        return addTag;
    }

    public void setAddTag(Button addTag) {
        this.addTag = addTag;
    }

    public Button getInboxDeleteTagBTN() {
        return inboxDeleteTagBTN;
    }

    public void setInboxDeleteTagBTN(Button inboxDeleteTagBTN) {
        this.inboxDeleteTagBTN = inboxDeleteTagBTN;
    }

    public Button getUndoBTN() {
        return undoBTN;
    }

    public void setUndoBTN(Button undoBTN) {
        this.undoBTN = undoBTN;
    }

    public Button getShowBTN() {
        return showBTN;
    }

    public void setShowBTN(Button showBTN) {
        this.showBTN = showBTN;
    }

    public Button getShowEmailSentBTN() {
        return showEmailSentBTN;
    }

    public void setShowEmailSentBTN(Button showEmailSentBTN) {
        this.showEmailSentBTN = showEmailSentBTN;
    }

    public Button getShowEmailTrashBTN() {
        return showEmailTrashBTN;
    }

    public void setShowEmailTrashBTN(Button showEmailTrashBTN) {
        this.showEmailTrashBTN = showEmailTrashBTN;
    }

    public Button getShowEmailDraftBTN() {
        return showEmailDraftBTN;
    }

    public void setShowEmailDraftBTN(Button showEmailDraftBTN) {
        this.showEmailDraftBTN = showEmailDraftBTN;
    }

    public Button getShowEmailSpamBTN() {
        return showEmailSpamBTN;
    }

    public void setShowEmailSpamBTN(Button showEmailSpamBTN) {
        this.showEmailSpamBTN = showEmailSpamBTN;
    }

    public Button getLogoutBTN() {
        return logoutBTN;
    }

    public void setLogoutBTN(Button logoutBTN) {
        this.logoutBTN = logoutBTN;
    }
}