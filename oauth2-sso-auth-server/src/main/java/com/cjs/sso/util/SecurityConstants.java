package com.cjs.sso.util;

/**
 * 权限相关通用常量
 * 
 * @author ruoyi
 * @author:heshengjin qq:2356899074
 */
public class SecurityConstants
{
    /**
     * 令牌类型
     */
    public static final String BEARER_TOKEN_TYPE = "Bearer";

    /**
     * 授权token url
     */
    public static final String AUTH_TOKEN = "/oauth/token";

    /**
     * 注销token url
     */
    public static final String TOKEN_LOGOUT = "/token/logout";


    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    public static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from oauth_client_details";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";
}
