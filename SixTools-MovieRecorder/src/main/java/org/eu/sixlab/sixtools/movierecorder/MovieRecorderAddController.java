/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/21 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.movierecorder;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.eu.sixlab.sixtools.common.beans.MovieRecord;
import org.eu.sixlab.sixtools.movierecorder.dao.MovieRecorderDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/21
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class MovieRecorderAddController {

    public TextField addName;
    public TextField addCountry;
    public TextField addYear;
    public TextField addDirector;
    public DatePicker addDate;
    public TextArea addRemark;

    public void saveAndClose(ActionEvent event) {

        addMovie();

        addName.getScene().getWindow().hide();
    }

    public void saveAndContinue(ActionEvent event) {

        addMovie();

    }

    public void addClose(ActionEvent event) {
        addName.getScene().getWindow().hide();
    }

    private void addMovie() {
        String name = addName.getText();
        String country = addCountry.getText();
        String year = addYear.getText();
        String director = addDirector.getText();
        String remark = addRemark.getText();

        LocalDate inputDate = addDate.getValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = inputDate.format(dtf);

        MovieRecord movieRecord = new MovieRecord(null,name,country,year,
                director,date,remark);

        MovieRecorderDao.insertMovie(movieRecord);
    }
}
