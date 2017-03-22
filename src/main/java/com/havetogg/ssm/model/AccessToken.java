package com.havetogg.ssm.model;

import com.havetogg.ssm.model.base.BaseEntity;

import java.util.Date;

/**
 * Created by admin on 2017/3/16.
 * accessToken类实体
 */
public class AccessToken extends BaseEntity{
    private long id;
    private long userId;
    private String accessToken;
    private Date creatTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
