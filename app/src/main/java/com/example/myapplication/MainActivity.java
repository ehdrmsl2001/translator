package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button bt;
    Translator translator;
    Weather weather;
    String[] oldData = {"o1", "o2", "o3"}, newData = {"n1", "n2", "n3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.text);
        bt = findViewById(R.id.button);
        et = findViewById(R.id.et);
        translator = new Translator();
        weather = new Weather();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString().trim();
                String city = translator.translation(text);
                newData = weather.callWeatherData(city);
                if (newData != null && !Arrays.equals(oldData, newData)) {
                    tv.setText(text + " " + newData[1].toString() +  " " + newData[2].toString());
                    oldData = newData;
                }
            }
        });
    }
}
