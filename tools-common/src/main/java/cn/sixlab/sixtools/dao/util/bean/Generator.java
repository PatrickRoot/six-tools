/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.sixtools.dao.util.bean;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href='https://blog.sixlab.cn/'>六楼的雨/Patrick Root</a>
 */
public abstract class Generator {
    
    public static String ORM_NODE_ORM2 = "node-orm2";
    
    private String drive = "";
    private String url = "";
    
    public void writeFile(String tableName){
    
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
