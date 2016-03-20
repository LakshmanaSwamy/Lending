package com.meenakshi.lending;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meenakshi.com.meenakshi.serviceAPI.RestClient;
import com.meenakshi.domain.ResponseResult;
import com.meenakshi.domain.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {

    Button loginBtn, signUpBtn;
    String loginType="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signUpBtn = (Button) findViewById(R.id.signUp);

        final TextView userNameTV = (TextView)(findViewById(R.id.userName));
        final TextView passwordTV = (TextView)(findViewById(R.id.password));



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override//192.168.100.117
            public void onClick(View v) {
                String signinURL = getResources().getString(R.string.SIGN_IN_URL);
                RestClient client = new RestClient(signinURL, getApplicationContext());
                String postString = "{\"loginId\":\""+userNameTV.getText()+"\",\"password\":\""+passwordTV.getText()+"\",\"loginType\":\""+loginType+"\"}";
                client.setPostString(postString);
                //RestClient.RequestMethod.POST;
                client.AddHeader("Accept", "application/json");
                client.AddHeader("Content-Type", "application/json");

                try {
                   // client.Execute(RestClient.RequestMethod.POST);
                    client.execute(RestClient.RequestMethod.POST);
                  //  client.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
                   // makePostRequest();

                String response = client.getResponse();
                Gson gson = new Gson();
                ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
                if (responseObj.status.equalsIgnoreCase("SUCCESS")){
                    User user = gson.fromJson(responseObj.result, User.class);
                    //((Lending) this.getApplication()).setUser(user);
                    ((Lending) getApplication() ).setUser(user);

                    Intent intent=null;
                    if(loginType.equalsIgnoreCase("admin")){
                        intent = new Intent(getBaseContext(), AdminHomeActivity.class);
                    }
                   else if(loginType.equalsIgnoreCase("employee")){
                        intent = new Intent(getBaseContext(), EmployeeHomeActivity.class);
                    }
                    else if(loginType.equalsIgnoreCase("customer")){
                        intent = new Intent(getBaseContext(), CustomerHomeActivity.class);
                    }
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(), responseObj.status, Toast.LENGTH_LONG).show();
                }
                } catch (Exception e) {
                    Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        Spinner staticSpinner = (Spinner) findViewById(R.id.loginAs);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.loginType_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String typeLocal = (String) parent.getItemAtPosition(position);
                loginType = typeLocal.toLowerCase();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

}