package com.mhc.bi.vo.taskinstance;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class Status {
    private int id;
    private String name;
    private String chineseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public static Status getStatus(int statusId) {
        Status status=new Status();
        if (statusId == 1) {
            status.setId(1);
            status.setName("committed");
            status.setChineseName("已提交");
        } else if (statusId == 2) {
            status.setId(2);
            status.setName("waited");
            status.setChineseName("等待中");
        } else if (statusId == 3) {
            status.setId(3);
            status.setName("running");
            status.setChineseName("运行中");
        } else if (statusId == 4) {
            status.setId(4);
            status.setName("success");
            status.setChineseName("成功");
        } else if (statusId == 5) {
            status.setId(5);
            status.setName("failed");
            status.setChineseName("失败");
        } else {
            try {
                throw new Exception("状态码 " + statusId + " 不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}