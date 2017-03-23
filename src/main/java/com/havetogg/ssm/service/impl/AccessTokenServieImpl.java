package com.havetogg.ssm.service.impl;

import com.havetogg.ssm.dao.AccessTokenDao;
import com.havetogg.ssm.model.AccessToken;
import com.havetogg.ssm.service.AccessTokenService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
public class AccessTokenServieImpl implements AccessTokenService{

    @Resource
    private AccessTokenDao accessTokenDao;

    @Override
    public void insertAccessToken(AccessToken accessToken) {
        accessTokenDao.insertAccessToken(accessToken);
    }

    @Override
    public AccessToken getAccessTokenByUid(String uid) {
        return accessTokenDao.getAccessTokenByUid(uid);
    }

    //检验accessToken是否有效
    @Override
    public boolean idExpire(String uid) {
        Date createDate = accessTokenDao.getCreateDateByUid(uid);
        if(createDate==null) return true;
        long interval = new Date().getTime()-createDate.getTime();
        long twoHours = 2*60*60*1000;
        if(interval<twoHours){
            return false;
        }else{
            return true;
        }
    }
}
