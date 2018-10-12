package com.chauncy.blog.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.chauncy.blog.common.util.PropertyUtil;

public class AliPayConfig {

    private static AlipayClient alipayClient = null;
    /**
     * 支付宝网关（沙箱环境）
     */
    private static final String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    private static final String APPID = "2016092200569877";

    /**
     * 开发者私钥,由开发者自己生成
     */
    private static final String APP_PRIVATE_KEY = PropertyUtil.getProperty("APP_PRIVATE_KEY");

    /**
     * 参数返回格式,只支持json(固定)
     */
    private static final String FORMAT = "json";

    /**
     * 编码集,支持GBK/UTF-8
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 支付宝公钥,由支付宝生成（从配置文件中读取）
     */
    private static final String ALIPAY_PUBLIC_KEY = PropertyUtil.getProperty("ALI_PUBLIC_KEY");

    /**
     * 商户生成签名字符串所使用的签名算法类型,目前支持RSA2和RSA,推荐使用RSA2
     */
    private static final String SIGN_TYPE = "RSA2";

    // 初始化
    static {
        // 参数解释 : new DefaultAlipayClient(支付宝网关,APPID,应用私钥,参数返回格式,编码集,支付宝公钥,签名算法类型,encryptKey,encryptType)
        alipayClient = new DefaultAlipayClient(URL, APPID, APP_PRIVATE_KEY, FORMAT, CHARSET, APP_PRIVATE_KEY, SIGN_TYPE);

    }

    public static AlipayClient getAlipayClient() {
        return alipayClient;
    }
}
