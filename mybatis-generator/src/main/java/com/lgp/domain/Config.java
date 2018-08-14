package com.lgp.domain;

public class Config {
    private Long id;

    private Long createTime;

    private String defaultCollectType;

    private String defaultFavorties;

    private String defaultModel;

    private Long lastModifyTime;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDefaultCollectType() {
        return defaultCollectType;
    }

    public void setDefaultCollectType(String defaultCollectType) {
        this.defaultCollectType = defaultCollectType == null ? null : defaultCollectType.trim();
    }

    public String getDefaultFavorties() {
        return defaultFavorties;
    }

    public void setDefaultFavorties(String defaultFavorties) {
        this.defaultFavorties = defaultFavorties == null ? null : defaultFavorties.trim();
    }

    public String getDefaultModel() {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel) {
        this.defaultModel = defaultModel == null ? null : defaultModel.trim();
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}