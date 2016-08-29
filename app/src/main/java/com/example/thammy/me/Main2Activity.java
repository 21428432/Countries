package com.example.thammy.me;
/**
 * Created by THAMMY on 8/26/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2Activity extends AppCompatActivity {
    private ProgressDialog progress;

    ListView listitem2;
    Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listitem2 = (ListView)findViewById(R.id.listView2);
        bt2 =(Button)findViewById(R.id.button1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new Fetchitem().execute();
            }
        });

        listitem2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView)view).getText().toString();
                finder(name);
                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();

            }
        });
    }

    private class Fetchitem extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> listitem3 = findAllItems();
            return listitem3;
        }

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(Main2Activity.this);
            progress.setTitle("Countries");
            progress.setMessage("Downloading Countries");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.setMax(100);
            progress.show();
        }
        @Override
        protected void onPostExecute(List<String> s) {
            super.onPostExecute(s);
            progress.hide();
            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext() , R.layout.list_item , s);
            listitem2.setAdapter(adapter);
        }
    }
    public static JSONObject requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)urlToRequest.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {

            } else if (statusCode != HttpURLConnection.HTTP_OK) {

            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {

        } catch (SocketTimeoutException e) {

        } catch (IOException e) {

        } catch (JSONException e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }


    private static void disableConnectionReuseIfNecessary() {
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {

        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    public List<String> findAllItems() {
        JSONObject serviceResult = this.requestWebService("http://services.groupkt.com/country/get/all");
        List<String> foundItem1 = new ArrayList<String>();


        try {
            JSONArray items = serviceResult.getJSONObject("RestResponse").getJSONArray("result");

            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i);
                Country c = new Country();
                BackEnd backEnd = new BackEnd(getApplicationContext());
                c.setName(obj.getString("name"));
                c.setCode(obj.getString("alpha2_code"));
                backEnd.addCountry(c);
                foundItem1.add(obj.getString("name"));
            }

        } catch (JSONException e) {
        }

        return foundItem1;
    }

    public  void finder(String f){

        BackEnd backEnd = new BackEnd(getApplicationContext());
        for(int c = 0; c < backEnd.getAllCountries().size(); c++){
            if( backEnd.getAllCountries().get(c).getName().equalsIgnoreCase(f)){
                Intent i = new Intent(getApplicationContext(),Main4Activity.class);
                i.putExtra("cname", backEnd.getAllCountries().get(c).getName());
                i.putExtra("ccode",backEnd.getAllCountries().get(c).getCode());
                startActivity(i);
            }
        }
    }
}
