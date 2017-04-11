package com.stinky.fartone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String broker = "tcp://mqtt.gawra.net.pl:1883";
    final String mqttClientId = "and_app";
    final String LOG_TAG_MAIN = "LOG_MAIN";

    @BindView(R.id.mqtt_message)
    TextView mqttMessage;
    @BindView(R.id.subscribe_button)
    Button button;

    MqttClient simpleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            Log.i(LOG_TAG_MAIN, "Creating client.");
            simpleClient = new MqttClient(broker, mqttClientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("fart1");
            connOpts.setPassword("farting-toilet".toCharArray());
            simpleClient.connect(connOpts);
            Log.i(LOG_TAG_MAIN, "Connected");
            Log.i(LOG_TAG_MAIN, "Is really connected? : " + simpleClient.isConnected());
        } catch (MqttException e) {
            System.err.println(e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.subscribe_button) void subscribe(View view) {
        try {
            Log.i(LOG_TAG_MAIN, "Subscribing");
            simpleClient.subscribe("test/app/", 1);
            simpleClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.err.println(cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Got message!");
                    System.out.println("Topic: " + topic);
                    System.out.println("Message: ");
                    System.out.println(message);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.err.println(token);
                }
            });
        } catch (MqttException e) {
            System.err.println(e);
        }
    }
}
