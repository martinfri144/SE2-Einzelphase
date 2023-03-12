package com.se2einzelphase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    EditText mtn;
    TextView showReplyServer;
    TextView showPrimzahlen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonSRVClick(View v) {
        mtn = findViewById(R.id.editTextNumberMtn);
        showReplyServer = findViewById(R.id.textViewReplyServer);

        String mtnToSrv = mtn.getText().toString();
        ConnectionThread c = new ConnectionThread(mtnToSrv);
        c.start();
        try {
            c.join();
        } catch (InterruptedException ie) {
            showReplyServer.setText("fehler");
        }

        showReplyServer.setText(c.returnMtn());

    }

    public void buttonPrimzahlen(View v) {
        mtn = findViewById(R.id.editTextNumberMtn);
        showPrimzahlen = findViewById(R.id.textViewPrimzahlen);
        String mtnString = mtn.getText().toString();


        int mtnNumber = Integer.parseInt(mtnString);
        int length = mtn.length();

        ArrayList<Integer> liste = new ArrayList<Integer>();


        for (int i = length; i > 0; i--) {
            if ((mtnNumber % 10) == 2 || (mtnNumber % 10) == 3)
            {
                liste.add(0, mtnNumber % 10);

            } else if ((mtnNumber % 10) >= 2 && (mtnNumber % 10) % 2 != 0 && (mtnNumber % 10) % 3 != 0) {
                liste.add(0, mtnNumber % 10);
            }

            mtnNumber = mtnNumber / 10;
            length--;
        }
        showPrimzahlen.setText(liste.toString());
    }
}