package com.mhc.bi.vo;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/26
 * @description File类表示文件的存储结构，包括文件的类型（文件夹or目录），文件的id,文件的名称，文件的子集合，文件的父目录id
 */
public class File {
    private String type;
    private int id;
    private String name;
    private List<Integer> children;
    private Integer parent;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public List<Integer> getChildren() {
        return children;
    }

    public void setChildren(List<Integer> children) {
        this.children = children;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
