package com.meenakshi.lending;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meenakshi.com.meenakshi.serviceAPI.RestClient;
import com.meenakshi.domain.Contact;
import com.meenakshi.domain.ResponseResult;
import com.meenakshi.domain.User;

import java.util.Date;

public class ContactForLoanActivity extends AppCompatActivity {

    EditText cusName,mobileNumber,subject,message;
    Button contactSaveBtn;
    User user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_for_loan);
        user =((Lending) this.getApplication()).getUser();
        cusName = (EditText)findViewById(R.id.nameValue);
        mobileNumber = (EditText)findViewById(R.id.contactValue);
        subject = (EditText)findViewById(R.id.subjectValue);
        message = (EditText)findViewById(R.id.messageValue);
        contactSaveBtn = (Button)findViewById(R.id.messageBtn);

        contactSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contactObj = new Contact();
                contactObj.setLoginId(user.getLoginId());
                contactObj.setContactNumber(mobileNumber.getText().toString());
                contactObj.setContactSubject(subject.getText().toString());
                contactObj.setMessage(message.getText().toString());
                contactObj.setUpdatedTime((new Date()).toString());
                Gson gson = new Gson();
                String signupURL = getResources().getString(R.string.SAVE_CONTACT);
                RestClient client = new RestClient(signupURL, getApplicationContext());
                client.setPostString(gson.toJson(contactObj));
                client.AddHeader("Accept", "application/json");
                client.AddHeader("Content-Type", "application/json");

                try {
                    client.execute(RestClient.RequestMethod.POST);
                    // makePostRequest();


                    String response = client.getResponse();
                    ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
                    if (responseObj.status.equalsIgnoreCase("SUCCESS")){
                        Toast.makeText(getApplication(), "Thanks for your Registering. We will get back soon", Toast.LENGTH_LONG).show();
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
