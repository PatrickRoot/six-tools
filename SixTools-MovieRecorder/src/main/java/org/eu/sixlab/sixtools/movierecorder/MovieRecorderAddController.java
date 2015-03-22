/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/21 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.movierecorder;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.eu.sixlab.sixtools.common.beans.MovieRecord;
import org.eu.sixlab.sixtools.movierecorder.dao.MovieRecorderDao;
import org.eu.sixlab.sixutil.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/21
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class MovieRecorderAddController {

    private TextField addName;
    private TextField addCountry;
    private TextField addYear;
    private TextField addDirector;
    private DatePicker addDate;
    private TextArea addRemark;
    private Label tipsLabel;

    private boolean canClose = false;

    public void saveAndClose(ActionEvent event) {
        saveAndContinue(event);
        if(canClose){
            addClose(event);
        }
    }

    public void saveAndContinue(ActionEvent event) {
        canClose = false;
        tipsLabel.setText("");

        String name = addName.getText();
        if(StringUtil.isEmpty(name)){
            tipsLabel.setText("电影名字为空，请重新填写！");
            tipsLabel.setTextFill(Color.RED);
            addName.requestFocus();
            return ;
        }

        String year = addYear.getText();
        if(StringUtil.isNotEmpty(year)){
            if(year.length()!=4 || StringUtil.isNotNumber(year)){
                tipsLabel.setText("年份填写不正确，四位数字");
                tipsLabel.setTextFill(Color.RED);
                addYear.requestFocus();
                return;
            }
        }

        String country = addCountry.getText();
        String director = addDirector.getText();
        String remark = addRemark.getText();

        LocalDate inputDate = addDate.getValue();
        if( null == inputDate ){
            inputDate = LocalDate.now();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = inputDate.format(dtf);

        MovieRecord movieRecord = new MovieRecord(null,name,country,year,
                director,date,remark);

        MovieRecorderDao.insertMovie(movieRecord);
        MovieRecorderController.data.clear();
        MovieRecorderController.data.addAll(MovieRecorderDao.getMoviesByMovieName(name));
        canClose = true;
    }

    public void addClose(ActionEvent event) {
        addName.getScene().getWindow().hide();
    }
}
