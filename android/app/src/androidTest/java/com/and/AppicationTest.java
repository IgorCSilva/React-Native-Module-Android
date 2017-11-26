package com.and;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.and.toastmodule.DownloadViewActivity;
import com.and.toastmodule.ViewImage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Administrador on 25/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AppicationTest {

    @Rule
    public ActivityTestRule<DownloadViewActivity>
        imageActivityTestRule = new ActivityTestRule<DownloadViewActivity>(DownloadViewActivity.class, true, false);


    @Test
    public void viewImageLaunched(){

        onView(withId(R.id.imagemDownload)).check(matches(isDisplayed()));
    }
}
