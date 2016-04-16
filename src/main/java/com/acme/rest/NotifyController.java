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
            String callbackUrl = url;
            logger.info("Request Consumer Key: {} %nRequest Signature: {}", requestConsumerKey, requestSignature);
            if (url.matches("https:\\/\\/.*-test\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
                logger.info("AppDirect Test URL, retrieving and responding");


            } else if (url.matches("https:\\/\\/.*\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
                logger.info("AppDirect Production URL, validating OAuth, retrieving and responding");
                // TODO Validate OAuth
                OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
                consumer.setSigningStrategy(new QueryStringSigningStrategy());
                callbackUrl = consumer.sign(url);
                logger.info("Signed URL {}", callbackUrl);

            }
            RestTemplate restTemplate = new RestTemplate();

            String callbackResponse = restTemplate.getForObject(callbackUrl, String.class, (Object) null);
            logger.info("Response from URL:%n{}", callbackResponse);
        } catch (OAuthExpectationFailedException | OAuthMessageSignerException | OAuthCommunicationException e) {
            logger.error("Error during event processing: {}", e.getLocalizedMessage(), e);
            return new NotifyResponse(false, "100", "Error during event processing", null);
        }

        // TODO Print XML
        // Respond to caller
        return new NotifyResponse(true, null, null, null);
    }
}
