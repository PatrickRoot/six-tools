/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.pelicula;

import cn.sixlab.sixtools.comun.bean.SeisPelicula;
import cn.sixlab.sixtools.comun.dao.MovieDao;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Logger logger = LoggerFactory.getLogger(PeliculaService.class);

    private MovieDao dao = new MovieDao();
    public ObservableList<SeisPelicula> data = FXCollections.observableArrayList();
    private PeliculaController ctrl;
    private SeisPelicula thePelicula = null;

    public PeliculaService(PeliculaController ctrl){
        this.ctrl = ctrl;
    }

    public void initTable() {

        ctrl.tcNo.setPrefWidth(39);
        ctrl.tcYear.setPrefWidth(39);
        ctrl.tcDate.setPrefWidth(67);
        ctrl.tcCountry.setPrefWidth(100);
        ctrl.tcDirector.setPrefWidth(100);
        ctrl.tcName.setPrefWidth(140);
        ctrl.tcRemark.setPrefWidth(295);

        ctrl.tcNo.setCellValueFactory(new PropertyValueFactory("id"));
        ctrl.tcName.setCellValueFactory(new PropertyValueFactory("movieName"));
        ctrl.tcCountry.setCellValueFactory(new PropertyValueFactory("country"));
        ctrl.tcYear.setCellValueFactory(new PropertyValueFactory("produceYear"));
        ctrl.tcDirector.setCellValueFactory(new PropertyValueFactory("director"));
        ctrl.tcDate.setCellValueFactory(new PropertyValueFactory("viewDate"));
        ctrl.tcRemark.setCellValueFactory(new PropertyValueFactory("remark"));

        ctrl.tableView.setItems(data);

        ctrl.tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                //TODO (click count == 2)===( double click)?
                SeisPelicula clickedPelicula = (SeisPelicula) ctrl.tableView.getSelectionModel().getSelectedItem();
                if (null != clickedPelicula && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPelicula(clickedPelicula);
                }
            }
        });
    }

    private void modifyPelicula(SeisPelicula clickedPelicula) {
        ctrl.titleLabel.setText("修改电影记录，ID : " + clickedPelicula.getId());
        thePelicula = clickedPelicula;
        ctrl.addName.setText(clickedPelicula.getMovieName());
        ctrl.addCountry.setText(clickedPelicula.getCountry());
        ctrl.addDirector.setText(clickedPelicula.getDirector());
        ctrl.addRemark.setText(clickedPelicula.getRemark());
        if(S.isNotEmpty(clickedPelicula.getProduceYear())){
            ctrl.addYear.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getProduceYear()),1,1));
        }
        ctrl.addDate.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getViewDate().substring(0, 4))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(4, 6))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(6, 8))));
        ctrl.tabPane.getSelectionModel().select(ctrl.operaTab);
    }

    public void reset() {
        ctrl.titleLabel.setText("添加电影记录");
        thePelicula = null;
        ctrl.addName.setText("");
        ctrl.addCountry.setText("");
        ctrl.addDirector.setText("");
        ctrl.addRemark.setText("");
        ctrl.addYear.setValue(null);
        ctrl.addDate.setValue(LocalDate.now());
    }

    public void loadMovies(String text) {
        List<SeisPelicula> movieList;

        if(null!=text){
            movieList = dao.getMoviesByMovieName(text);
            ctrl.toolbarKeyword.setText(text);
            ctrl.tipsLabel.setText(ctrl.tipsLabel.getText()+".共有 " + movieList.size() + " 部相似名字电影。");
        }else{
            String keyword = ctrl.toolbarKeyword.getText();
            if ("".equals(keyword)) {
                movieList = dao.getAllMovies();
            } else {
                movieList = dao.getMoviesByKeyword(keyword);
            }
            ctrl.tipsLabel.setText("共有 " + movieList.size() + " 部电影。");

            data.clear();
            data.addAll(movieList);
        }
    }

    public void searchByNet(String url) {
        String text = ((SeisPelicula) ctrl.tableView.getSelectionModel().getSelectedItem()).getMovieName();

        String path = url + text;
        try {
            Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            logger.error(e1.getMessage(),e1);
        }
    }

    public void confirm() {
        String text = ctrl.addName.getText();
        if(S.isEmpty(text)){
            ctrl.tipsLabel.setText("必需输入电影名称");
            ctrl.addName.requestFocus();
            return;
        }

        String director = ctrl.addDirector.getText();
        String country = ctrl.addCountry.getText();
        String remark = ctrl.addRemark.getText();

        LocalDate addYearDate = ctrl.addYear.getValue();
        String year = "";
        if(null!=addYearDate){
            year = String.valueOf(addYearDate.getYear());
        }
        LocalDate addDateDate = ctrl.addDate.getValue();
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
            ctrl.tipsLabel.setText("更新 " + text + "成功。");
        }else{
            SeisPelicula seisPelicula = new SeisPelicula();
            seisPelicula.setMovieName(text);
            seisPelicula.setViewDate(vewDate);
            seisPelicula.setCountry(country);
            seisPelicula.setDirector(director);
            seisPelicula.setRemark(remark);
            seisPelicula.setProduceYear(year);
            dao.insert(seisPelicula);
            ctrl.tipsLabel.setText("添加 " + text + "成功。");
        }

        loadMovies(text);
    }
}
