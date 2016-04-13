package com.acme.rest;

import com.acme.rest.types.NotifyResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bdraraujo on 16-04-12.
 */
@RestController
public class NotifyController {

    @RequestMapping(method = RequestMethod.GET, path = "/notify")
    public NotifyResponse notify(@RequestParam(value = "url") String url) {
        return new NotifyResponse(false, "xyz", "Test error", null);
    }
}
