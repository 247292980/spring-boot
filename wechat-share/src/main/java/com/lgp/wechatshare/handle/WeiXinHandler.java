package com.lgp.wechatshare.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgp.wechatshare.constant.WeiXinInfo;
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
    @Autowired
    private WeiXinInfo weiXinInfo;

    /**
     * 生成用于获取access_token的Code的Url
     *
     * @param redirectUrl
     * @return
     */
    public String getRequestCodeUrl(String redirectUrl, String scope) {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
                weiXinInfo.getAppID(), redirectUrl, scope, "code");
    }

    /**
     * 获取请求用户信息的授权信息
     *
     * @param code
     * @return { "
     * access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     */
    public Map<String, String> getWeiXinAccessInfo(String code) throws Exception {
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                weiXinInfo.getAppID(), weiXinInfo.getAppSecret(), code);
        logger.info("WeiXinClient.getWeiXinAccessInfo.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            logger.info("WeiXinClient.getWeiXinAccessInfo.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, Map.class);
        } catch (NullPointerException e) {
            throw new NullPointerException("code 已被使用" + code);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getWeiXinOpenId(String code) throws Exception {
        Map<String, String> data = getWeiXinAccessInfo(code);
        return data.get("openid");
    }

    public String getAccessToken(String code) throws Exception {
        Map<String, String> data = getWeiXinAccessInfo(code);
        return data.get("access_token");
    }

    /**
     * 刷新token
     * 返回的新token和旧token
     * 不同超时
     * 相同延时
     *
     * grantType 默认为refresh_token
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     */
    public Map<String, String> refreshAcessToken(String accessToken, String openId, String grantType) throws Exception {
        String url = "ttps://api.weixin.qq.com/sns/oauth2/refresh_token?appid==" + openId + "&grant_type=" + grantType + "&refresh_token=" + accessToken;
        logger.info("WeiXinClient.refreshAcessToken.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            logger.info("WeiXinClient.refreshAcessToken.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, Map.class);
        } catch (NullPointerException e) {
            throw new NullPointerException("accessToken和openId已经过期");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取用户详细信息
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
     */
    public Map<String, String> getUserInfo(String accessToken, String openId) throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        logger.info("WeiXinClient.getUserInfo.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            logger.info("WeiXinClient.getUserInfo.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, Map.class);
        } catch (NullPointerException e) {
            throw new NullPointerException("accessToken和openId已经过期");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 检验授权凭证，测试这个accessToken和openId是否匹配
     */
    public Boolean auth(String accessToken, String openId) throws Exception {
        Map<String, String> data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/auth?access_token=" + accessToken + "&openid=" + openId;
        logger.info("WeiXinClient.auth.url: {}", url);
        try {
            String result = restTemplate.getForObject(url, String.class);
            logger.info("WeiXinClient.auth.result: {}", result);
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(result, Map.class);
            if ("ok".equals(data.get("errmsg"))) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            throw new NullPointerException("accessToken和openId已经过期");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
