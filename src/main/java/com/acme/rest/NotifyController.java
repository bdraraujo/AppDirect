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
        final String consumerKey = "product-1-105571";
        final String secret = "CzHCBcYLAWMxpKJl";
        // lPcbzdKCj8CJ5ZN9
        // TODO Call AppDirect with signed URL to get XML back
        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = "CzHCBcYLAWMxpKJl";

        //URL destUrl = null;
        try {
            //destUrl = new URL(url);
            //HttpURLConnection request = (HttpURLConnection) destUrl.openConnection();
            /*String signature = signer.computeSignature(url);
            //String template = "&oauth_timestamp=%d&oauth_consumer_key=%s&oauth_signature_method=HMAC-SHA1&oauth_version=1.0&oauth_signature=%s";
            OAuthParameters params = new OAuthParameters();
            params.setOAuthConsumerKey(consumerKey);
            params.setOAuthNonce(OAuthUtil.getNonce());
            params.setOAuthTimestamp(OAuthUtil.getTimestamp());
            params.setOAuthSignatureMethod(signer.getSignatureMethod());
            params.setOAuthType(OAuthParameters.OAuthType.TWO_LEGGED_OAUTH);
            params.setOAuthSignature(signature);
            params.addCustomBaseParameter("oauth_version", "1.0");

            //signer.setPrivateKey(privKey);
            //String finalUrl = url + String.format(template, new Timestamp((new Date()).getTime()).getTime(), key, signature);
            String baseString = OAuthUtil.encode(OAuthUtil.normalizeUrl(url)) + '&'
                    + OAuthUtil.encode(OAuthUtil.normalizeParameters(url, params.getBaseParameters()));*/
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
