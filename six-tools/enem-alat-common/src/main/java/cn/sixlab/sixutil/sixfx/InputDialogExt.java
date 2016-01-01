/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixutil.sixfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/9 10:37
 */
public class InputDialogExt extends Dialog implements DialogExt{

    private WebView webView = new WebView();
    private ScrollPane scrollPane = new ScrollPane();
    private TextField textField = new TextField();
    private Button cancelBtn = new Button(" 取消 ");

    public InputDialogExt(){
        this("", "");
    }

    public InputDialogExt(String title, String content){
        super(title, content);
    }

    public TextField getTextField(){
        return textField;
    }

    @Override
    public void setTipContent(String content){
        webView.getEngine().loadContent(content);
    }

    @Override
    public void show() {
        label.setStyle("-fx-font-size: 16px");
        label.setLayoutX(30);
        label.setLayoutY(20);
        textField.setLayoutX(60);
        textField.setLayoutY(50);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(85);
        cancelBtn.setLayoutX(60);
        cancelBtn.setLayoutY(270);
        confirmBtn.setLayoutX(165);
        confirmBtn.setLayoutY(270);

        confirmBtn.setOnAction(e->{
            value.handle(e);
            stage.hide();
        });

        cancelBtn.setOnAction(e->{stage.hide();});

        scrollPane.setPrefWidth(240);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(180);
        scrollPane.setContent(webView);

        pane.getChildren().addAll(label, textField, scrollPane, cancelBtn, confirmBtn);
        Scene scene = new Scene(pane, 260, 300);
        stage.setScene(scene);
        super.show();
    }
}
