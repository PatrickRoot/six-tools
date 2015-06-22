/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixfx;

import javafx.scene.Scene;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/9 10:07
 */
public class AlertDialog extends Dialog{

    public AlertDialog(){
        this("", "");
    }

    public AlertDialog(String title, String content){
        super(title, content);
    }

    public void show() {
        label.setStyle("-fx-font-size: 16px");
        label.setLayoutX(30);
        label.setLayoutY(30);
        confirmBtn.setLayoutX(165);
        confirmBtn.setLayoutY(105);

        confirmBtn.setOnAction(e->{
            value.handle(e);
            stage.hide();
        });

        pane.getChildren().addAll(label, confirmBtn);
        Scene scene = new Scene(pane, 260, 155);
        stage.setScene(scene);
        super.show();
    }
}
