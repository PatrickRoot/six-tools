/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.encode;

import cn.sixlab.sixtools.comun.util.S;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/22 19:30
 */
public class EncodeController {
    private static Logger logger = LoggerFactory.getLogger(EncodeController.class);

    public TextArea leftText;
    public TextArea rightText;
    public Label tipsLabel;

    public void textChange(Event event) {
        TextArea textArea = (TextArea) event.getTarget();
        tipsLabel.setText(textArea.getText().length() + " 个字符");
    }

    public void itemClick(ActionEvent event) {
        Button btn = (Button) event.getTarget();
        String id = btn.getId().substring(6);
        String methodName = id;
        try {

            Method method = this.getClass().getMethod(methodName, ActionEvent.class);
            method.invoke(this, event);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * URL编码
     *
     * @param event
     * @throws UnsupportedEncodingException
     */
    public void i01(ActionEvent event) throws UnsupportedEncodingException {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = URLEncoder.encode(text, "utf-8");
        rightText.setText(result);
    }

    /**
     * URL解码
     *
     * @param event
     * @throws UnsupportedEncodingException
     */
    public void i02(ActionEvent event) throws UnsupportedEncodingException {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = URLDecoder.decode(text, "utf-8");
        rightText.setText(result);
    }

    /**
     * base64 编码
     *
     * @param event
     */
    public void i03(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = (new BASE64Encoder()).encode(text.getBytes());
        rightText.setText(result);
    }

    /**
     * base64 解码
     *
     * @param event
     * @throws IOException
     */
    public void i04(ActionEvent event) throws IOException {
        String text = leftText.getText();
        logger.info("left:" + text);
        byte[] bytes = (new BASE64Decoder()).decodeBuffer(text);
        rightText.setText(new String(bytes));
    }

    /**
     * MD5 加密
     *
     * @param event
     * @throws NoSuchAlgorithmException
     */
    public void i05(ActionEvent event) throws NoSuchAlgorithmException {
        String text = leftText.getText();
        logger.info("left:" + text);
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = text.getBytes();
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(btInput);
        byte[] md = mdInst.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        rightText.setText(new String(str));
    }

    /**
     * UUID
     *
     * @param event
     */
    public void i06(ActionEvent event) {
        UUID uuid = UUID.randomUUID();
        rightText.setText(uuid.toString() + "\n\n" + rightText.getText().replaceFirst("\n", ""));
    }

    /**
     * 计算表达式
     *
     * @param event
     */
    public void i07(ActionEvent event) throws ScriptException {
        String text = leftText.getText();
        logger.info("left:" + text);
        if (S.isNotEmpty(text)) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");

            String js;
            js = "function calculate(){return " + text + "} \n calculate()";

            String result = engine.eval(js).toString();
            try {
                rightText.setText(String.valueOf(Integer.parseInt(result)));
            } catch (Exception e) {
                rightText.setText(String.valueOf(Double.parseDouble(result)));
            }
        }
    }

    /**
     * 人民币大写
     *
     * @param event
     */
    public void i08(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = capital(text);
        rightText.setText(result);
    }

    private static String capital(String text) {
        String result = "";
        String dict = "仟佰拾亿仟佰拾万仟佰拾元角分";
        text += "00";
        int dotPos = text.indexOf('.');
        if (dotPos >= 0) {
            text = text.substring(0, dotPos) + text.substring(dotPos + 1, dotPos + 3);
        }
        dict = dict.substring(dict.length() - text.length());
        for (int i = 0; i < text.length(); i++) {
            int temp = Integer.valueOf(text.substring(i, i + 1));
            result += "零壹贰叁肆伍陆柒捌玖".substring(temp, temp + 1) + dict.substring(i, i + 1);
        }
        result = result.replace("/零角零分$/", "整").replace("/零[仟佰拾]/g", "零").
                replace("/零{2,}/g", "零").replace("/零([亿|万])/g", "$1").replace("/零+元/", "元").
                replace("/亿零{0,3}万/", "亿").replace("/^元/", "零元");
        return result;
    }

    /**
     * Unicode JS编码
     *
     * @param event
     */
    public void i09(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            int point = text.codePointAt(i);
            String temp = Integer.toHexString(point);
            int length = 4 - temp.length();
            if (length == 2) {
                result += "\\u00" + temp;
            } else if (length == 3) {
                result += "\\u000" + temp;
            } else if (length == 1) {
                result += "\\u0" + temp;
            } else {
                result += "\\u" + temp;
            }
        }

        rightText.setText(result);
    }

    /**
     * Unicode JS解码
     *
     * @param event
     */
    public void i10(ActionEvent event) throws UnsupportedEncodingException {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = "";
        String[] strings = text.split("\\\\u");
        for (String string : strings) {
            if (!"".equals(string)) {
                result += (char) Integer.parseInt(string, 16);
            }
        }
        rightText.setText(result);
    }

    /**
     * Unicode HTML编码
     *
     * @param event
     */
    public void i11(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = text.replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;").
                replace("\"", "&quot;").replace("\''", "&#039;");
        rightText.setText(result);
    }

    /**
     * Unicode HTML解码
     *
     * @param event
     */
    public void i12(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = "";
        result.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&").
                replace("&quot;", "\"").replace("&#039;", "\''");
        rightText.setText(result);
    }

    public void i13(ActionEvent event) {}
    public void i14(ActionEvent event) {}
    
}
