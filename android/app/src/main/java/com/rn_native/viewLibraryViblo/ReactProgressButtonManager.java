package com.rn_native.viewLibraryViblo;

import android.util.Log;

import com.dd.CircularProgressButton;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nonnull;

// https://viblo.asia/p/hoc-react-native-tu-co-ban-den-nang-cao-phan-4-goi-native-ui-android-trong-react-native-RnB5pyYrKPG
// Làm theo hướng dãn của Viblo

public class ReactProgressButtonManager extends SimpleViewManager<CircularProgressButton> {
    private static final String TAG = "RNProgressButtonManager";

    public static final String REACT_CLASS = "RCTCircularProgressButton";

    @Nonnull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Nonnull
    @Override
    protected CircularProgressButton createViewInstance(@Nonnull ThemedReactContext reactContext) {
        Log.i(TAG, " createViewInstance");
        CircularProgressButton circularProgressButton = new CircularProgressButton(reactContext);
        return circularProgressButton;
    }

    // Các hàm sẽ chạy nếu mà nhận được Prop là text --> thay đổi text của native UI 
    @ReactProp(name = "text", customType = "")
    public void setText(CircularProgressButton circularProgressButton, String text) {
        Log.i(TAG, " setText "+text);
        circularProgressButton.setText(text);
    }

    @ReactProp(name = "completeText", customType = "")
    public void setCompleteText(CircularProgressButton circularProgressButton, String completeText) {
        Log.i(TAG, " setCompleteText "+completeText);
        circularProgressButton.setCompleteText(completeText);
    }

    @ReactProp(name = "errorText", customType = "")
    public void setErrorText(CircularProgressButton circularProgressButton, String errorText) {
        Log.i(TAG, " setErrorText "+errorText);
        circularProgressButton.setErrorText(errorText);
    }

    @ReactProp(name = "progress", defaultInt = 0)
    public void setProgress(CircularProgressButton circularProgressButton, int progress){
        Log.i(TAG, " setProgress "+progress);
        circularProgressButton.setProgress(progress);
    }

    @ReactProp(name = "idleText", customType = "")
    public void setIdleText(CircularProgressButton circularProgressButton, String idleText) {
        Log.i(TAG, " setIdleText "+idleText);
        circularProgressButton.setIdleText(idleText);
    }
}
