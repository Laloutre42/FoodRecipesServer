/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zed.foodrecipes.signup;

import com.zed.foodrecipes.model.User;
import com.zed.foodrecipes.service.RedirectViewService;
import com.zed.foodrecipes.service.UserService;
import com.zed.foodrecipes.signin.SignInUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class SignupController {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedirectViewService redirectViewService;

    private ProviderSignInUtils providerSignInUtils;

    @Inject
    public SignupController(
            ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository connectionRepository) {
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public RedirectView redirectRequestToRegistrationPage(HttpServletResponse httpServletResponse) {
        return redirectViewService.createApplicationRedirectView("/signup");
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.GET)
    public SignupForm signupForm(WebRequest request) {

        logger.info("[signupForm] Sign up");

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            logger.info("[signupForm] A connection has been found, return a prefill signUp form");
            request.setAttribute("message", "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up.", WebRequest.SCOPE_REQUEST);
            return SignupForm.fromProviderUser(connection.fetchUserProfile());
        } else {
            logger.info("[signupForm] No connection found, return an empty signUp form");
            return new SignupForm();
        }
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public RedirectView signup(@Valid @RequestBody SignupForm form, BindingResult formBinding, WebRequest request) {

        if (formBinding.hasErrors()) {
            logger.error("[signup] formBinding has Errors");
            return null;
        }

        // Account local sans password => error
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection == null && form.getPassword() == null) {
            logger.error("[signupForm] Local sign Up with no password");
            return null;
        }

        if (connection.getKey() == null || connection.getKey().getProviderId() == null) {
            logger.error("[signupForm] connection.getKey().getProviderId() is null");
            return null;
        }

        User user = createUser(form, formBinding, connection.getKey().getProviderId());
        logger.info("[signup] Create User {}", user);

        if (user != null) {
            SignInUtils.signin(user.getUsername());
            providerSignInUtils.doPostSignUp(user.getUsername(), request);
            return redirectViewService.createApplicationMainPageRedirectView();
        }
        return null;
    }

    // internal helpers

    private User createUser(SignupForm form, BindingResult formBinding, String connectionProviderId) {
        User user = new User(form.getName(), form.getFullName(), form.getEmail(), connectionProviderId);
        return userService.createUser(user);
    }

}
