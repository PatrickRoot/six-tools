/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixutil.sixfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/9 8:30
 */
public class Dialog {

    protected Stage stage = new Stage();
    protected Pane pane = new Pane();
    protected Label label = new Label();
    protected Button confirmBtn = new Button(" 确定 ");
    protected EventHandler<ActionEvent> value;

    public Dialog(){
        this("", "");
    }

    public Dialog(String title, String content){
        super();
        this.stage.setTitle(title);
        this.label.setText(content);
        value = e->{};
    }

    public Dialog(String title, String content, EventHandler<ActionEvent> value){
        super();
        this.stage.setTitle(title);
        this.label.setText(content);
        this.value = value;
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public void setContent(String content){
        label.setText(content);
    }

    public void setOnAction(EventHandler<ActionEvent> value) {
        this.value = value;
    }

    public void show() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}