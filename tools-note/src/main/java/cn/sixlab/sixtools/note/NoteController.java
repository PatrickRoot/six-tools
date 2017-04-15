/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.note;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsNote;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.S;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/25 16:05
 */
public class NoteController extends BaseController implements Initializable{
    private static Logger logger = LoggerFactory.getLogger(NoteController.class);
    public static NoteController self;
    public VBox vBox;
    public Label tipLabel;
    private Dao dao = D.dao;

    public TextArea textArea;
    public TextField textField;

    private ToolsNote currBloc;
    private Pane currPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
        loadList();
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            vBox.getChildren().clear();
        }finally {
            super.finalize();
        }
    }

    public void updateBloc() {
        String text = textArea.getText();
        currBloc.setText(text);
        Label label = (Label) currPane.getChildren().get(0);
        label.setText(trimText(text));
        dao.update(currBloc);
        tipLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-yy-MM HH:mm:ss SSS")));
    }

    private void loadList(){
        loadList(null);
    }

    private void loadList(List<ToolsNote> blocList) {
        if(null==blocList){
            blocList = dao.query(ToolsNote.class, Cnd.orderBy().desc("date"));
        }
        vBox.getChildren().clear();
        boolean isFirst = true;
        for (ToolsNote toolsNote : blocList) {
            BorderPane pane = new BorderPane();
            pane.setStyle("-fx-border-color: #FFb;");
            pane.setPrefHeight(30);
            Button button = new Button("-");
            button.setId("btn_" + toolsNote.getId());
            button.setOnAction(event -> {
                delete(event);
            });

            Label label = new Label(trimText(toolsNote.getText()));
            Pane labelPane = new Pane(label);
            labelPane.setId("lel_"+ toolsNote.getId());
            labelPane.setOnMouseClicked(event -> {
                clickLabel(event);
            });
            if (isFirst) {
                currBloc = blocList.get(0);
                textArea.setText(currBloc.getText());
                currPane = labelPane;
                labelPane.setStyle("-fx-background-color: #de0;");
                isFirst = false;
            }else{
                labelPane.setStyle("-fx-background-color: #deb;");
            }

            pane.setLeft(button);
            pane.setCenter(labelPane);

            pane.setMargin(button, new Insets(0, 5, 0, 5));
            pane.setAlignment(button, Pos.CENTER);
            pane.setAlignment(labelPane, Pos.CENTER_LEFT);
            vBox.getChildren().add(pane);
        }
    }

    private String trimText(String str) {
        String result = "";
        int length = 20;
        if(S.isNotEmpty(str)){
            String[] texts = str.split("\n");
            String text = texts[0];

            int t = 0;
            char[] tempChar = text.toCharArray();
            for (int i = 0; i < tempChar.length && t < length; i++) {
                if ((int) tempChar[i] >= 0x4E00 && (int) tempChar[i] <= 0x9FA5) {//是否汉字
                    result += tempChar[i];
                    t += 2;
                } else {
                    result += tempChar[i];
                    t++;
                }
            }
            return (result + "...");
        }
        return result;
    }

    public void delete(ActionEvent event) {
        String idStr = ((Button) event.getSource()).getId();
        Integer id = Integer.valueOf(idStr.substring(4));
        dao.delete(ToolsNote.class, id);
        loadList();
    }

    //public void inputChange(Event event) {
    //    System.out.println("-----------------------");
    //    String text = textArea.getText();
    //    if(text.length()<23){
    //        System.out.println("+++++                           +++++++++++++++++++");
    //        Label label = (Label) currPane.getChildren().get(0);
    //        label.setText(text);
    //    }
    //}

    public void changeSearch(Event event) {
        String text = textField.getText();
        loadList(dao.query(ToolsNote.class, Cnd.where(" text ", "like", "%" + text + "%").desc("date")));
    }

    public void addBloc(ActionEvent event) {
        ToolsNote toolsNote = new ToolsNote();
        toolsNote.setDate(LocalDateTime.now().toString());
        toolsNote.setText("");
        dao.insert(toolsNote);
        loadList();
    }

    public void clickLabel(Event event) {
        Pane pane = (Pane)event.getSource();
        Long id = Long.valueOf(pane.getId().substring(4));

        currPane.setStyle("-fx-background-color: #deb;");
        pane.setStyle("-fx-background-color: #de0;");

        currBloc = dao.fetch(ToolsNote.class,id);
        currPane = pane;
        textArea.setText(currBloc.getText());
    }
}
