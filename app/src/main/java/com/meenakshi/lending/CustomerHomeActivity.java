package com.meenakshi.lending;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerHomeActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "1. Available Loan Details ",
                "2. Change Profile",
                "3. Contact For Loan",
                "4. About Us"

        };
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.customer_item, R.id.listText, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                Intent intent;
                switch (itemPosition){
                    case 0:
                        intent =  new Intent(getApplicationContext(), LoanDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent =  new Intent(getApplicationContext(), ChangeProfileActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent =  new Intent(getApplicationContext(), ContactForLoanActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent =  new Intent(getApplicationContext(), CustomerAboutActivity.class);
                        startActivity(intent);
                        break;


                }
                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                        .show();

            }

        });
   }

}
