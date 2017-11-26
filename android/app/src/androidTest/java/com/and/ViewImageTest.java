package com.and;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Administrador on 25/11/2017.
 */

public class ViewImageTest {

    @Test
    public void viewImageLaunched(){
        onView(withId(R.id.imagemDownload)).check(matches(isDisplayed()));
    }

}
