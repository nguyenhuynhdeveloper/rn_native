
package com.rn_native.fragmentView_doc;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;


import java.util.ArrayList;

import javax.annotation.Nonnull;


public class MyPackage_FragmentViewDoc implements ReactPackage {

    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        return new ArrayList<>();
    }

    @Override
   public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new MyViewManager(reactContext)
        );
   }

}