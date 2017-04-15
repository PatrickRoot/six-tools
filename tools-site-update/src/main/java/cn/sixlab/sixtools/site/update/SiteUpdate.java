/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.site.update;

import cn.sixlab.sixtools.dao.util.A;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.base.BaseMain;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/19 15:37
 */
public class SiteUpdate extends BaseMain {

    public static void main(String[] args) {
        title = "Six Tools Site Update : " + A.get();
        C.implicitExit = true;
        launch(args);
    }

}
