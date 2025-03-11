package com.rn_native.customTextView;

import android.graphics.Color;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class CustomTextViewManager extends SimpleViewManager<TextView> {

    public static final String REACT_CLASS = "CustomTextView"; // Tên sẽ dùng trong JS

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TextView createViewInstance(@NonNull ThemedReactContext reactContext) {
        TextView textView = new TextView(reactContext);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    @ReactProp(name = "text") // Thuộc tính có thể nhận từ React Native
    public void setText(TextView view, String text) {
        view.setText(text);
    }
}
