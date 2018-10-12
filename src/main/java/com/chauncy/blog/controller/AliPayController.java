package com.chauncy.blog.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.chauncy.blog.alipay.AliPayConfig;
import com.chauncy.blog.common.result.AjaxResult;
import com.chauncy.blog.common.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
@Slf4j
public class AliPayController {

    /**
     * 请求支付接口
     * localhost:8080/alipay/payRequest/test
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/payRequest/test")
    public void payRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AlipayClient alipayClient = AliPayConfig.getAlipayClient();

        // 创建API对应的 request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();

        //在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl("http://127.0.0.1:8080/alipay/CallBack/return_url");
        alipayRequest.setNotifyUrl("http://127.0.0.1:8080/alipay/CallBack/notify_url");

        //填充业务参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"99999999999999999999999999999999999991\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":\"9.9\"," +
                "    \"subject\":\"IphoneXS 256G\"," +
                "    \"body\":\"IphoneXS 256G\"" +
                "  }");

        String form = "";

        try {
            // 调用 SDK 生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 支付异步回调通知
     */
    @RequestMapping("/CallBack/notify_url")
    public void asyncPayNotify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {

        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //调用 SDK 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, PropertyUtil.getProperty("ALI_PUBLIC_KEY"), "UTF-8", "RSA2");

        if (signVerified) {
            // 获取订单号（需要验证是否是商户系统中创建的订单号）
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            // 判断该笔订单是否已经在商户系统中做过处理
            if (trade_status.equals("TRADE_FINISHED")) {

            }

            // 返回成功
            log.info("{}", out_trade_no + " " + trade_no + " " + trade_status);
        } else {
            log.info("ERROR");
            // 返回失败
        }
    }

    /**
     * 支付异步回调通知
     */
    @RequestMapping("/CallBack/return_url")
    @ResponseBody
    public AjaxResult syncPayNotify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {

        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        return AjaxResult.success(params);
    }
}