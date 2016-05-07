/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.movie;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsMovie;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * MovieRecorder对应的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/3/21 19:46
 */
public class MovieRecorderController extends BaseController implements Initializable{
    private static Logger logger = LoggerFactory.getLogger(MovieRecorderController.class);
    public static MovieRecorderController self;
    private Dao dao = D.dao;

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

    public ObservableList<ToolsMovie> data = FXCollections.observableArrayList();
    private ToolsMovie thePelicula = null;
    private EventHandler<MouseEvent> eventHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventHandler = e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                //TODO (click count == 2)===( double click)?
                ToolsMovie clickedPelicula = (ToolsMovie) tableView.getSelectionModel().getSelectedItem();
                if (null != clickedPelicula && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPelicula(clickedPelicula);
                }
            }
        };
        self = this;
        this.reset();
        this.initTable();
        this.loadMovies(null);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            tableView.removeEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
            data.clear();
        } finally {
            super.finalize();
        }
    }

    public void reset() {
        titleLabel.setText("添加电影记录");
        thePelicula = null;
        addName.setText("");
        addCountry.setText("");
        addDirector.setText("");
        addRemark.setText("");
        addYear.setValue(null);
        addDate.setValue(LocalDate.now());
    }

    public void initTable() {

        tcNo.setPrefWidth(39);
        tcYear.setPrefWidth(39);
        tcDate.setPrefWidth(67);
        tcCountry.setPrefWidth(100);
        tcDirector.setPrefWidth(100);
        tcName.setPrefWidth(140);
        tcRemark.setPrefWidth(295);

        tcNo.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("movieName"));
        tcCountry.setCellValueFactory(new PropertyValueFactory("country"));
        tcYear.setCellValueFactory(new PropertyValueFactory("produceYear"));
        tcDirector.setCellValueFactory(new PropertyValueFactory("director"));
        tcDate.setCellValueFactory(new PropertyValueFactory("viewDate"));
        tcRemark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableView.setItems(data);

        tableView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void loadMovies(String text) {
        List<ToolsMovie> movieList;

        if (null != text) {
            Cnd cnd = Cnd.where("movieName", "like", "%" + text + "%").or("remark","like", "%" + text + "%");
            movieList = dao.query(ToolsMovie.class, cnd.desc("id"));
            toolbarKeyword.setText(text);
            tipsLabel.setText(tipsLabel.getText() + ".共有 " + movieList.size() + " 部相似名字电影。");
        } else {
            String keyword = toolbarKeyword.getText();
            if ("".equals(keyword)) {
                movieList = dao.query(ToolsMovie.class, Cnd.orderBy().desc("id"));
            } else {
                Cnd cnd = Cnd.where("id","like", "%" + keyword + "%")
                        .or("movieName", "like", "%" + keyword + "%")
                        .or("country", "like", "%" + keyword + "%")
                        .or("produceYear", "like", "%" + keyword + "%")
                        .or("director", "like", "%" + keyword + "%")
                        .or("remark", "like", "%" + keyword + "%")
                        .or("viewDate", "like", "%" + keyword + "%");
                movieList = dao.query(ToolsMovie.class, cnd.desc("id"));
            }
            tipsLabel.setText("共有 " + movieList.size() + " 部电影。");

            data.clear();
            data.addAll(movieList);
        }
    }

    public void searchByNet(String url) {
        String text = ((ToolsMovie) tableView.getSelectionModel().getSelectedItem()).getMovieName();

        String path = url + text;
        try {
            Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND + path);
        } catch (IOException e1) {
            logger.error(e1.getMessage(), e1);
        }
    }

    public void confirm() {
        String text = addName.getText();
        if (S.isEmpty(text)) {
            tipsLabel.setText("必需输入电影名称");
            addName.requestFocus();
            return;
        }

        String director = addDirector.getText();
        String country = addCountry.getText();
        String remark = addRemark.getText();

        LocalDate addYearDate = addYear.getValue();
        String year = "";
        if (null != addYearDate) {
            year = String.valueOf(addYearDate.getYear());
        }
        LocalDate addDateDate = addDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String vewDate = addDateDate.format(formatter);
        if (null != thePelicula) {
            thePelicula.setMovieName(text);
            thePelicula.setViewDate(vewDate);
            thePelicula.setCountry(country);
            thePelicula.setDirector(director);
            thePelicula.setRemark(remark);
            thePelicula.setProduceYear(year);
            dao.update(thePelicula);
            tipsLabel.setText("更新 " + text + "成功。");
        } else {
            ToolsMovie toolsMovie = new ToolsMovie();
            toolsMovie.setMovieName(text);
            toolsMovie.setViewDate(vewDate);
            toolsMovie.setCountry(country);
            toolsMovie.setDirector(director);
            toolsMovie.setRemark(remark);
            toolsMovie.setProduceYear(year);
            dao.insert(toolsMovie);
            tipsLabel.setText("添加 " + text + "成功。");
        }

        loadMovies(text);
    }

    private void modifyPelicula(ToolsMovie clickedPelicula) {
        titleLabel.setText("修改电影记录，ID : " + clickedPelicula.getId());
        thePelicula = clickedPelicula;
        addName.setText(clickedPelicula.getMovieName());
        addCountry.setText(clickedPelicula.getCountry());
        addDirector.setText(clickedPelicula.getDirector());
        addRemark.setText(clickedPelicula.getRemark());
        if (S.isNotEmpty(clickedPelicula.getProduceYear())) {
            addYear.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getProduceYear()), 1, 1));
        }
        addDate.setValue(LocalDate.of(Integer.valueOf(clickedPelicula.getViewDate().substring(0, 4))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(4, 6))
                , Integer.valueOf(clickedPelicula.getViewDate().substring(6, 8))));
        tabPane.getSelectionModel().select(operaTab);
    }

    public void searchByKeyword(ActionEvent event) {
        loadMovies(null);
    }

    public void searchByBaidu(ActionEvent event) {
        searchByNet(C.BAIDU_SEARCH_STRING);
        System.out.println("--------------------");
        System.out.println(tcNo.getWidth());
        System.out.println(tcName.getWidth());
        System.out.println(tcYear.getWidth());
        System.out.println(tcCountry.getWidth());
        System.out.println(tcDirector.getWidth());
        System.out.println(tcRemark.getWidth());
        System.out.println(tcDate.getWidth());
        System.out.println("--------------------");
    }

    public void searchByDouban(ActionEvent event) {
        searchByNet(C.DOUBAN_SEARCH_STRING);
    }

    public void reset(ActionEvent event) {
        reset();
    }

    public void confirm(ActionEvent event) {
        confirm();
    }

    public void searchReset(ActionEvent event) {
        toolbarKeyword.setText("");
        loadMovies(null);
    }
}