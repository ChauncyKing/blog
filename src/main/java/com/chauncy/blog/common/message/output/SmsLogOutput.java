package com.chauncy.blog.common.message.output;

import java.io.Serializable;
import java.util.List;

/**
 * 短信日志输出对象
 */
public class SmsLogOutput implements Serializable {

    /**
     * 状态 - status : success
     * 应用ID - appid : your_app_id
     * 日志条数 - count : 2
     * 开始 - start_row : 1
     * 结束 - end_row : 2
     * 起始日期 - start_date : 2014-11-24 00:35:14
     * 结束日期 - end_date : 2015-04-02 16:00:00
     * 日志记录 -results : [{"sendID":"7ac69ff98e48c9b3b0f49afa5a0788c7","project":"Gy1CR1","recipient":"138xxxxxxxx","message":"如需重置您的 SUBMAIL 账户密码，请在重置页面输入此验证码：1425 ，并输入新的密码。","signature":"【SUBMAIL】","result_status":"delivered","api":"message/xsend","send_date":"2015-01-19 15:21:27","sent_date":"2015-01-19 15:21:27","length":56,"credit":1},{"sendID":"e5c77f9a1c6728140c06824196eaa480","project":"Gy1CR1","recipient":"138xxxxxxxx","message":"如需重置您的 SUBMAIL 账户密码，请在重置页面输入此验证码：1325 ，并输入新的密码。","signature":"【SUBMAIL】","result_status":"sending","api":"message/xsend","send_date":"2015-01-19 15:20:52","sent_date":"2015-01-19 15:20:53","length":56,"credit":1}]
     */

    private String status;

    private String appId;

    private int count;

    private int startRow;

    private int endRow;

    private String startDate;

    private String endDate;

    private List<LogResult> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<LogResult> getResults() {
        return results;
    }

    public void setResults(List<LogResult> results) {
        this.results = results;
    }


}
