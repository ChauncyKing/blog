package com.chauncy.blog.common.message;

import com.alibaba.fastjson.JSONObject;
import com.chauncy.blog.common.constant.GlobalConsts;
import com.chauncy.blog.common.message.output.SmsErrorOutput;
import com.chauncy.blog.common.message.output.SmsLogOutput;
import com.chauncy.blog.common.message.output.SmsSuccessOutput;
import com.chauncy.blog.common.result.AjaxResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MessageSender {

    @Autowired
    private MessageConfig config;

    /**
     * 应用集成中创建的短信应用 ID [必需]
     */
    public static final String APPID = "appid";

    /**
     * 密匙 或 数字签名 [必需]
     * 密钥明文验证模式 ：直接在 signature 参数中配置应用密钥，此验证模式可以忽略 timestamp 和 sign_type 参数
     * 数字签名验证模式 ：使用于安全要求较高的应用，使用数字签名模式，需要将 sign_type 参数设为 md5 或者 sha1，同时需要创建签名字符串（创建规则请参考文档）
     */
    public static final String SIGNATURE = "signature";

    /**
     * 设置短信收件人手机号码，短信仅支持一对一模式 (即单条API请求仅能发送一个联系人)
     */
    public static final String TO = "to";

    /**
     * 短信正文
     * e.g. 【验证码】您的短信验证码：9587，请在10分钟内输入。【XXX】必须存在且置于短信正文最前
     * content 参数将会与您账户中的短信模板进行匹配，如无匹配 API 会创建一个短信模板并提交审核
     */
    public static final String CONTENT = "content";

    /**
     * 请求客户端
     */
    private static final CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();


    /**
     * 请求服务器，发送验证码
     *
     * @param phoneNum 短信接收号码
     * @return
     */
    public AjaxResult send(String phoneNum) {
        ParamMap paramMap = new ParamMap();
        paramMap.addWithSeparator(TO, phoneNum);
        // todo:设置短信模板
        paramMap.addWithSeparator(CONTENT, "【验证码】9587");

        return executeRequest(config.getUrl(), paramMap, SmsSuccessOutput.class);
    }

    /**
     * 获取短信发送的日志
     */
    public AjaxResult getMessageLog() {
        ParamMap paramMap = new ParamMap();
        Long now = System.currentTimeMillis();
        // 查询最近一个月的短信发送日志
        Long startTime = now - (1000 * 60 * 60 * 24 * 30);
        paramMap.addWithSeparator("start_date", startTime.toString());

        return executeRequest(config.getLogUrl(), paramMap, SmsLogOutput.class);
    }

    /**
     * 【Post】方式请求短信服务器
     *
     * @param url    请求短信服务器地址
     * @param params 请求的参数
     * @param clazz  请求成功返回的结果类型
     * @return
     */
    private <T> AjaxResult executeRequest(String url, Map<String, String> params, Class<T> clazz) {

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("charset", "UTF-8");
        //设置请求参数 (类型：HttpEntity)
        httpPost.setEntity(build(params));

        try {
            // 执行请请求
            HttpResponse response = closeableHttpClient.execute(httpPost);
            // 获取结果
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 获取 JSON 字符串格式的请求结果
                String responseJsonStr = EntityUtils.toString(httpEntity, "UTF-8");
                //TODO : 日志记录短信请求结果
                // JSON 字符串转换成 JSON 对象
                JSONObject responseJsonObj = JSONObject.parseObject(responseJsonStr);

                // 获取结果状态
                String status = responseJsonObj.getString("status");
                if (GlobalConsts.ERROR.equals(status)) {
                    SmsErrorOutput output = JSONObject.parseObject(responseJsonStr, SmsErrorOutput.class);
                    return AjaxResult.error(output.getCode(), "[短信服务异常]" + output.getMsg());
                }
                if (GlobalConsts.SUCCESS.equals(status)) {
                    T output = JSONObject.parseObject(responseJsonStr, clazz);
                    return AjaxResult.success(output);
                }
            }
            // 关闭连接
            closeableHttpClient.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将请求参数转换为 HttpEntity
     * 请求参数中，固定封装了 应用ID，签名
     *
     * @param data
     * @return
     */
    private HttpEntity build(Map<String, String> data) {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 配置短信应用 ID
        data.put(APPID, config.getAppId());
        // 配置签名（密钥明文）
        data.put(SIGNATURE, config.getAppKey());
        // 配置 mimeType 和 charset
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);

        // 遍历 dataMap ，加入到 MultipartEntityBuilder 中构造请求报文
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.addTextBody(key, value, contentType);
        }
        return builder.build();
    }

}
