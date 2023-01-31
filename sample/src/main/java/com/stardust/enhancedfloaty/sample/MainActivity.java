package com.stardust.enhancedfloaty.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.stardust.enhancedfloaty.FloatyService;
import com.stardust.enhancedfloaty.R;
import com.stardust.enhancedfloaty.ResizableExpandableFloatyWindow;
import com.stardust.enhancedfloaty.ResizableFloatyWindow;
import com.stardust.enhancedfloaty.util.FloatingWindowPermissionUtil;


/**
 * Created by Stardust on 2017/4/18.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingWindowPermissionUtil.goToFloatingWindowPermissionSettingIfNeeded(this);
        startService(new Intent(this, FloatyService.class));
        findViewById(R.id.resizable_floaty).setOnClickListener(v -> FloatyService.addWindow(new ResizableFloatyWindow(new SampleFloaty())));
        findViewById(R.id.expandable_floaty).setOnClickListener(v -> FloatyService.addWindow(new ResizableExpandableFloatyWindow(new SampleExpandableFloaty())));
    }

}
