package com.and.classesTests;

import com.and.toastmodule.Notifications.SimpleNotification;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Administrador on 23/11/2017.
 */

public class notificationTests {

    @Test
    public void testSimpleNotification(){

        long[] vibrate = {0, 100, 200, 300};

        SimpleNotification notification = new SimpleNotification(vibrate, 10, "Show", "De bolas");

        assertArrayEquals("Os padrões devem ser iguais", vibrate, notification.getVibrate());
        assertEquals(10, notification.getId());
    }

}
