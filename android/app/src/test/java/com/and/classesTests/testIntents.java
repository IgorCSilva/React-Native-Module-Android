package com.and.classesTests;

import com.and.toastmodule.Intents.IntentCall;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrador on 23/11/2017.
 */

public class testIntents {

    @Test
    public void testLengthNumber(){
        IntentCall call = new IntentCall("81995474541");

        assertEquals(11, call.getLengthNumber());
    }
}
