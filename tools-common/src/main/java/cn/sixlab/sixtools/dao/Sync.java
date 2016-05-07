/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao;

import cn.sixlab.sixtools.dao.util.Prop;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 15-7-22 下午9:40
 */
public class Sync {

    public static void main(String[] args) throws Exception {
        //sync();
        System.out.println();
    }

    public static void sync() throws Exception {
        auth();
    }

    private static void auth() throws Exception {
        URL url = genOauthUrl();
        HttpURLConnection connection ;
    }

    private static URL genOauthUrl() throws MalformedURLException, UnsupportedEncodingException {
        String baseUrl = Prop.get("kuai_pan_other_url");
        String params = getParams();
        String sign = ("oauth_signature&" + signature(baseUrl, params, "GET"));
        return new URL(baseUrl+"?"+params+sign);
    }

    private static String getParams() {
        String params = "";
        params += ("oauth_nonce&" + String.valueOf(new Random().nextInt(9999999)));
        params += ("oauth_timestamp&" + String.valueOf(System.currentTimeMillis()));
        params += ("oauth_consumer_key&" + Prop.get("kuai_pan_consumer_key"));
        params += ("oauth_signature_method&" + "HMAC-SHA1");
        return params;
    }

    private static String signature(String baseUrl, String params, String httpMethod)
            throws UnsupportedEncodingException {
        String signature = httpMethod+"&";
        signature+= URLEncoder.encode(baseUrl, "UTF-8");
        signature+="&";
        signature+= URLEncoder.encode(params, "UTF-8");
        return null;

        /**
         * http_method + "&" +
         url_encode( base_uri ) + "&" +
         url_encode(
         “&”.join(
         sort( [url_encode ( k ) + "=" +url_encode ( v ) for k, v in paramesters.items() ]
         )
         )
         */
    }

}
