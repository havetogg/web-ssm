package com.havetogg.ssm.dao;

import com.havetogg.ssm.model.AccessToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/22.
 */
@Repository
public interface AccessTokenDao {
    void insertAccessToken(@Param("accessToken") AccessToken accessToken);

    AccessToken getAccessTokenByUid(@Param("uid") String uid);

    Date getCreateDateByUid(@Param("uid") String uid);
}
