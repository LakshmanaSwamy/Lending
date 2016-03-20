package com.meenakshi.lending;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.Date;

public class SignUpActivity extends Activity {

    TextView firstName, lastName, mobileNumber, email, address, userName, confirmPwd, password;
    Button registerBtn;
    String gender="male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerBtn = (Button)findViewById(R.id.signUpSubmitBnt);
        firstName = (TextView)findViewById(R.id.firstName);
        lastName = (TextView)findViewById(R.id.lastName);
        mobileNumber = (TextView)findViewById(R.id.mobileNumber);
        email = (TextView)findViewById(R.id.email);
        address = (TextView)findViewById(R.id.address);
        userName = (TextView)findViewById(R.id.regUserName);
        confirmPwd = (TextView)findViewById(R.id.regConfirmPwd);
        password = (TextView)findViewById(R.id.regPwd);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gender = ((String) parent.getItemAtPosition(position)).toLowerCase();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User userObj = new User();
                userObj.setFirstName(firstName.getText().toString());
                userObj.setLastName(lastName.getText().toString());
                userObj.setMobileNumber(mobileNumber.getText().toString());
                userObj.setEmail(email.getText().toString());
                userObj.setGender(gender);
                userObj.setAddress(address.getText().toString());
                userObj.setLoginId(userName.getText().toString());
                userObj.setPassword(password.getText().toString());
                userObj.setLoginType("customer");
                userObj.setIsActive(1);
                userObj.setFirstLoginTime((new Date()).toString());
                Gson gson = new Gson();
                String signupURL = getResources().getString(R.string.SIGN_UP_URL);
                RestClient client = new RestClient(signupURL, getApplicationContext());
                client.setPostString(gson.toJson(userObj));
                client.AddHeader("Accept", "application/json");
                client.AddHeader("Content-Type", "application/json");

                try {
                    client.execute(RestClient.RequestMethod.POST);
                    // makePostRequest();


                String response = client.getResponse();
                ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
                if (responseObj.status.equalsIgnoreCase("SUCCESS")){
                    Toast.makeText(getApplication(), "Registration Success", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getBaseContext(), AdminHomeActivity.class);
//                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(), responseObj.status, Toast.LENGTH_LONG).show();
                }
                } catch (Exception e) {
                   // e.printStackTrace();
                    Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
