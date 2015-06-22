/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.plan;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:31
 */
public class PlanController implements Initializable {
    private Logger logger = LoggerFactory.getLogger(PlanController.class);
    private PlanService service = new PlanService(PlanController.this);

    public TabPane tabPane;
    public Tab yearTab;
    public Tab seasonTab;
    public Tab monthTab;
    public Tab weekTab;
    public Tab dayTab;
    public Tab taskTab;

    public ComboBox yearStatusCombo;

    public TableView yearTable;
    public TableColumn yearIdColumn;
    public TableColumn yearNameColumn;
    public TableColumn yearTimeColumn;
    public TableColumn yearDateColumn;
    public TableColumn yearStatusColumn;

    public ComboBox seasonStatusCombo;

    public TableView seasonTable;
    public TableColumn seasonIdColumn;
    public TableColumn seasonNameColumn;
    public TableColumn seasonTimeColumn;
    public TableColumn seasonDateColumn;
    public TableColumn seasonStatusColumn;

    public ComboBox monthStatusCombo;

    public TableView monthTable;
    public TableColumn monthIdColumn;
    public TableColumn monthNameColumn;
    public TableColumn monthDateColumn;
    public TableColumn monthTimeColumn;
    public TableColumn monthStatusColumn;

    public ComboBox dayStatusCombo;

    public TableView dayTable;
    public TableColumn dayIdColumn;
    public TableColumn dayNameColumn;
    public TableColumn dayTimeColumn;
    public TableColumn dayDateColumn;
    public TableColumn dayStatusColumn;

    public ComboBox weekStatusCombo;

    public TableView weekTable;
    public TableColumn weekIdColumn;
    public TableColumn weekNameColumn;
    public TableColumn weekTimeColumn;
    public TableColumn weekDateColumn;
    public TableColumn weekStatusColumn;

    public TextField nameField;
    public ComboBox typeCombo;
    public TextArea contentArea;
    public DatePicker datePicker;
    public TextField timeField;

    public Label tipLabel;
    public Label taskTitleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setShowWeekNumbers(true);
        service.initCombo();
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

    public void searchDay(ActionEvent event) {
        service.searchDay();
    }
}
