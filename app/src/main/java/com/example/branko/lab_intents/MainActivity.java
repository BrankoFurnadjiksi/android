package com.example.branko.lab_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {
    static final int WAIT_TEXT_REQUEST = 1;
    static final String TITLE = " MPiP Send Title";
    static final String CONTENT = "Content send from MainActivity";
    static final int PICK_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button explicit = (Button) findViewById(R.id.explicit_button);
        explicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ExplicitActivity.class);
                startActivityForResult(i, WAIT_TEXT_REQUEST);
            }
        });

        Button implicit = (Button) findViewById(R.id.implicit_button);
        implicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("mk.ukim.finki.mpip.IMPLICIT_ACTION");
                startActivity(i);
            }
        });

        Button send = (Button) findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();

                sendIntent.setAction(ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TITLE, TITLE);
                sendIntent.putExtra(Intent.EXTRA_TEXT, CONTENT);
                sendIntent.setType("text/plain");

                startActivity(sendIntent);
            }
        });

        Button picture = (Button) findViewById(R.id.picture_button);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == WAIT_TEXT_REQUEST) {
            if(resultCode == RESULT_OK) {
                String text = data.getExtras().getString("TextValue");
                TextView textView = (TextView) findViewById(R.id.text);
                textView.setText(text);
            }
        }
        if(requestCode == PICK_IMAGE) {
            if(resultCode == RESULT_OK) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(data.getData(),"image/*");
                startActivity(intent);
            }
        }
    }
}
