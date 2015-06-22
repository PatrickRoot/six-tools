/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixfx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/9 9:44
 */
public class InputDialog extends Dialog{

    private TextField textField = new TextField();
    private Button cancelBtn = new Button(" 取消 ");

    public InputDialog(){
        this("", "");
    }

    public InputDialog(String title, String content){
        super(title, content);
    }

    public TextField getTextField(){
        return textField;
    }

    public void show() {
        label.setStyle("-fx-font-size: 16px");
        label.setLayoutX(30);
        label.setLayoutY(30);
        textField.setLayoutX(70);
        textField.setLayoutY(80);
        cancelBtn.setLayoutX(60);
        cancelBtn.setLayoutY(135);
        confirmBtn.setLayoutX(165);
        confirmBtn.setLayoutY(135);

        confirmBtn.setOnAction(e->{
            value.handle(e);
            stage.hide();
        });

        cancelBtn.setOnAction(e->{stage.hide();});

        pane.getChildren().addAll(label, textField, cancelBtn, confirmBtn);
        Scene scene = new Scene(pane, 260, 185);
        stage.setScene(scene);
        super.show();
    }
}
