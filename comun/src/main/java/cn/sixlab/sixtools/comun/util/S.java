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

    /**
     * 判断字符串既不是null也不是空字符串
     *
     * @param ulStr          下划线分割的字符串
     * @return 返回驼峰命名法的字符串，首字母小写。
     */
    public static String getCamel(String ulStr) {
        return getCamel(ulStr, true);
    }

    /**
     * 判断字符串既不是null也不是空字符串
     *
     * @param ulStr 下划线分割的字符串
     * @param isFirstLower 首字母是否小写
     * @return 返回驼峰命名法的字符串，@{code isFirstCapital}为true，则首字母也是大写，否则首字母小写。
     */
    public static String getCamel(String ulStr, boolean isFirstLower) {
        StringBuffer result = new StringBuffer();

        String[] ulArray = ulStr.split("_");

        boolean isFirst = true;
        for (String s : ulArray) {
            if (isNotEmpty(s)){
                s = lowerCase(s);
                if(isFirst && isFirstLower){
                    result.append(s);
                    isFirst = false;
                }else{
                    result.append(swapCase(s.substring(0, 1))).append(s.substring(1, s.length()));
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(getCamel("test_test_test", true));
        System.out.println(getCamel("test_test_test", false));
        System.out.println(getCamel("test_test_test"));
        System.out.println(getCamel("Test_test_test", true));
        System.out.println(getCamel("Test_test_test", false));
        System.out.println(getCamel("Test_test_test"));
        System.out.println(getCamel("test_TE_test", true));
        System.out.println(getCamel("test_TE_test", false));
        System.out.println(getCamel("test_TE_test"));
    }

    /**
     * 把一个字符串中的大写转为小写，小写不变
     *
     * @param str 被转化的字符串
     * @return 结果
     */
    public static String lowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] buffer = str.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            }
        }
        return new String(buffer);
    }

    /**
     * 把一个字符串中的大写转为小写，小写转换为大写
     * @param str 被转化的字符串
     * @return 结果
     */
    public static String swapCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] buffer = str.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                buffer[i] = Character.toUpperCase(ch);
            }
        }
        return new String(buffer);
    }
}
