package com.epam.learn.prep6.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
@Builder
@Value
@Accessors(chain = true)
public class Event {
    /**
     * com.epam.learn.assign1.com.epam.learn.prep6.model.Event id. UNIQUE.
     * @return com.epam.learn.assign1.com.epam.learn.prep6.model.Event Id
     */
    @Getter
    Long id;
    String title;
    Date dateOld;
    OffsetDateTime date;
}
