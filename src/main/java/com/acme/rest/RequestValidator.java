package com.acme.rest;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bdraraujo on 16-04-15.
 */
public class RequestValidator {
    private static Logger logger = LoggerFactory.getLogger(RequestValidator.class);

    private boolean myResult;
    private String url;
    private String requestSignature;
    private String consumerKey;
    private String secret;
    private String callbackUrl;

    public RequestValidator(String url, String requestSignature, String consumerKey,
                            String secret, String callbackUrl) {
        this.url = url;
        this.requestSignature = requestSignature;
        this.consumerKey = consumerKey;
        this.secret = secret;
        this.callbackUrl = callbackUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public boolean isValid() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
        myResult = true;

        if (url.matches("https:\\/\\/.*-test\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
            logger.info("AppDirect Test URL, retrieving and responding");
        } else if (url.matches("https:\\/\\/.*\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
            logger.info("AppDirect Production URL, validating OAuth, retrieving and responding");

            // Validate OAuth
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
            consumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
            String signatureForVerification = consumer.sign(url);
            if (!signatureForVerification.equals(requestSignature)) {
                logger.error("Cannot verify sender, signature mismatch");
                myResult = false;
            }
        }

        return myResult;
    }

    public String sign() throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String signedUrl;
        if (url.matches("https:\\/\\/.*-test\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
            logger.info("AppDirect Test URL, returning original URL");
            signedUrl = url;
        } else if (url.matches("https:\\/\\/.*\\.byappdirect\\.com\\/api\\/integration\\/v1\\/events\\/dummyOrder")) {
            logger.info("AppDirect Production URL, signing and returning");
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
            consumer.setSigningStrategy(new QueryStringSigningStrategy());
            signedUrl = consumer.sign(url);
        } else {
            logger.info("Unrecognized origin, returning blank URL");
            signedUrl = "";
        }
        logger.info("Signed URL: {}", signedUrl);
        return signedUrl;
    }

}
