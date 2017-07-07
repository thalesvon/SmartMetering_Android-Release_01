package com.iot.unb.SmartMetering;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.iot.unb.model.service.BatteryInfo;
import com.iot.unb.model.service.RaiseUIOT;
import com.android.volley.VolleyError;
import com.iot.unb.model.service.Raise;
import com.iot.unb.model.service.RaiseUIOT;
import com.iot.unb.model.service.SignalLevel;
import com.iot.unb.model.service.UIOTCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    int  REQUEST_CODE_ASK_PERMISSIONS = 123;
    private static Context context;
    private ListView listView;
    private List<LastData> data_list;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static List<String> last_json;
    private SwipeListAdapter adapter;

    /**
     * Gets the <{@link MainActivity}/> context.
     * @return <{@link Context}/>.
     */
    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Smart Metering");
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
        context = getBaseContext();
        new com.iot.unb.SmartMetering.GPSTracker(MainActivity.this);
        new SignalLevel(MainActivity.this);
        new BatteryInfo(MainActivity.this);
        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        data_list = new ArrayList<>();
        last_json = new ArrayList<>();
        adapter = new SwipeListAdapter(this, data_list);

        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getLastData();
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
        );


        final UIOTCaller uiotCaller = new UIOTCaller();
        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                uiotCaller.autoRegister();
                uiotCaller.dataCollection();
            }
        };
        timer.schedule (hourlyTask, 0l, 1000*10);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings :
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_state:
                Intent intent1 = new Intent(getContext(), StateActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
            getLastData();

    }

    public void getLastData(){
        swipeRefreshLayout.setRefreshing(true);
        String latitude;
        String longitude;
        String state;
        String percentage;
        String carrier;
        String signal;
        String signaldbm;
        String timestamp;

        try {
                for(int i = 0;i<last_json.size();i++) {

                    JSONObject dataObj = new JSONObject(last_json.get(i));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    timestamp = "Data collected on: " + sdf.format(Integer.valueOf(dataObj.getString("client_time")) * 1000L);
                    System.out.println("ESSE AQUUUIIIIIII "+dataObj.getString("client_time"));
                    JSONArray data = dataObj.getJSONArray("data");
                    JSONObject data_values = data.getJSONObject(0);
                    System.out.println(data_values);
                    JSONObject value = data_values.getJSONObject("data_values");
                    System.out.println(value);
                    latitude = "Latitude: " + value.getString("latitude");
                    longitude = "Longitude: " + value.getString("longitude");
                    state = "State: " + value.getString("state");
                    percentage = "Percentage: " + value.getString("percentage");
                    carrier = "Carrier: " ;//+ value.getString("carrier");
                    signal = "Signal: " + value.getString("signal");
                    signaldbm = "Signal(dBm): " + value.getString("signaldbm");


                    //System.out.println(""+timestamp);
                    //System.out.println(longitude);

                    LastData ld = new LastData(timestamp, latitude, longitude, state, percentage, carrier, signal, signaldbm);
                    data_list.add(0, ld);


                }

                    //System.out.println(data_list.get(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        last_json.clear();

    }
}
