package com.example.branko.lab_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.string.cancel;
import static android.telephony.PhoneNumberUtils.WAIT;

/**
 * Created by Branko on 22.10.2017.
 */

public class ExplicitActivity extends Activity {
    static final int WAIT_TEXT_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explicit_activity);

        final EditText editText = (EditText) findViewById(R.id.text_input);

        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Button okay = (Button) findViewById(R.id.okay_button);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("TextValue",message);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
