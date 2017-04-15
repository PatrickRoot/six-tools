/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/7/16 23:12
 */
public class Collection {

    public static boolean isEmpty(java.util.Collection collection){
        if(null == collection){
            return true;
        }else if(collection.size()<1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNotEmpty(java.util.Collection collection) {
        if (null == collection) {
            return false;
        } else if (collection.size() < 1) {
            return false;
        } else {
            return true;
        }
    }
}
