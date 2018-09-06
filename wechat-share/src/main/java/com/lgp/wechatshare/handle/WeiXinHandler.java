package com.lgp.wechatshare.handle;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/4/16 21:27
 * @DESCRIPTION
 **/
@Component
public class WeiXinHandler {
    private static Logger logger = LoggerFactory.getLogger(WeiXinHandler.class);

    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    protected RedisTemplate redisTemplate;

    private String snsapiUserinfo = "snsapi_userinfo";
    private String snsapiBase = "snsapi_base";
    private String refreshToken = "refresh_token";

    /**
     * 生成用于 获取 net服务器的授权跳转Url
     */
    private String getWeXinNetOuthRecallUrl(String netOuthUrl, String redirectUrl) {
        return netOuthUrl + redirectUrl;
    }

    /**
     * 生成用于获取 获取access_token的Code的Url
     */
    private String getWeXinCodeUrl(String appId, String netOuthRecallUrl, String scope) {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                appId, netOuthRecallUrl, scope, "code");
    }

    /**
     * 生成验证的url
     */
    public Map<String, String> getOuthCode(String netOuthUrl, String redirectUrl, String appId, String scope) {
        String outhUrl = this.getWeXinCodeUrl(appId, this.getWeXinNetOuthRecallUrl(netOuthUrl, redirectUrl), scope);
        Map<String, String> map = new HashMap<>();
        map.put("codeUrl", outhUrl);
        logger.info("WeiXinUtil.getOuthCode.map={}", JSON.toJSON(map));
        return map;
    }

    /**
     * 获取请求用户信息的access_token
     *
     * @return { "
     * access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     */
    public Map<String, String> getWeiXinAccessInfo(String appId, String appSecret, String code) throws Exception {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                appId, appSecret, code);
        logger.info("WeiXinUtil.getWeiXinAccessInfo.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            logger.info("WeiXinUtil.getWeiXinAccessInfo.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, Map.class);
        } catch (Exception e) {
            logger.info("WeiXinUtil.getWeiXinAccessInfo.e: {}", e.getMessage(), e);
            throw new Exception("获取请求用户信息的access_token error");
        }
    }

    /**
     * 获取用户信息
     * {
     * "openid":" OPENID",
     * " nickname": NICKNAME,
     * "sex":"1",
     * "province":"PROVINCE"
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     * 获取后直接放redis 1天
     */
    public Map<String, String> getWeiXinUserInfo(String accessToken, String openId) throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        logger.info("WeiXinUtil.getWeiXinUserInfo.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            logger.info("WeiXinUtil.getWeiXinUserInfo.result: {}", result);
            Map<String, String> map = new HashMap<>();
//            this.setUserInfoRedis(openId, result);
            map.put("result", result);
            return map;
        } catch (Exception e) {
            logger.info("WeiXinUtil.getWeiXinUserInfo.e: {}", e.getMessage(), e);
            throw new Exception("获取用户信息error");
        }
    }

//    /**
//     * 微信用户信息保存到redis，前端只需openid就能获得相应信息
//     * 这个老铁自己写吧
//     */
//    private void setUserInfoRedis(String openId, String result) {
//        String key = RedisKeys.buildRedisKeyByParams(WEIXIN_USER_INFO, openId);
//        redisTemplate.opsForValue().set(key, result, 1, TimeUnit.DAYS);
//    }
//
//    /**
//     * 获取微信用户信息 redis，前端只需传openid
//     *
//     * @return json的字符串
//     */
//    private String getUserInfoRedis(String openId) {
//        String key = RedisKeys.buildRedisKeyByParams(WEIXIN_USER_INFO, openId);
//        return (String) redisTemplate.opsForValue().get(key);
//    }

    public String getSnsapiUserinfo() {
        return snsapiUserinfo;
    }

    public String getSnsapiBase() {
        return snsapiBase;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

//--------------------------------以下的目前需求用不上

    /**
     * 刷新授权
     * grantType 默认为refresh_token
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     * 返回的新token于旧token不同
     */
    public Map<String, String> refreshAcessToken(String accessToken, String openId, String grantType) throws Exception {
        String url = "ttps://api.weixin.qq.com/sns/oauth2/refresh_token?appid==" + openId + "&grant_type=" + grantType + "&refresh_token=" + accessToken;
        logger.info("WeiXinUtil.refreshAcessToken.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            logger.info("WeiXinUtil.refreshAcessToken.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, Map.class);
        } catch (Exception e) {
            logger.info("WeiXinUtil.refreshAcessToken.e: {}", e.getMessage(), e);
            throw new Exception("刷新授权 error");
        }
    }


    /**
     * 检验授权凭证
     * <p>
     * 理论上
     * 取accessToken后，不马上获取用户信息，那么之后获取用户信息应该调用该方法校验才能获取用户信息
     * 微信这隐私管理强吧
     * <p>
     * 智障，我们可以一开始就获取微信的用户信息丢缓存里面。
     */
    public Boolean auth(String accessToken, String openId) throws Exception {
        Map<String, String> data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/auth?access_token=" + accessToken + "&openid=" + openId;
        logger.info("WeiXinUtil.auth.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
            logger.info("WeiXinUtil.auth.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(result, Map.class);
            if ("ok".equals(data.get("errmsg"))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.info("WeiXinUtil.auth.e: {}", e.getMessage(), e);
            throw new Exception("accessToken和openId已经过期");
        }
    }
}
