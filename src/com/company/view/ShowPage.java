package com.company.view;


import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShowPage extends HBox {
    private Text fromTXT;
    private Text toTXT;
    private Text subjectTXT;
    private Text textTXT;
    private Text dateTXT;
    private Text from;
    private Text to;
    private Text subject;
    private Text text;
    private Text date;



    public ShowPage(){
        setFromTXT(new Text("From : "));
        setToTXT(new Text("To : "));
        setSubjectTXT(new Text("Subject : "));
        setTextTXT(new Text("Text : "));
        setDateTXT(new Text("Date : "));
        setFrom(new Text());
        setTo(new Text());
        setSubject(new Text());
        setText(new Text());
        setDate(new Text());

        VBox vBox = new VBox(fromTXT , toTXT , subjectTXT , textTXT , dateTXT);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(8);

        VBox vBox1 = new VBox(from , to , subject , text , date);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(8);

        this.getChildren().addAll(vBox , vBox1);
        this.setAlignment(Pos.CENTER);
    }



    public Text getFromTXT() {
        return fromTXT;
    }

    public void setFromTXT(Text fromTXT) {
        this.fromTXT = fromTXT;
    }

    public Text getToTXT() {
        return toTXT;
    }

    public void setToTXT(Text toTXT) {
        this.toTXT = toTXT;
    }

    public Text getSubjectTXT() {
        return subjectTXT;
    }

    public void setSubjectTXT(Text subjectTXT) {
        this.subjectTXT = subjectTXT;
    }

    public Text getTextTXT() {
        return textTXT;
    }

    public void setTextTXT(Text textTXT) {
        this.textTXT = textTXT;
    }

    public Text getDateTXT() {
        return dateTXT;
    }

    public void setDateTXT(Text dateTXT) {
        this.dateTXT = dateTXT;
    }

    public Text getFrom() {
        return from;
    }

    public void setFrom(Text from) {
        this.from = from;
    }

    public Text getTo() {
        return to;
    }

    public void setTo(Text to) {
        this.to = to;
    }

    public Text getSubject() {
        return subject;
    }

    public void setSubject(Text subject) {
        this.subject = subject;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text getDate() {
        return date;
    }

    public void setDate(Text date) {
        this.date = date;
    }
}
