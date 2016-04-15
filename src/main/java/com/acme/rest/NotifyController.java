package com.acme.rest;

import com.acme.types.NotifyResponse;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by bdraraujo on 16-04-12.
 */
@RestController
public class NotifyController {

    @RequestMapping(method = RequestMethod.GET, path = "/notify")
    public NotifyResponse notify(@RequestParam(value = "url") String url) {
        final String key = "product-1-105455";
        // lPcbzdKCj8CJ5ZN9
        // TODO Call AppDirect with signed URL to get XML back
        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = "lPcbzdKCj8CJ5ZN9";

        //URL destUrl = null;
        try {
            //destUrl = new URL(url);
            //HttpURLConnection request = (HttpURLConnection) destUrl.openConnection();
            String signature = signer.computeSignature(url); //&oauth_nonce=95009478
            String template = "&oauth_timestamp=%d&oauth_consumer_key=%s&oauth_signature_method=HMAC-SHA1&oauth_version=1.0&oauth_signature=%s";
            String finalUrl = url + String.format(template, new Timestamp((new Date()).getTime()).getTime(), key, signature);
            System.out.println(finalUrl);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(finalUrl, String.class, (Object) null);
            System.out.println(response);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        // TODO Print XML
        // Respond to caller
        return new NotifyResponse(false, "xyz", "Test error", null);
    }
}
