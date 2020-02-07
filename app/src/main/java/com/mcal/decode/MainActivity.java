/**
 * Copyright 2020 Иван Тимашков. All Rights Reserved.
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
package com.mcal.decode;

import android.annotation.SuppressLint;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import com.mcal.decode.data.Preferences;
import com.mcal.decode.utils.Utils;
import com.mcal.decode.view.CenteredToolBar;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
	private CenteredToolBar toolbar;
	private AppCompatEditText input, output;
	private AppCompatButton decode, decode1, decode2, decode3, decode4;
	private AppCompatImageButton clearInput, clearOutput, copyInput, copyOutput, pasteInput, pasteOutput;
	private ClipboardManager myClipboard;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupToolbar(getString(R.string.app_name));
		output = findViewById(R.id.outString);
		input = findViewById(R.id.decruptString);
		decode = findViewById(R.id.btn_decode);
		decode.setOnClickListener(this);
		decode1 = findViewById(R.id.btn_decode1);
		decode1.setOnClickListener(this);
		decode2 = findViewById(R.id.btn_decode2);
		decode2.setOnClickListener(this);
		decode3 = findViewById(R.id.btn_decode3);
		decode3.setOnClickListener(this);
		decode4 = findViewById(R.id.btn_decode4);
		decode4.setOnClickListener(this);
		clearInput = findViewById(R.id.clear_input);
		clearInput.setOnClickListener(this);
		clearOutput = findViewById(R.id.clear_output);
		clearOutput.setOnClickListener(this);
		copyInput = findViewById(R.id.copy_input);
		copyInput.setOnClickListener(this);
		copyOutput = findViewById(R.id.copy_output);
		copyOutput.setOnClickListener(this);
		pasteInput = findViewById(R.id.paste_input);
		pasteInput.setOnClickListener(this);
		pasteOutput = findViewById(R.id.paste_output);
		pasteOutput.setOnClickListener(this);
		myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    }
	
	public void onClick(View v) {
        if (v == decode) {
            String i = input.getText().toString();
			output.setText(Utils.xorPlus(i, 0));
        } else if (v == decode1) {
            String i = input.getText().toString();
			output.setText(Utils.xorPlus(i, 1));
        } else if (v == decode2) {
            String i = input.getText().toString();
			output.setText(Utils.xorPlus(i, 2));
        } else if (v == decode3) {
            String i = input.getText().toString();
			output.setText(Utils.xorPlus(i, 3));
        } else if (v == decode4) {
            String i = input.getText().toString();
			output.setText(Utils.xorPlus(i, 4));
        } else if (v == pasteInput) {
            // If it does contain data, decide if you can handle the data.
			if (!(myClipboard.hasPrimaryClip())) {
			} else if (!(myClipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
				// since the clipboard has data but it is not plain text
			} else {
				input.setText(myClipboard.getText().toString());
			}
        } else if (v == pasteOutput) {
            // If it does contain data, decide if you can handle the data.
			if (!(myClipboard.hasPrimaryClip())) {
			} else if (!(myClipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
				// since the clipboard has data but it is not plain text
			} else {
				output.setText(myClipboard.getText().toString());
			}
        } else if (v == copyInput) {
			myClipboard.setText(input.getText().toString());
			Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
        } else if (v == copyOutput) {
            myClipboard.setText(output.getText().toString());
			Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
        } else if (v == clearInput) {
			input.setText("");
        } else if (v == clearOutput) {
			output.setText("");
        }
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            if (Preferences.isNightModeEnabled()) {
                Preferences.setNightModeEnabled(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                getDelegate().applyDayNight();
            } else {
                Preferences.setNightModeEnabled(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                getDelegate().applyDayNight();
            }
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void setupToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
