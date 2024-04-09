package com.rn_native.view_doc;



import android.content.Context;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

// Phần phục vụ cho Evnet : khi thao tác thu , cuộn , phóng bên android này sẽ thông báo cho bên JS biết để sử lý 
// Custom lại lắng nghe các thao tác

class MyCustomView extends View {
    public MyCustomView(Context context) {
        super(context);
    }

    //   ...
    public void onReceiveNativeEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("message", "MyMessage");
        ReactContext reactContext = (ReactContext)getContext();
        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "topChange", event);
    }


}

