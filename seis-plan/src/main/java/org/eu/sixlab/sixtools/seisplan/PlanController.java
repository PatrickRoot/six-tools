/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seisplan;

import javafx.event.ActionEvent;
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

    public TabPane tabPane;
    public ComboBox yearStatusCombo;
    public TextField yearYearField;
    public DatePicker yearDatePicker;
    public TableView yearTable;
    public TableColumn yearIdColumn;
    public TableColumn yearNameColumn;
    public TableColumn yearTimeColumn;
    public TableColumn yearPerColumn;
    public TableColumn yearStatusColumn;
    public TableColumn yearParentColumn;
    public TableView seasonTable;
    public TableColumn seasonParentColumn;
    public TableColumn seasonStatusColumn;
    public TableColumn seasonPerColumn;
    public TableColumn seasonTimeColumn;
    public TableColumn seasonNameColumn;
    public TableColumn seasonIdColumn;
    public ComboBox seasonStatusCombo;
    public TextField seasonYearField;
    public TextField seasonSeasonField;
    public DatePicker seasonDatePicker;
    public TableView monthTable;
    public TableColumn monthIdColumn;
    public TableColumn monthParentColumn;
    public TableColumn monthStatusColumn;
    public TableColumn monthPerColumn;
    public TableColumn monthTimeColumn;
    public TableColumn monthNameColumn;
    public ComboBox monthStatusCombo;
    public TextField monthYearField;
    public TextField monthSeasonField;
    public TextField monthMonthField;
    public DatePicker monthDatePicker;
    public TableView weekTable;
    public TableColumn weekIdColumn;
    public TableColumn weekNameColumn;
    public TableColumn weekTimeColumn;
    public TableColumn weekPerColumn;
    public TableColumn weekStatusColumn;
    public TableColumn weekParentColumn;
    public ComboBox weekStatusCombo;
    public TextField weekYearField;
    public TextField weekSeasonField;
    public TextField weekMonthField;
    public TextField weekWeekField;
    public DatePicker weekDatePicker;
    public TextField nameField;
    public TextField timeField;
    public ComboBox typeCombo;
    public DatePicker datePicker;
    public TextArea contentArea;
    public Tab yearTab;
    public Tab seasonTab;
    public Tab monthTab;
    public Tab weekTab;
    public Tab taskTab;
    public Label tipLabel;
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
