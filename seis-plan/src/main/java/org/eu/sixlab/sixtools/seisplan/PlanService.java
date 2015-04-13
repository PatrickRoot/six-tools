/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seisplan;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.eu.sixlab.sixtools.comun.bean.SeisPlan;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanStatus;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanType;
import org.eu.sixlab.sixtools.comun.dao.PlanDao;
import org.eu.sixlab.sixtools.comun.util.C;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:32
 */
public class PlanService {

    private PlanDao dao = new PlanDao();
    private PlanController con;

    private final ObservableList<SeisPlan> yearData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> seasonData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> monthData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> weekData = FXCollections.observableArrayList();
    private final ObservableList<PlanStatus> statusData = FXCollections.observableArrayList();
    private final ObservableList<PlanType> typeData = FXCollections.observableArrayList();

    private Integer parentId = null;
    private SeisPlan thePlan;

    public PlanService(PlanController planController) {
        this.con = planController;
    }

    public void initControlData() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<PlanType> typeList = PlanType.allTypes();
        typeData.clear();
        typeData.addAll(typeList);

        LocalDate localDate = LocalDate.now();

        String year = String.valueOf(localDate.getYear());
        Integer monthValue = localDate.getMonthValue();
        String month = String.valueOf(monthValue);
        String season = String.valueOf((monthValue + 2) / 3);
        String week = String.valueOf(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));

        con.yearYearField.setText(year.toString());
        con.seasonYearField.setText(year.toString());
        con.monthYearField.setText(year.toString());
        con.weekYearField.setText(year.toString());

        con.seasonSeasonField.setText(season.toString());
        con.monthSeasonField.setText(season.toString());
        con.weekSeasonField.setText(season.toString());

        con.monthMonthField.setText(month.toString());
        con.weekMonthField.setText(month.toString());

        con.weekWeekField.setText(week.toString());

        con.yearDatePicker.setValue(localDate);
        con.seasonDatePicker.setValue(localDate);
        con.monthDatePicker.setValue(localDate);
        con.weekDatePicker.setValue(localDate);
    }

    public void initControl() {

        ComboBox[] statusCombo = new ComboBox[]{
                con.yearStatusCombo, con.monthStatusCombo, con.seasonStatusCombo, con.weekStatusCombo
        };
        for (ComboBox comboBox : statusCombo) {
            comboBox.setItems(statusData);
            comboBox.getSelectionModel().selectFirst();

            comboBox.setCellFactory(p -> {
                return new ListCell<PlanStatus>() {
                    @Override
                    protected void updateItem(PlanStatus planStatus, boolean bln) {
                        super.updateItem(planStatus, bln);
                        if (planStatus != null) {
                            setText(planStatus.getStatusName());
                        } else {
                            setText(null);
                        }
                    }
                };
            });
        }

        con.typeCombo.setItems(typeData);
        con.typeCombo.getSelectionModel().selectLast();

        con.typeCombo.setCellFactory(p -> {
            return new ListCell<PlanType>() {
                @Override
                protected void updateItem(PlanType planType, boolean bln) {
                    super.updateItem(planType, bln);
                    if (planType != null) {
                        setText(planType.getTypeName());
                    } else {
                        setText(null);
                    }
                }
            };
        });




        con.yearYearField.textProperty().addListener(e -> {
            con.searchYear();
        });
        con.seasonYearField.textProperty().addListener(e -> {
            con.searchSeason();
        });
        con.monthYearField.textProperty().addListener(e -> {
            con.searchMonth();
        });
        con.weekYearField.textProperty().addListener(e -> {
            con.searchWeek();
        });

        con.seasonSeasonField.textProperty().addListener(e -> {
            con.searchSeason();
        });
        con.monthSeasonField.textProperty().addListener(e -> {
            con.searchMonth();
        });
        con.weekSeasonField.textProperty().addListener(e -> {
            con.searchWeek();
        });

        con.monthMonthField.textProperty().addListener(e -> {
            con.searchMonth();
        });
        con.weekMonthField.textProperty().addListener(e -> {
            con.searchWeek();
        });

        con.weekWeekField.textProperty().addListener(e -> {
            con.searchWeek();
        });

        con.yearDatePicker.setOnAction(e -> {
            LocalDate localDate = con.yearDatePicker.getValue();
            con.yearYearField.setText(String.valueOf(localDate.getYear()));
            con.searchYear();
        });
        con.seasonDatePicker.setOnAction(e -> {
            LocalDate localDate = con.seasonDatePicker.getValue();
            con.seasonYearField.setText(String.valueOf(localDate.getYear()));
            con.seasonSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            con.searchSeason();
        });
        con.monthDatePicker.setOnAction(e -> {
            LocalDate localDate = con.monthDatePicker.getValue();
            con.monthYearField.setText(String.valueOf(localDate.getYear()));
            con.monthSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            con.monthMonthField.setText(String.valueOf(localDate.getMonthValue()));
            con.searchMonth();
        });
        con.weekDatePicker.setOnAction(e -> {
            LocalDate localDate = con.weekDatePicker.getValue();
            con.weekYearField.setText(String.valueOf(localDate.getYear()));
            con.weekSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            con.weekMonthField.setText(String.valueOf(localDate.getMonthValue()));
            con.weekWeekField.setText(String.valueOf(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
            con.searchWeek();
        });
    }
    private void initTable() {
        initTableCellValueFactory();

        con.yearTable.setItems(yearData);
        con.seasonTable.setItems(seasonData);
        con.monthTable.setItems(monthData);
        con.weekTable.setItems(weekData);

        con.yearTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            clickTable(e, con.yearTable);
        });

        con.seasonTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            clickTable(e, con.seasonTable);
        });

        con.monthTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            clickTable(e, con.monthTable);
        });

        con.weekTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            clickTable(e, con.weekTable);
        });

        initTableCellFactory();
        searchData();
    }

    private void clickTable(MouseEvent e, TableView table) {
        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
            SeisPlan sixPlan = (SeisPlan) table.getSelectionModel().getSelectedItem();
            if (null != sixPlan && e.getTarget().getClass() != TableColumnHeader.class) {
                System.out.println(sixPlan.getId());
                modifyPlan(sixPlan);
            }
        } else if (e.getButton().equals(MouseButton.SECONDARY)) {
            showContextMenu(table, e);
        }
    }

    public void typeChange(ComboBox typeCombo, DatePicker datePicker) {
        Integer type = ((PlanType)(typeCombo.getSelectionModel().getSelectedItem())).getTypeValue();
        if( C.PLAN_TYPE_WEEK.equals(type)){
            datePicker.setValue(LocalDate.now().plusDays(5));
        }else{
            datePicker.setValue(LocalDate.now().plusMonths(1));
        }
    }


    public void processPlan(SeisPlan sixPlan, String newPer) {

//        if (C.PLAN_TYPE_YEAR.equals(sixPlan.getPlanType()) || C.PLAN_TYPE_WEEK.equals(sixPlan.getPlanType()) ) {
//            sixPlan.setPlanPer(newPer);
//            dao.updateById(sixPlan);
//        }else{
//            Integer parentId = sixPlan.getParentId();
//            Integer oldPer = sixPlan.getPlanPer();
//            SeisPlan parentPlan = dao.queryById(parentId);
//
//            double dividend = sixPlan.getPlanTime();
//            double divisor = parentPlan.getPlanTime();
//            double multiple = dividend / divisor;
//            Integer per = (int) (parentPlan.getPlanPer() + multiple * (newPer-oldPer));
//            processPlan(parentPlan, per);
//        }
    }
}
