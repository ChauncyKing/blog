package com.chauncy.blog.common.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    /**
     * 获取完整的 URL
     * 因为使用request.getRequestURL()获取的地址，在某些情况下（nginx转发），会丢失端口号，因此这里手动拼接地址
     *
     * @param request
     * @return
     */
    public static String getRealUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        String query = request.getQueryString();
        if (!StringUtil.isNullOrEmpty(query)) {
            url += ("?" + query);
        }
        return url;
    }

    /**
     * todo : 获取请求的来源（归属地）
     */
    public String getUrlAttribution(String url) {
        return null;
    }
}
