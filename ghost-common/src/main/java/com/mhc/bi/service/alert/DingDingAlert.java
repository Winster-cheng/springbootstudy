package com.mhc.bi.service.alert;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author baiyan
 * @date 2018/07/25
 * @description if node error ,send error msg to dingding
 */
@Service
public class DingDingAlert {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=aa9e4a7c27ffb17e33b60df5a9d172c413fc704c7b9fc4e932bb36bf424f953d";

    public  void sendMsg(String msg) {

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + msg + "\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = null;
            try {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }
    }
}