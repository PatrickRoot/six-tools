/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seisplan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:31
 */
public class PlanController implements Initializable {

    private PlanService service = new PlanService(PlanController.this);

    @FXML
    public TabPane tabPane;
    @FXML
    public ComboBox yearStatusCombo;
    @FXML
    public TextField yearYearField;
    @FXML
    public DatePicker yearDatePicker;
    @FXML
    public TableView yearTable;
    @FXML
    public TableColumn yearIdColumn;
    @FXML
    public TableColumn yearNameColumn;
    @FXML
    public TableColumn yearTimeColumn;
    @FXML
    public TableColumn yearPerColumn;
    @FXML
    public TableColumn yearStatusColumn;
    @FXML
    public TableColumn yearParentColumn;
    @FXML
    public TableView seasonTable;
    @FXML
    public TableColumn seasonParentColumn;
    @FXML
    public TableColumn seasonStatusColumn;
    @FXML
    public TableColumn seasonPerColumn;
    @FXML
    public TableColumn seasonTimeColumn;
    @FXML
    public TableColumn seasonNameColumn;
    @FXML
    public TableColumn seasonIdColumn;
    @FXML
    public ComboBox seasonStatusCombo;
    @FXML
    public TextField seasonYearField;
    @FXML
    public TextField seasonSeasonField;
    @FXML
    public DatePicker seasonDatePicker;
    @FXML
    public TableView monthTable;
    @FXML
    public TableColumn monthIdColumn;
    @FXML
    public TableColumn monthParentColumn;
    @FXML
    public TableColumn monthStatusColumn;
    @FXML
    public TableColumn monthPerColumn;
    @FXML
    public TableColumn monthTimeColumn;
    @FXML
    public TableColumn monthNameColumn;
    @FXML
    public ComboBox monthStatusCombo;
    @FXML
    public TextField monthYearField;
    @FXML
    public TextField monthSeasonField;
    @FXML
    public TextField monthMonthField;
    @FXML
    public DatePicker monthDatePicker;
    @FXML
    public TableView weekTable;
    @FXML
    public TableColumn weekIdColumn;
    @FXML
    public TableColumn weekNameColumn;
    @FXML
    public TableColumn weekTimeColumn;
    @FXML
    public TableColumn weekPerColumn;
    @FXML
    public TableColumn weekStatusColumn;
    @FXML
    public TableColumn weekParentColumn;
    @FXML
    public ComboBox weekStatusCombo;
    @FXML
    public TextField weekYearField;
    @FXML
    public TextField weekSeasonField;
    @FXML
    public TextField weekMonthField;
    @FXML
    public TextField weekWeekField;
    @FXML
    public DatePicker weekDatePicker;
    @FXML
    public TextField nameField;
    @FXML
    public TextField timeField;
    @FXML
    public ComboBox typeCombo;
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextArea contentArea;
    @FXML
    public Tab yearTab;
    @FXML
    public Tab seasonTab;
    @FXML
    public Tab monthTab;
    @FXML
    public Tab weekTab;
    @FXML
    public Tab taskTab;
    @FXML
    public Label tipLabel;
    @FXML
    public Label taskTitleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service.initCombo();
        service.initField();
        service.initTable();
        service.initTab();
    }

    public void searchYear() {
        service.searchYear();
    }

    public void searchSeason() {
        service.searchSeason();
    }

    public void searchMonth() {
        service.searchMonth();
    }

    public void searchWeek() {
        service.searchWeek();
    }

    public void okPlan() {
        service.okPlan();
    }

    public void resetPlan() {
        service.initTaskTab();
    }

    public void typeChange(ActionEvent event) {
        service.typeChange();
    }
}
