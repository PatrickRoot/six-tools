/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seispelicula;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.eu.sixlab.sixtools.comun.util.C;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MovieRecorder对应的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/3/21 19:46
 */
public class PeliculaController implements Initializable{
    private PeliculaService service ;

    public Label titleLabel;
    public TableColumn tcNo;
    public TableColumn tcName;
    public TableColumn tcYear;
    public TableColumn tcCountry;
    public TableColumn tcDirector;
    public TableColumn tcRemark;
    public TableColumn tcDate;
    public TableView tableView;
    public TextField toolbarKeyword;
    public TabPane tabPane;
    public Tab listTab;
    public Tab operaTab;
    public TextField addName;
    public TextField addCountry;
    public TextField addDirector;
    public DatePicker addDate;
    public TextArea addRemark;
    public DatePicker addYear;
    public Label tipsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new PeliculaService(PeliculaController.this);
        service.reset();
        service.initTable();
        service.loadMovies(null);
    }

    public void searchByKeyword(ActionEvent event) {
        service.loadMovies(null);
    }

    public void searchByBaidu(ActionEvent event) {
        service.searchByNet(C.BAIDU_SEARCH_STRING);
    }

    public void searchByDouban(ActionEvent event) {
        service.searchByNet(C.DOUBAN_SEARCH_STRING);
    }

    public void reset(ActionEvent event) {
        service.reset();
    }

    public void confirm(ActionEvent event) {
        service.confirm();
    }

    public void searchReset(ActionEvent event) {
        toolbarKeyword.setText("");
        service.loadMovies(null);
    }
}