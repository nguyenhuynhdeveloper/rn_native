package com.rn_native.fragmentviewDoc;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

// https://reactnative.dev/docs/native-components-android#integration-with-an-android-fragment-example
// Làm theo hướng dẫn của DOC để có thể Đưa 1 View Fragment Android vào view React Native

// Tạo 1 cái Custom View kế thừa từ FrameLayout : Đây là 1 View Fragment được vẽ lên bằng code
public class CustomView extends FrameLayout {
  public CustomView(@NonNull Context context) {
    super(context);
    // Set padding and background color
    this.setPadding(16,16,16,16);
    this.setBackgroundColor(Color.parseColor("#5FD3F3"));

    // Add default text view
    TextView text = new TextView(context);
    text.setText("Welcome to Android Fragments with React Native.");
    this.addView(text);
  }
}