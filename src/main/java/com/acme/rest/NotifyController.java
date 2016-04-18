package com.acme.rest;

import com.acme.service.EventService;
import com.acme.types.Event;
import com.acme.types.NotifyResponse;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private static Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, path = "/notify")
    public NotifyResponse notify(@RequestParam(value = "url") String url,
                                 @RequestHeader(value = "oauth_consumer_key", defaultValue = "") String requestConsumerKey,
                                 @RequestHeader(value = "oauth_signature", defaultValue = "") String requestSignature,
                                 @RequestHeader(value = "accountIdentifer", defaultValue = "") String accountIdentifer) {
        // TODO Inject value from property (?)
        final String consumerKey = "Dummy"; //"product-1-105571";
        final String secret = "secret"; //"CzHCBcYLAWMxpKJl";

        try {
            String callbackUrl = url;
            logger.info("Request Consumer Key: {}", requestConsumerKey);
            logger.info("Request Signature: {}", requestSignature);
            logger.info("Request Account Identifier: {}", accountIdentifer);

            RequestValidator requestValidator = new RequestValidator(url, requestSignature, consumerKey, secret, callbackUrl);
            if (!requestValidator.isValid()) {
                return new NotifyResponse(false, "101", "Cannot verify sender, signature mismatch", accountIdentifer);
            }

            callbackUrl = requestValidator.sign();
            RestTemplate restTemplate = new RestTemplate();
            Event event = restTemplate.getForObject(callbackUrl, Event.class);

            logger.info("Response from URL: %n {}", event.toString());
            logger.info("Writting Event to persistent store");
            event = eventService.save(event);
            logger.info("Generated ID: {}", event.getId());

        } catch (OAuthExpectationFailedException | OAuthMessageSignerException | OAuthCommunicationException e) {
            logger.error("Error during event processing: {}", e.getLocalizedMessage(), e);
            return new NotifyResponse(false, "100", "Error during event processing", accountIdentifer);
        }

        // Respond to caller
        return new NotifyResponse(true, null, null, accountIdentifer);
    }
}
