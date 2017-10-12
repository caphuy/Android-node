package com.caphuy.smartclassroom;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.squareup.picasso.Picasso;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MqttCallback {

    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleImageView = new CircleImageView(this);
        circleImageView.setBackgroundResource(R.drawable.background_color_2);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(getApplicationContext())
                        .load("https://1.soompi.io/wp-content/uploads/2015/02/shinsaekyung1.jpg").into(circleImageView);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        relativeLayout.addView(circleImageView);
        Picasso.with(getApplicationContext())
                .load("https://0.soompi.io/wp-content/uploads/2017/08/27181600/Shin-Se-Kyung4.jpg")
                .resize(50, 50)
                .centerCrop()
                .into(circleImageView);
//        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        rl.addRule(RelativeLayout);
        try {
            MqttClient client = new MqttClient("tcp://192.168.1.3:1883", "AndroidThingSub", new MemoryPersistence());
            client.setCallback(this);
            client.connect();

            String topic = "updateloc";
            client.subscribe(topic);

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    class A extends AsyncTask<Void, Void, Void> {

        Context context;

        public A (Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                Picasso.with(this.context)
                        .load("https://1.soompi.io/wp-content/uploads/2015/02/shinsaekyung1.jpg")
                        .resize(50, 50)
                        .centerCrop()
                        .into(circleImageView);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void connectionLost(Throwable cause) {

    }
    int a = 0;
    int b = 0;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(message);
        circleImageView.animate().translationX(a + 50);
        circleImageView.animate().translationY(b + 50);
        a = a +50;
        b = b + 50;
        circleImageView.setAlpha(0.4f);
        A a = new A(getApplicationContext());
        a.execute();
//        try {
//            circleImageView.setImageResource();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Log.e("a", topic);
    }



    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
