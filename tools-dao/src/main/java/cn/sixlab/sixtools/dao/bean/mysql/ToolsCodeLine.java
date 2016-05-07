package cn.sixlab.sixtools.dao.bean.mysql;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("tools_code_line")
public class ToolsCodeLine {

    @Id
    private int id;
    @Column("check_date")
    private Date checkDate;
    @Column("all_num")
    private int allNum;
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

    public Date getCheckDate () {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public int getAllNum () {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
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