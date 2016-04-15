package com.zed.foodrecipes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Singleton;

/**
 * Created by Arnaud on 13/03/2016.
 */
@Singleton
@Service
public class RedirectViewService {

    private static final Logger logger = LoggerFactory.getLogger(RedirectViewService.class);
    @Value("${application.url}")
    private String applicationUrl;

    public RedirectView createApplicationRedirectView(String url) {
        String redirectUrl = applicationUrl + url;
        logger.info("[redirectRequestToRegistrationPage] Redirect to {}", redirectUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }

    public RedirectView createApplicationMainPageRedirectView() {
        String redirectUrl = applicationUrl;
        logger.info("[redirectRequestToRegistrationPage] Redirect to {}", redirectUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }
}
