/*
 * Copyright (C) 2018 CyberAgent, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage.sample.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import jp.co.cyberagent.android.gpuimage.sample.R;

public class ActivityMain extends AppCompatActivity implements OnClickListener {

    private static final int REQUEST_CAMERA = 1;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_gallery).setOnClickListener(this);
        findViewById(R.id.button_camera).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_gallery:
                startActivity(new Intent(this, ActivityGallery.class));
                break;

            case R.id.button_camera:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA);
                } else {
                    startActivity(new Intent(this, ActivityCamera.class));
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA &&
                grantResults.length != 1 ||
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, ActivityCamera.class));
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}