package com.meenakshi.lending;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.meenakshi.adapter.CustomAdapter;
import com.meenakshi.com.meenakshi.serviceAPI.RestClient;
import com.meenakshi.domain.Loan;
import com.meenakshi.domain.ResponseResult;
import com.meenakshi.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoanDetailsActivity extends AppCompatActivity {

    ListView list;
    Button applyLoanBtn;
    CustomAdapter adapter;
    User user;
    List<Loan> loanDetails;
    public LoanDetailsActivity CustomListView = null;
    public ArrayList<Loan> CustomListViewValuesArr = new ArrayList<Loan>();
    @Override
     protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);
        applyLoanBtn = (Button)(findViewById(R.id.applyLoanBtn));
        user =((Lending) this.getApplication()).getUser();

        String loanURL = getResources().getString(R.string.LOAN_DETAILS);
        RestClient client = new RestClient(loanURL, getApplicationContext());
        String postString = "{\"loginId\":\""+user.getLoginId()+"\"}";
        client.setPostString(postString);
        client.AddHeader("Accept", "application/json");
        client.AddHeader("Content-Type", "application/json");
                try {
            // client.Execute(RestClient.RequestMethod.POST);
            client.execute(RestClient.RequestMethod.POST);
            // makePostRequest();

            String response = client.getResponse();
            Gson gson = new Gson();
            ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
            if (responseObj.status.equalsIgnoreCase("SUCCESS")){
                JSONArray jsonArray = new JSONArray(responseObj.result);
                for(int i =0; i < jsonArray.length(); i++)
                {
                   JSONObject obj = jsonArray.getJSONObject(i);// parse you JSONObject to model object here
                    final Loan loanObj = new Loan();
                    /******* Firstly take data in model object ******/
                    loanObj.setLoginId(obj.getString("loginId"));
                    loanObj.setLoanType(obj.getString("loanType"));
                    loanObj.setLoanDetails(obj.getString("loanDetails"));
                    loanObj.setUpdated_date(obj.getString("updated_date"));
                    CustomListViewValuesArr.add( loanObj );

                }
               // loanDetails = gson.fromJson(responseObj.getResult(),  List<Loan>.class);
                //((Lending) this.getApplication()).setUser(user);

            }else{
                Toast.makeText(getApplication(), responseObj.status, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();

        }

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
      //  setListData();

        Resources res =getResources();
        list = ( ListView )findViewById( R.id.list );// List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );

        applyLoanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent applyLoanIntent = new Intent(getApplicationContext(), ApplyLoanActivity.class);
                startActivity(applyLoanIntent);
            }
        });

        }
    public void setListData()
     {

        for (int i = 0; i < loanDetails.size(); i++) {

            final Loan loanObj = new Loan();
            /******* Firstly take data in model object ******/
            loanObj.setLoginId("Company " + i);
            loanObj.setLoanDetails("image" + i);
            loanObj.setLoanType("http:\\www." + i + ".com");
            loanObj.setUpdated_date("http:\\www."+i+".com");


            /******** Take Model Object in ArrayList **********/
           CustomListViewValuesArr.add( loanObj );
            }

        }


           /*****************  This function used by adapter ****************/
           public void onItemClick(int mPosition)
    {
        Loan tempValues = ( Loan ) CustomListViewValuesArr.get(mPosition);
        // SHOW ALERT                 
        Toast.makeText(CustomListView,""+tempValues.getLoginId()+"Image:"+tempValues.getLoanDetails(),Toast.LENGTH_LONG).show();
        }






//    ListView listView ;
//    User user;
//    ArrayList<Loan> loanDetails;
//    ArrayList<String> values;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loan_details);
//        user =((Lending) this.getApplication()).getUser();
//
//        String loanURL = getResources().getString(R.string.LOAN_DETAILS);
//        RestClient client = new RestClient(loanURL, getApplicationContext());
//        String postString = "{\"loginId\":\""+user.getLoginId()+"\"}";
//        client.setPostString(postString);
//        client.AddHeader("Accept", "application/json");
//        client.AddHeader("Content-Type", "application/json");
//
//        try {
//            // client.Execute(RestClient.RequestMethod.POST);
//            client.execute(RestClient.RequestMethod.POST);
//            // makePostRequest();
//
//            String response = client.getResponse();
//            Gson gson = new Gson();
//            ResponseResult responseObj = gson.fromJson(response, ResponseResult.class);
//            if (responseObj.status.equalsIgnoreCase("SUCCESS")){
//                loanDetails = gson.fromJson(responseObj.result, ArrayList.class);
//                //((Lending) this.getApplication()).setUser(user);
//                values = new ArrayList<String>();
//                for(int i=0;i<loanDetails.size();i++){
//
//                }
//            }else{
//                Toast.makeText(getApplication(), responseObj.status, Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
//
//        }
//        listView = (ListView) findViewById(R.id.list);
////        String[] values = new String[] { "1. Personal Loan ",
////                "2. Gold Loan",
////                "3. Car Loan",
////                "4. Insta Loan"};
//
//
//
//        // Define a new Adapter
//        // First parameter - Context
//        // Second parameter - Layout for the row
//        // Third parameter - ID of the TextView to which the data is written
//        // Forth - the Array of data
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.customer_item, R.id.listText, values);
//
//
//        // Assign adapter to ListView
//        listView.setAdapter(adapter);
//    }


}
