package com.and;

import com.and.classesTests.notificationTests;
import com.and.classesTests.testIntents;
import com.and.toastmodule.Intents.IntentCall;
import com.and.toastmodule.Notifications.SimpleNotification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by Administrador on 22/11/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        notificationTests.class,
        testIntents.class
})

public class TestRNMA {

}
