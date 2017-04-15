/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/28 13:38
 */
public class DUtil {

    private static String[] tableNames = new String[]{
            "seis_seis_values",
    };

    private String drive = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:sixtools.db";

    private String copyright = "Sixlab";
    private String user = "六楼的雨/Patrick Root";
    private String email = "nianqinianyi@163.com";

    public static void main(String[] args) throws Exception {
        for (String tableName : tableNames) {
            new Thread(()->{
                try {
                    DUtil d = new DUtil();
                    List<TableColumn> columns = d.getColumnType(tableName);
                    d.genBeanFile(columns, tableName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("end");
    }

    public List<TableColumn> getColumnType(String tableName) throws Exception {

        Class.forName(drive);
        Connection con = DriverManager.getConnection(url);
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet rs = metaData.getColumns(con.getCatalog(), "main", tableName, null);
        List<TableColumn> columnList = new ArrayList<>();
        while (rs.next()) {
            TableColumn column = new TableColumn();
            column.setColumnName(rs.getString("COLUMN_NAME"));
            column.setColumnType(rs.getString("TYPE_NAME"));
            columnList.add(column);
        }
        return columnList;
    }

    private void genBeanFile(List<TableColumn> columnType, String tableName) throws IOException {
        StringBuffer javaFile = new StringBuffer();
        String className = S.getCamel(tableName, false);

        appendTitle(javaFile);

        javaFile.append("@Table(\"" + tableName + "\")\n");
        javaFile.append("public class " + className + " {\n\n");

        for (TableColumn tableColumn : columnType) {
            String name = tableColumn.getColumnName();
            String type = tableColumn.getColumnType();

            if ("id".equals(name)) {
                javaFile.append("    @Id\n");
            } else {
                javaFile.append("    @Column(\"" + name + "\")\n");
            }

            switch (type) {
                case "INTEGER":
                    javaFile.append("    private int " + S.getCamel(name) + ";\n");
                    break;
                case "REAL":
                    javaFile.append("    private boolean " + S.getCamel(name) + ";\n");
                    break;
                case "TEXT":
                case "BLOB":
                    javaFile.append("    private String " + S.getCamel(name) + ";\n");
                    break;
            }
        }

        for (TableColumn tableColumn : columnType) {
            String name = tableColumn.getColumnName();
            String type = tableColumn.getColumnType();
            switch (type) {
                case "INTEGER":
                    appendGetterSetter(javaFile, "int", name);
                    break;
                case "REAL":
                    appendGetterSetter(javaFile, "boolean", name);
                    break;
                case "TEXT":
                case "BLOB":
                    appendGetterSetter(javaFile, "String", name);
                    break;
            }
        }

        javaFile.append("\n}");
        String filePath = new File(getClass().getResource("/").getPath()).getParentFile().getParent() + File.separator + "temp" + File.separator + className + ".java";
        FileWriter writer = new FileWriter(filePath);
        writer.write(javaFile.toString());
        writer.flush();
        writer.close();
    }

    private void appendTitle(StringBuffer javaFile) {
        javaFile.append("/**\n * @Copyright © " + copyright + " " + LocalDate.now().getYear() + "\n" +
                " * @author " + user + "\n * @email <" + email + ">\n */\n");
        javaFile.append("package cn.sixlab.sixtools.comun.bean.db;\n\n");
        javaFile.append("import org.nutz.dao.entity.annotation.Table;\n");
        javaFile.append("import org.nutz.dao.entity.annotation.Id;\n");
        javaFile.append("import org.nutz.dao.entity.annotation.Column;\n\n");
        javaFile.append("/**\n * TODO\n *\n * @author " + user + "\n" +
                " * @date " + LocalDateTime.now() + "\n */\n");
    }

    private void appendGetterSetter(StringBuffer javaFile, String type, String name) {
        String lowerName = S.getCamel(name);
        String upperName = S.getCamel(name, false);
        javaFile.append("\n");
        javaFile.append("    public " + type + " get" + upperName + " () {\n");
        javaFile.append("        return " + lowerName + ";\n    }\n");

        javaFile.append("\n");
        javaFile.append("    public void set" + upperName + "(" + type + " " + lowerName + ") {\n");
        javaFile.append("        this." + lowerName + " = " + lowerName + ";\n    }\n");
    }

    private class TableColumn {
        private String columnName;
        private String columnType;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }
    }
}
