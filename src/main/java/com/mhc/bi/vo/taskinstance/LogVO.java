package com.mhc.bi.vo.taskinstance;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class LogVO {
    private String time;
    private String content;
    private Status status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
