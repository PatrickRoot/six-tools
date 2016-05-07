package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

@Table("tools_code_line")
public class ToolsCodeLine {

    @Id
    private int id;
    @Column("check_date")
    private String checkDate;
    @Column("all_num")
    private int allNum;
    @Column("empty_num")
    private int emptyNum;
    @Column("code_num")
    private int codeNum;
    @Column("code_type")
    private String codeType;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckDate () {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public int getAllNum () {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getEmptyNum () {
        return emptyNum;
    }

    public void setEmptyNum(int emptyNum) {
        this.emptyNum = emptyNum;
    }

    public int getCodeNum () {
        return codeNum;
    }

    public void setCodeNum(int codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeType () {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

}