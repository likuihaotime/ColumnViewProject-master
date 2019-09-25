/*
 * Copyright 2016 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2016-10-02 00:22:33
 *
 */

package com.lixiaodaoaaa.graphics;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gcssloop.graphics.R;

public class MainActivity2 extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final HistoGram histoGram = (HistoGram) findViewById(R.id.staticview);
        String[] data ={"100","20","40","20","80","20","60","30","5","20","60","30","5","5","20","60","30","5"};
        final String[] title = {"1","2","3","4","5","6","7","8","9","6","7","8","9","9","6","7","8","9"};

        histoGram.setNum(title.length);
        histoGram.setData(data);
        histoGram.setxTitleString(title);
        histoGram.setOnChartClickListener(new HistoGram.OnChartClickListener() {
            @Override
            public void onClick(int num, float x, float y, float value) {

                //显示提示窗
                View inflate = View.inflate(MainActivity2.this, R.layout.custom_marker_view, null);
                TextView  textView = (TextView) inflate.findViewById(R.id.tvContent);
                textView.setText(value + "%\n" + title[num - 1]);
                if(mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mPopupWindow = new PopupWindow(inflate,140, 60, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.showAsDropDown(histoGram,(int)(x - 65),(int)((- histoGram.getHeight()) + y - 65) );
            }
        });

    }
}
