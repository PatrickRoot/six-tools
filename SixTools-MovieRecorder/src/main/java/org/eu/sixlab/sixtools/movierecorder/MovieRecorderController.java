/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/21 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.movierecorder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.common.beans.MovieRecord;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.movierecorder.dao.MovieRecorderDao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/21
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class MovieRecorderController implements Initializable{
    @FXML
    public TableView tableView;
    @FXML
    public TableColumn tcDate;
    @FXML
    public TableColumn tcRemark;
    @FXML
    public TableColumn tcDirector;
    @FXML
    public TableColumn tcCountry;
    @FXML
    public TableColumn tcYear;
    @FXML
    public TableColumn tcName;
    @FXML
    public TableColumn tcNo;
    @FXML
    private TextField toolbarKeyword;
    @FXML
    private Label toolbarTips;

    public static final ObservableList<MovieRecord> data = FXCollections.observableArrayList();
    private String lastKeyword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
        loadMovies();
    }

    private void initTableView(){
        tableView.setEditable(true);

        tcNo.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("movieName"));
        tcCountry.setCellValueFactory(new PropertyValueFactory("country"));
        tcYear.setCellValueFactory(new PropertyValueFactory("produceYear"));
        tcDirector.setCellValueFactory(new PropertyValueFactory("director"));
        tcDate.setCellValueFactory(new PropertyValueFactory("viewDate"));
        tcRemark.setCellValueFactory(new PropertyValueFactory("remark"));

        tcName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcName.setOnEditCommit(handleCellEdit());

        tcCountry.setCellFactory(TextFieldTableCell.forTableColumn());
        tcCountry.setOnEditCommit(handleCellEdit());

        tcYear.setCellFactory(TextFieldTableCell.forTableColumn());
        tcYear.setOnEditCommit(handleCellEdit());

        tcDirector.setCellFactory(TextFieldTableCell.forTableColumn());
        tcDirector.setOnEditCommit(handleCellEdit());

        tcRemark.setCellFactory(TextFieldTableCell.forTableColumn());
        tcRemark.setOnEditCommit(handleCellEdit());

        tcDate.setCellFactory(TextFieldTableCell.forTableColumn());
        tcDate.setOnEditCommit(handleCellEdit());

        tableView.setItems(data);
    }

    public void close(ActionEvent event) {
        tableView.getScene().getWindow().hide();
    }

    public void addMovie(ActionEvent event) throws Exception {
        Stage stage = new Stage();

        Parent parent = FXMLLoader.load(getClass().getResource("addMovie.fxml"));

        Scene scene = new Scene(parent, 400, 280);

        stage.setScene(scene);
        stage.setTitle("Add Recorder");
        stage.show();
    }

    public void searchByKeyword(ActionEvent event) {
        String keyword = toolbarKeyword.getText();
        lastKeyword = keyword;
        loadMovies();
    }

    public void searchByBaidu(ActionEvent event) {
        searchByNet(SixToolsConstants.BAIDU_SEARCH_STRING);
    }

    public void searchByDouban(ActionEvent event) {
        searchByNet(SixToolsConstants.DOUBAN_SEARCH_STRING);
    }

    private void searchByNet(String url) {
        String text = ((MovieRecord)tableView.getSelectionModel().getSelectedItem()).getMovieName();

        String path = url + text;
        try {
            Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void loadMovies() {
        lastKeyword = (null==lastKeyword?"":lastKeyword);
        List<MovieRecord> movieRecordList;

        if(null==lastKeyword || "".equals(lastKeyword)){
            movieRecordList = MovieRecorderDao.getAllMovies();
        }else{
            movieRecordList = MovieRecorderDao.getMoviesByKeyword(lastKeyword);
        }

        data.clear();
        data.addAll(movieRecordList);
        toolbarTips.setText("共" + data.size() + "有部电影。");
    }

    private EventHandler<TableColumn.CellEditEvent> handleCellEdit() {
        return new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String fieldName = ((PropertyValueFactory) event.getTableColumn().getCellValueFactory()).getProperty();
                Field field = null;
                try {
                    field = MovieRecord.class.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if(null!=field){
                    MovieRecord movieRecord = new MovieRecord();
                    field.setAccessible(true);
                    try {
                        field.set(movieRecord, event.getNewValue());
                        movieRecord.setId(((MovieRecord) event.getRowValue()).getId());
                        MovieRecorderDao.update(movieRecord);
                        loadMovies();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void about(ActionEvent event) {
        System.out.println("this is about");
    }

}