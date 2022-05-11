package com.company.view;

import com.company.model.Tag;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ShowTagPage extends BorderPane {
    private TableView<Tag> tag;
    private Button deleteBTN;


    public ShowTagPage(){
        setTag(new TableView<>());
        setDeleteBTN(new Button("Delete Tag from Email"));

        TableColumn<Tag , String> tagIdCol = new TableColumn<>("Tag Id");
        TableColumn<Tag , String> tagNameCol = new TableColumn<>("Tag Name");

        tagIdCol.setCellValueFactory(new PropertyValueFactory<>("tagId"));
        tagNameCol.setCellValueFactory(new PropertyValueFactory<>("tagName"));

        tag.getColumns().addAll(tagIdCol , tagNameCol);
        tag.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox(deleteBTN);
        vBox.setAlignment(Pos.BOTTOM_CENTER);

        this.setCenter(tag);
        this.setRight(vBox);
    }




    public TableView<Tag> getTag() {
        return tag;
    }

    public void setTag(TableView<Tag> tag) {
        this.tag = tag;
    }

    public Button getDeleteBTN() {
        return deleteBTN;
    }

    public void setDeleteBTN(Button deleteBTN) {
        this.deleteBTN = deleteBTN;
    }
}
