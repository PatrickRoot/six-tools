/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.pelicula;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import cn.sixlab.sixtools.comun.bean.SeisPelicula;
import cn.sixlab.sixtools.comun.dao.MovieDao;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.StrUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:47
 */
public class PeliculaService {

    private MovieDao dao = new MovieDao();
    public ObservableList<SeisPelicula> data = FXCollections.observableArrayList();
    private PeliculaController controller;
    private SeisPelicula thePelicula = null;

    public PeliculaService(PeliculaController controller){
        this.controller = controller;
    }

    public void initTable() {

        controller.tcNo.setCellValueFactory(new PropertyValueFactory("id"));
        controller.tcName.setCellValueFactory(new PropertyValueFactory("movieName"));
        controller.tcCountry.setCellValueFactory(new PropertyValueFactory("country"));
        controller.tcYear.setCellValueFactory(new PropertyValueFactory("produceYear"));
        controller.tcDirector.setCellValueFactory(new PropertyValueFactory("director"));
        controller.tcDate.setCellValueFactory(new PropertyValueFactory("viewDate"));
        controller.tcRemark.setCellValueFactory(new PropertyValueFactory("remark"));

        controller.tableView.setItems(data);

        controller.tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                //TODO (click count == 2)===( double click)?
                SeisPelicula clickedPelicula = (SeisPelicula) controller.tableView.getSelectionModel().getSelectedItem();
                if (null != clickedPelicula && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPelicula(clickedPelicula);
                }
            }
        });
    }

    private void modifyPelicula(SeisPelicula clickedPelicula) {
        controller.titleLabel.setText("修改电影记录，ID : " + clickedPelicula.getId());
        thePelicula = clickedPelicula;
        controller.addName.setText(clickedPelicula.getMovieName());
        controller.addCountry.setText(clickedPelicula.getCountry());
        controller.addDirector.setText(clickedPelicula.getDirector());
        controller.addRemark.setText(clickedPelicula.getRemark());
        if(StrUtil.isNotEmpty(clickedPelicula.getProduceYear())){
            controller.addYear.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getProduceYear()),1,1));
        }
        controller.addDate.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getViewDate().substring(0, 4))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(4, 6))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(6, 8))));
        controller.tabPane.getSelectionModel().select(controller.operaTab);
    }

    public void reset() {
        controller.titleLabel.setText("添加电影记录");
        thePelicula = null;
        controller.addName.setText("");
        controller.addCountry.setText("");
        controller.addDirector.setText("");
        controller.addRemark.setText("");
        controller.addYear.setValue(null);
        controller.addDate.setValue(LocalDate.now());
    }

    public void loadMovies(String text) {
        List<SeisPelicula> movieList;

        if(null!=text){
            movieList = dao.getMoviesByMovieName(text);
            controller.toolbarKeyword.setText(text);
            controller.tipsLabel.setText(controller.tipsLabel.getText()+".共有 " + movieList.size() + " 部相似名字电影。");
        }else{
            String keyword = controller.toolbarKeyword.getText();
            if ("".equals(keyword)) {
                movieList = dao.getAllMovies();
            } else {
                movieList = dao.getMoviesByKeyword(keyword);
            }
            controller.tipsLabel.setText("共有 " + movieList.size() + " 部电影。");

            data.clear();
            data.addAll(movieList);
        }
    }

    public void searchByNet(String url) {
        String text = ((SeisPelicula)controller.tableView.getSelectionModel().getSelectedItem()).getMovieName();

        String path = url + text;
        try {
            Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void confirm() {
        String text = controller.addName.getText();
        if(StrUtil.isEmpty(text)){
            controller.tipsLabel.setText("必需输入电影名称");
            controller.addName.requestFocus();
            return;
        }

        String director = controller.addDirector.getText();
        String country = controller.addCountry.getText();
        String remark = controller.addRemark.getText();

        LocalDate addYearDate = controller.addYear.getValue();
        String year = "";
        if(null!=addYearDate){
            year = String.valueOf(addYearDate.getYear());
        }
        LocalDate addDateDate = controller.addDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String vewDate = addDateDate.format(formatter);
        if(null!=thePelicula){
            thePelicula.setMovieName(text);
            thePelicula.setViewDate(vewDate);
            thePelicula.setCountry(country);
            thePelicula.setDirector(director);
            thePelicula.setRemark(remark);
            thePelicula.setProduceYear(year);
            dao.update(thePelicula);
            controller.tipsLabel.setText("更新 " + text + "成功。");
        }else{
            SeisPelicula seisPelicula = new SeisPelicula();
            seisPelicula.setMovieName(text);
            seisPelicula.setViewDate(vewDate);
            seisPelicula.setCountry(country);
            seisPelicula.setDirector(director);
            seisPelicula.setRemark(remark);
            seisPelicula.setProduceYear(year);
            dao.insert(seisPelicula);
            controller.tipsLabel.setText("添加 " + text + "成功。");
        }

        loadMovies(text);
    }
}
