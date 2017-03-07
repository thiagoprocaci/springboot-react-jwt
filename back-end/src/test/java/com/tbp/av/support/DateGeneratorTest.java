package com.tbp.av.support;


import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class DateGeneratorTest {

    @Test
    public void testExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, 1);

        DateGenerator dateGenerator = new DateGenerator();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(dateGenerator.getExpirationDate());

        assertEquals(c.get(Calendar.DAY_OF_WEEK), c2.get(Calendar.DAY_OF_WEEK));
        assertEquals(c.get(Calendar.DAY_OF_MONTH), c2.get(Calendar.DAY_OF_MONTH));
        assertEquals(c.get(Calendar.MONTH), c2.get(Calendar.MONTH));
        assertEquals(c.get(Calendar.YEAR), c2.get(Calendar.YEAR));
        assertEquals(c.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.HOUR_OF_DAY));
    }


}
