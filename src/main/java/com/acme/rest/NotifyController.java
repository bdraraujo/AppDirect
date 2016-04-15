package com.acme.rest;

import com.acme.types.NotifyResponse;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bdraraujo on 16-04-12.
 */
@RestController
public class NotifyController {

    @RequestMapping(method = RequestMethod.GET, path = "/notify")
    public NotifyResponse notify(@RequestParam(value = "url") String url) {
        final String consumerKey = "Dummy"; //"product-1-105571";
        final String secret = "secret"; //"CzHCBcYLAWMxpKJl";

        // TODO Call AppDirect with signed URL to get XML back
        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = secret;

        try {
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
            consumer.setSigningStrategy(new QueryStringSigningStrategy());
            String signedUrl = consumer.sign(url);
            System.out.println(signedUrl);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(signedUrl, String.class, (Object) null);
            System.out.println(response);
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        // TODO Print XML
        // Respond to caller
        return new NotifyResponse(false, "xyz", "Test error", null);
    }
}
