/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/9 10:04
 */
public class ConfirmDialog extends Dialog {

    private Button cancelBtn = new Button(" 取消 ");

    public ConfirmDialog(){
        this("", "");
    }

    public ConfirmDialog(String title, String content){
        super(title, content);
    }

    public void show() {
        label.setStyle("-fx-font-size: 16px");
        label.setLayoutX(30);
        label.setLayoutY(30);
        cancelBtn.setLayoutX(60);
        cancelBtn.setLayoutY(105);
        confirmBtn.setLayoutX(165);
        confirmBtn.setLayoutY(105);

        confirmBtn.setOnAction(e->{
            value.handle(e);
            stage.hide();
        });

        cancelBtn.setOnAction(e->{stage.hide();});

        pane.getChildren().addAll(label, cancelBtn, confirmBtn);
        Scene scene = new Scene(pane, 260, 155);
        stage.setScene(scene);
        super.show();
    }
}
