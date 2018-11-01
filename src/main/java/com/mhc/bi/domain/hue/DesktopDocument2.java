package com.mhc.bi.domain.hue;

import java.util.Date;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
public class DesktopDocument2 {
    private int id;
    private int ownerId;
    private String name;
    private String description;
    private String uuid;
    private String type;
    private String data;
    private String extra;
    private Date lastModified;
    private int version;
    private int isHistory;
    private int parentDirectoryId;
    private String search;
    private int isManaged;
    private int isTrashed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(int isHistory) {
        this.isHistory = isHistory;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getIsManaged() {
        return isManaged;
    }

    public void setIsManaged(int isManaged) {
        this.isManaged = isManaged;
    }

    public int getIsTrashed() {
        return isTrashed;
    }

    public void setIsTrashed(int isTrashed) {
        this.isTrashed = isTrashed;
    }

    public int getParentDirectoryId() {
        return parentDirectoryId;
    }

    public void setParentDirectoryId(int parentDirectoryId) {
        this.parentDirectoryId = parentDirectoryId;
    }
}
