/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.code.line;

import cn.sixlab.sixtools.dao.bean.sqlite.ToolMeta;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsCodeLine;
import cn.sixlab.sixtools.dao.util.D;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:30
 */
public class CodeLine  {

    private static List<ToolMeta> dirs = new ArrayList<>();
    private static List<ToolMeta> fileTypes = new ArrayList<>();

    public static void main(String[] args) {
        //title = "Six Plan : " + A.get();
        //C.implicitExit = true;
        //launch(args);

        Dao dao = D.dao;
        dirs = dao.query(ToolMeta.class, Cnd.where("toolKey", "=", "code-line-dir").and("toolFlag","=","1"));
        fileTypes = dao.query(ToolMeta.class, Cnd.where("toolKey", "=", "code-line-file").and("toolFlag", "=", "1"));

        Map<String, ToolsCodeLine> result = new HashMap<>();
        Set<String> his = new HashSet<>();

        for (ToolMeta dir : dirs) {
            String path = dir.getToolValue();
            File a = new File(path);
            if(a.exists()){
                if(a.isDirectory()){
                    System.out.println(a.getAbsolutePath());
                    loop(a, his, result);
                }
            }
        }
        System.out.println("\n\n\n---------------------------------------\n\n\n");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String now = localDateTime.format(formatter);
        for (String key : result.keySet()) {
            ToolsCodeLine codeLines = result.get(key);
            codeLines.setCheckDate(now);
            dao.insert(codeLines);
        }
    }

    private static void loop(File dir, Set<String> his, Map<String, ToolsCodeLine> result) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                if(file.getName().equalsIgnoreCase("target")){
                    break;
                }
                loop(file, his, result);
            }else{
                if (his.add(file.getAbsolutePath())) {
                    String fileName = file.getName().toLowerCase();
                    boolean flag = true;
                    for (ToolMeta type : fileTypes) {
                        if (flag && fileName.endsWith(type.getToolValue().toLowerCase())) {
                            countLine(file, type.getToolValue(), result);
                            flag = false;
                        }
                    }
                    if(flag){
                        his.remove(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static void countLine(File file, String type, Map<String, ToolsCodeLine> result) {
        try {
            ToolsCodeLine codeLines;
            if (result.containsKey(type)) {
                codeLines = result.get(type);
            } else {
                codeLines = new ToolsCodeLine();
                codeLines.setCodeType(type);
                codeLines.setAllNum(0);
                codeLines.setCodeNum(0);
                codeLines.setEmptyNum(0);
            }
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            String str;
            while ((str = br.readLine()) != null) {
                str = str.replaceAll("\\s","");
                if(str.length()==0){
                    codeLines.setEmptyNum(codeLines.getEmptyNum() + 1);
                }else{
                    codeLines.setCodeNum(codeLines.getCodeNum() + 1);
                }

                codeLines.setAllNum(codeLines.getAllNum()+1);
            }

            result.put(type, codeLines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}