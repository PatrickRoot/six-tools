/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixfx;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebView;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/16 10:17
 */
public class AlertDialogExt extends Dialog implements DialogExt{

    private WebView webView = new WebView();
    private ScrollPane scrollPane = new ScrollPane();

    public AlertDialogExt(){
        this("", "");
    }

    public AlertDialogExt(String title, String content){
        super(title, content);
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
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(50);
        confirmBtn.setLayoutX(105);
        confirmBtn.setLayoutY(260);

        confirmBtn.setOnAction(e->{
            value.handle(e);
            stage.hide();
        });

        scrollPane.setPrefWidth(240);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(200);
        scrollPane.setContent(webView);

        pane.getChildren().addAll(label, scrollPane, confirmBtn);
        Scene scene = new Scene(pane, 260, 290);
        stage.setScene(scene);
        super.show();
    }
}
