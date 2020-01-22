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
import com.mcal.decode.view.CenteredToolBar;
import java.util.Objects;

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
			output.setText(xorPlus(i, 0));
        } else if (v == decode1) {
            String i = input.getText().toString();
			output.setText(xorPlus(i, 1));
        } else if (v == decode2) {
            String i = input.getText().toString();
			output.setText(xorPlus(i, 2));
        } else if (v == decode3) {
            String i = input.getText().toString();
			output.setText(xorPlus(i, 3));
        } else if (v == decode4) {
            String i = input.getText().toString();
			output.setText(xorPlus(i, 4));
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
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
	
	public static String xorPlus(String str, int i) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i2 = 0; i2 < str.length(); i2++) {
				stringBuilder.append((char) (str.charAt(i2) ^ a(i)[i2 % a(i).length]));
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	public static char[] a(int i) {
		switch (i) {
			case 0:
				return new char[]{'鉝'};
			case 1:
				return new char[]{'々'};
			case 2:
				return new char[]{'〆'};
			case 3:
				return new char[]{'\u0670'};
			case 4:
				return new char[]{'ۖ'};
			default:
				return new char[0];
		}
	}
	
			
	public static String xorOld(String str) {
        char[] cArr = new char[]{'\u3005', '\u3006'};
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                stringBuilder.append((char) (str.charAt(i) ^ cArr[i % cArr.length]));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
