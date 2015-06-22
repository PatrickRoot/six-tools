/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.util;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/22 9:00
 */
public class S {

    public static Boolean isNumber(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是null或者空字符串
     *
     * @param str 要传入的字符串
     * @return 返回true则{@code str}是null或者空字符串； 返回false则{@code str}既不是null也不是空字符串。
     */
    public static Boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串既不是null也不是空字符串
     *
     * @param str 要传入的字符串
     * @return 返回true则{@code str}既不是null也不是空字符串； 返回false则{@code str}是null或者空字符串。
     */
    public static Boolean isNotEmpty(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        return true;
    }
}
