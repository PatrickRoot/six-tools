/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.sixtomcat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import cn.sixlab.sixtools.comun.bean.SeisTomcat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/14 17:00
 */
public class TomcatController implements Initializable {
    public TextField xmlPathField;

    public Label tipLabel;
    public TableView tomcatTable;
    public TableColumn pathColumn;
    public TableColumn docColumn;

    private String xmlPath = "D:\\ebs\\tomcat\\apache-tomcat-7.0.56\\conf\\server.xml";
    private final ObservableList<SeisTomcat> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        xmlPathField.setText(xmlPath);
    }

    private void initTable() {
        pathColumn.setCellValueFactory(new PropertyValueFactory("path"));
        docColumn.setCellValueFactory(new PropertyValueFactory("docBase"));

        pathColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        docColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        pathColumn.setOnEditCommit(pathEdit());
        pathColumn.setOnEditCancel(pathEdit());
        docColumn.setOnEditCommit(docEdit());
        docColumn.setOnEditCancel(docEdit());

        tomcatTable.setItems(data);
        initData();
    }

    private EventHandler<TableColumn.CellEditEvent> docEdit() {
        return e -> {
            String path = ((SeisTomcat) e.getRowValue()).getPath();
            String docBase = (String) e.getNewValue();
            update(path, path, docBase);
        };
    }

    private EventHandler<TableColumn.CellEditEvent> pathEdit() {
        return e -> {
            String newPath = (String) e.getNewValue();
            String oldPath = (String) e.getOldValue();
            String docBase = ((SeisTomcat) e.getRowValue()).getDocBase();
            update(oldPath, newPath, docBase);
        };
    }

    private void update(String oldPath, String newPath, String newDocBase) {
        try {
            data.clear();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlPath));
            Element host = document.getRootElement().element("Service").element("Engine").element("Host");
            List<Element> contexts = host.elements("Context");

            boolean found = false;

            for (Element context : contexts) {
                String path = context.attributeValue("path").substring(1);
                if(path.equals(oldPath)){
                    if("".equals(newPath)){
                        host.remove(context);
                    }else{
                        context.setAttributeValue("path", "/" + newPath);
                        context.setAttributeValue("docBase", newDocBase.replaceAll("\\\\", "/"));
                    }
                    found = true;
                    break;
                }
            }

            if (!found){
                Element element = host.addElement("Context");
                element.addAttribute("path", "/" + newPath);
                element.addAttribute("docBase", null==newDocBase?"": newDocBase.replaceAll("\\\\", "/"));
                tipLabel.setText("新增成功:"+newPath);
            }
            OutputFormat format = new OutputFormat();
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format);
            writer.write(document);
            tipLabel.setText("更新成功:" + newPath);
            initData();
        } catch (DocumentException|IOException e) {
            e.printStackTrace();
            tipLabel.setText("错误了");
            initData();
        }
    }

    private void initData() {
        try {
            data.clear();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlPath));
            Element rootElement = document.getRootElement();
            List<Element> contexts = rootElement.element("Service").element("Engine").element("Host").elements("Context");

            for (Element context : contexts) {
                String path = context.attributeValue("path").substring(1);
                String docBase = context.attributeValue("docBase");
                SeisTomcat seisTomcat = new SeisTomcat();
                seisTomcat.setPath(path);
                seisTomcat.setDocBase(docBase);
                data.add(seisTomcat);
            }

            SeisTomcat seisTomcat = new SeisTomcat();
            data.add(seisTomcat);

        } catch (DocumentException e) {
            e.printStackTrace();
            tipLabel.setText("错误了!");
        }
    }

    public void chooseFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文件");
        File file = fc.showOpenDialog(tomcatTable.getScene().getWindow());
        if(null!=file){
            xmlPathField.setText(file.getPath());
            xmlPath = file.getPath();
            initData();
        }
    }
}
