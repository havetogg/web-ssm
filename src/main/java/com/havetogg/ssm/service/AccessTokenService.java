package com.havetogg.ssm.service;

import com.havetogg.ssm.model.AccessToken;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
public interface AccessTokenService {
    void insertAccessToken(AccessToken accessToken);

    AccessToken getAccessTokenByUid(String uid);

    boolean idExpire(String uid);
}
