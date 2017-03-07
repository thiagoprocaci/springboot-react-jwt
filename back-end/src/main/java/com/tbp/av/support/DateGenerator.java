package com.tbp.av.support;


import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateGenerator {

    public Date getExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, 1);
        return c.getTime();
    }

}
