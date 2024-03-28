package com.rn_native.BadgeModule;


        import com.facebook.react.ReactPackage;
        import com.facebook.react.bridge.NativeModule;
        import com.facebook.react.bridge.ReactApplicationContext;
        import com.facebook.react.uimanager.ViewManager;
        import com.rn_native.module_doc.CalendarModule;


        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class BadgePackage implements ReactPackage {

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

        return Arrays.<ViewManager>asList();
    }

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new BadgeModule(reactContext));

        return modules;
    }

}