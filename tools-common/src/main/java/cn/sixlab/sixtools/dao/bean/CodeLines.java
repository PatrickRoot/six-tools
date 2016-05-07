/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 * 
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/2/18)
 */
package cn.sixlab.sixtools.dao.bean;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/2/18)
 */
public class CodeLines {
    private String fileType;
    private int nullLines;
    private int allLines;
    private int codeLines;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getAllLines() {
        return allLines;
    }

    public void setAllLines(int allLines) {
        this.allLines = allLines;
    }

    public int getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(int codeLines) {
        this.codeLines = codeLines;
    }

    public int getNullLines() {
        return nullLines;
    }

    public void setNullLines(int nullLines) {
        this.nullLines = nullLines;
    }
}
