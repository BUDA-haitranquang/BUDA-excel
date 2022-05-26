package com.buda.excel.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class RequestResolver {
    @Value("${authentication.url}")
    private String authenticationURL;
    public Long getUserIDGeneral(HttpServletRequest httpServletRequest, String type) {
        final String token = httpServletRequest.getHeader("Authorization").substring(7);
        HttpPost httpPost = new HttpPost(authenticationURL + "api/token/verify/" + type);
        HttpClient client = HttpClients.createDefault();
        String json = "{\"accessToken\":" + "\"" + token + "\"" + "}";
        StringEntity entity;
        entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        try {
            HttpResponse response = client.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity));
            return jsonObject.getLong("userID");
        } catch (Exception e) {

        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

    }

    public Long getUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "normal");
    }

    public Long getProUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "pro");
    }

    public Long getPremiumUserIDFromUserToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "premium");
    }

    // on staff token
    public Long getStaffIDFromStaffToken(HttpServletRequest httpServletRequest) {
        return getUserIDGeneral(httpServletRequest, "staff");
    }

}
