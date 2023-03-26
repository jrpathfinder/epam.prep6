package com.epam.learn.prep6.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Created by maksym_govorischev on 14/03/14.
 */
@Value
@Builder
public class User {

    /**
     * com.epam.learn.assign1.com.epam.learn.prep6.model.User Id. UNIQUE.
     * @return com.epam.learn.assign1.com.epam.learn.prep6.model.User Id.
     */
    @Getter
    Long id;
    String name;
    /**
     * com.epam.learn.assign1.com.epam.learn.prep6.model.User email. UNIQUE.
     * @return com.epam.learn.assign1.com.epam.learn.prep6.model.User email.
     */
    String email;
}
