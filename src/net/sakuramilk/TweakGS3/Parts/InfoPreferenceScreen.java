/*
 * Copyright (C) 2012 sakuramilk <c.sakuramilk@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sakuramilk.TweakGS3.Parts;

import net.sakuramilk.TweakGS3.R;
import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoPreferenceScreen extends Preference {

    private TextView mTextView;
    private String mInfo;

    public InfoPreferenceScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfoPreferenceScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
    	LayoutInflater inflater =  (LayoutInflater)getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
    	View view = inflater.inflate(R.layout.preference_info, parent, false);
    	mTextView = (TextView)view.findViewById(R.id.info);
    	mTextView.setText(mInfo);
        return view;
    }

    public void setInfo(String info) {
        if (mTextView != null) {
        	mTextView.setText(info);
        }
        mInfo = info;
    }

    public String getInfo() {
        return mInfo;
    }
}
