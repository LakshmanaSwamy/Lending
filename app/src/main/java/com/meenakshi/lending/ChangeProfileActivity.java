package com.meenakshi.lending;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meenakshi.com.meenakshi.serviceAPI.RestClient;
import com.meenakshi.domain.ResponseResult;
import com.meenakshi.domain.User;

import java.util.Date;

public class ChangeProfileActivity extends Activity {

    TextView firstName, lastName, mobileNumber, email, address, userName, confirmPwd, password;
    Button saveBtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        user = ((Lending) this.getApplication() ).getUser();
        firstName = (TextView)findViewById(R.id.firstName);
        firstName.setText(user.getFirstName());
        saveBtn = (Button)findViewById(R.id.profileSaveBtn);
        lastName = (TextView)findViewById(R.id.lastName);
        lastName.setText(user.getLastName());
        mobileNumber = (TextView)findViewById(R.id.mobileNumber);
        mobileNumber.setText(user.getMobileNumber());
        email = (TextView)findViewById(R.id.email);
        email.setText(user.getEmail());
        address = (TextView)findViewById(R.id.address);
        address.setText(user.getAddress());
        userName = (TextView)findViewById(R.id.regUserName);
        userName.setText(user.getLoginId());
        confirmPwd = (TextView)findViewById(R.id.saveConfirmPwd);
        confirmPwd.setText(user.getPassword());
        password = (TextView)findViewById(R.id.regPwd);
        password.setText(user.getPassword());



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setMobileNumber(mobileNumber.getText().toString());
                user.setEmail(email.getText().toString());
                user.setAddress(address.getText().toString());
                user.setLoginId(userName.getText().toString());
                user.setPassword(password.getText().toString());
                Gson gson = new Gson();
                String signupURL = getResources().getString(R.string.UPDATE_USER);
                RestClient client = new RestClient(signupURL, getApplicationContext());
                client.setPostString(gson.toJson(user));
                client.AddHeader("Accept", "application/json");
                client.AddHeader("Content-Type", "application/json");

                try {
                    client.execute(RestClient.RequestMethod.POST);
                    // makePostRequest();


                    String response = client.getResponse();
                    ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
                    if (responseObj.status.equalsIgnoreCase("SUCCESS")){
                        Toast.makeText(getApplication(), "Profile Saved Successfully", Toast.LENGTH_LONG).show();
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
