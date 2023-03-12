package com.se2einzelphase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mtn;
    TextView showReplyServer;
    TextView showPrimzahlen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonSRVClick (View v) {
        mtn = findViewById(R.id.editTextNumberMtn);
        showReplyServer = findViewById(R.id.textViewReplyServer);

        String mtnToSrv = mtn.getText().toString();
        ConnectionThread c = new ConnectionThread(mtnToSrv);
        c.start();
        try {
            c.join();
        }
        catch (InterruptedException ie) {
            showReplyServer.setText("fehler");
        }

        showReplyServer.setText(c.returnMtn());

    }

    public void buttonPrimzahlen (View v) {
        mtn = findViewById(R.id.editTextNumberMtn);
        showPrimzahlen = findViewById(R.id.textViewPrimzahlen);
        String mtnString = mtn.getText().toString();
    }
}