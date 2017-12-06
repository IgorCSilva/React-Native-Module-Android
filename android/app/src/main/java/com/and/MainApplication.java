package com.and;

import android.app.Application;
import android.widget.Toast;

import com.facebook.react.ReactApplication;
import com.jamesisaac.rnbackgroundtask.BackgroundTaskPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

import com.dieam.reactnativepushnotification.ReactNativePushNotificationPackage;  // <--- Import Package

import com.and.toastmodule.ToastPackage;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new BackgroundTaskPackage(),
            new BackgroundTimerPackage(),
          new ToastPackage(),
          new  ReactNativePushNotificationPackage()

      );
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
      super.onCreate();

      BackgroundTaskPackage.useContext(this);

      SoLoader.init(this, /* native exopackage */ false);

      Toast.makeText(this, "onCreate - MainApplication", Toast.LENGTH_SHORT).show();
  }

}
