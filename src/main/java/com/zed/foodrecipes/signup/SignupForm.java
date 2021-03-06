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

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

import javax.validation.constraints.Size;

@Data
public class SignupForm {

    @NotEmpty
    private String name;

    @Size(min = 6, message = "must be at least 6 characters")
    private String password;

    @NotEmpty
    private String email;

    @NotEmpty
    private String fullName;

    public static SignupForm fromProviderUser(UserProfile providerUser) {
        SignupForm form = new SignupForm();
        form.setName(providerUser.getUsername());
        form.setFullName(providerUser.getName());
        form.setEmail(providerUser.getEmail());
        return form;
    }
}
