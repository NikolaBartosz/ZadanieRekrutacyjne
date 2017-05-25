package com.example.android.zadanierekrutacyjne;


import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private EditText address;
    private TextView warning;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button downloadButton = (Button) findViewById(R.id.download_button);
        txtResult = (TextView) findViewById(R.id.source_here);
        address = (EditText) findViewById(R.id.address);
        warning = (TextView) findViewById(R.id.warning);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((address.getText().toString()).length() == 0) {
                    warning.setVisibility(View.VISIBLE);
                    warning.setText(R.string.enter_address);
                } else {
                    warning.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    Ion.with(getApplicationContext())
                            .load(address.getText().toString())
                            .asString()
                            .setCallback(new FutureCallback<String>() {
                                @Override
                                public void onCompleted(Exception e, String result) {
                                    txtResult.setText(result);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                }
            }

        });
    }
}