package com.mhc.bi.vo.tasksubmit;

import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.service.DesktopDocument2Service;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/26
 * @description File类表示文件的存储结构，包括文件的类型（文件夹or目录），文件的id,文件的名称，文件的子集合，文件的父目录id
 */
public class File {


    private int type;
    private int id;
    private String name;
    private List<Integer> childrenId;
    private List<File> children;
    private Integer parent;
    private String shelltype;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public List<Integer> getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(List<Integer> childrenId) {
        this.childrenId = childrenId;
    }

    public List<File> getChildren() {
        return children;
    }

    public void setChildren(List<File> children) {
        this.children = children;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getShelltype() {
        return shelltype;
    }

    public void setShelltype(String shelltype) {
        this.shelltype = shelltype;
    }

    public void initByDesktopDocument2(DesktopDocument2 desktopDocument2,DesktopDocument2Service desktopDocument2Service ) {
        if (!desktopDocument2.getType().equals("directory")) {
            //先设置shelltype
            if (desktopDocument2.getName().endsWith(".bi")) this.setShelltype("bi");
            else if (desktopDocument2.getType().equals("query-hive"))
                this.setShelltype("hiveshell");
            else if (desktopDocument2.getType().contains("shell")) this.setShelltype("shell");
            else this.setShelltype(desktopDocument2.getType());

            this.setType(1);
        } else this.setType(0);
        this.id = desktopDocument2.getId();
        this.name = desktopDocument2.getName();
        this.childrenId = desktopDocument2Service.getChildrenList(this.id);
        this.parent = desktopDocument2.getParentDirectoryId();
    }
}