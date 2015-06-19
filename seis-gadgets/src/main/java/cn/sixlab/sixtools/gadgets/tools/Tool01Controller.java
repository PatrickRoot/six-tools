/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets.tools;

import cn.sixlab.sixtools.gadgets.GadgetsController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
 * @date 2015/6/18 11:37
 */
public class Tool01Controller {
    private static Logger logger = LoggerFactory.getLogger(Tool01Controller.class);
    
    public TextArea leftText;
    public TextArea rightText;

    public void textChange(Event event) {
        TextArea textArea = (TextArea) event.getTarget();
        GadgetsController.ctrl.tipsLabel.setText(textArea.getText().length()+" 个字符");
    }

    public void itemClick(ActionEvent event) {
        Button btn = (Button) event.getTarget();
        String id = btn.getId().substring(6);
        String methodName = id;
        try {

            Method method = this.getClass().getMethod(methodName, ActionEvent.class);
            method.invoke(this, event);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    public void i01(ActionEvent event) throws UnsupportedEncodingException {
        String text = leftText.getText();
        logger.info("left:"+text);
        String result = URLEncoder.encode(text, "utf-8");
        rightText.setText(result);
    }
    
    public void i02(ActionEvent event) throws UnsupportedEncodingException {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = URLDecoder.decode(text, "utf-8");
        rightText.setText(result);
    }

    public void i03(ActionEvent event) {
        String text = leftText.getText();
        logger.info("left:" + text);
        String result = (new BASE64Encoder()).encode(text.getBytes());
        rightText.setText(result);
    }

    public void i04(ActionEvent event) throws IOException {
        String text = leftText.getText();
        logger.info("left:" + text);
        byte[] bytes = (new BASE64Decoder()).decodeBuffer(text);
        rightText.setText(new String(bytes));
    }

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

    public void i06(ActionEvent event) {
        UUID uuid = UUID.randomUUID();
        rightText.setText(uuid.toString() + "\n\n" + rightText.getText().replaceFirst("\n",""));
    }

    public void i07(ActionEvent event) {

    }

    public void i08(ActionEvent event) {}
    public void i09(ActionEvent event) {}
    public void i10(ActionEvent event) {}
    public void i11(ActionEvent event) {}
    public void i12(ActionEvent event) {}
    public void i13(ActionEvent event) {}
    public void i14(ActionEvent event) {}
    public void i15(ActionEvent event) {}
    public void i16(ActionEvent event) {}
}
