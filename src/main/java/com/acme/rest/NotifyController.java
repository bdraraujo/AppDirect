package com.acme.rest;

import com.acme.types.NotifyResponse;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bdraraujo on 16-04-12.
 */
@RestController
public class NotifyController {
    private static Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/notify")
    public NotifyResponse notify(@RequestParam(value = "url") String url,
                                 @RequestHeader(value = "oauth_consumer_key", defaultValue = "") String requestConsumerKey,
                                 @RequestHeader(value = "oauth_signature", defaultValue = "") String requestSignature) {
        // TODO Inject value from property (?)
        final String consumerKey = "Dummy"; //"product-1-105571";
        final String secret = "secret"; //"CzHCBcYLAWMxpKJl";

        try {
            logger.info("Request Consumer Key: {}%nRequest Signature: {}", requestConsumerKey, requestSignature);
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
            consumer.setSigningStrategy(new QueryStringSigningStrategy());
            String signedUrl = consumer.sign(url);
            logger.info("Signed URL {}", signedUrl);
            //System.out.println(signedUrl);
            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForObject(signedUrl, String.class, (Object) null);
            logger.info("Response from URL:%n{}", response);
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
