package com.money.mmproject;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;


public class updateActivity extends AppCompatActivity {
    private TransactionsDB db;
    private Button RegisterUserButton;
    private Button updateTransactionsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Parse.initialize(this, "sb86WYyd0l1igfZY77sTtEWb0TVn3g067JORyVT6",
                "uQTRuM7tHPVzgctBOYQO6LmFpSBCNbAKYaFS8OmA");
        RegisterUserButton = (Button) findViewById(R.id.RegisterUserButton);
        updateTransactionsButton = (Button) findViewById(R.id.UpdateTransactionsButton);
        db = new TransactionsDB(getApplication());
        Cursor CR = db.getInformation(db);
        if (CR.moveToFirst()) {
            do {
                System.out.println(CR.getString(0) + " " + CR.getString(1) +
                CR.getString(2) + " " + CR.getString(3));
            } while (CR.moveToNext());
        }
        RegisterUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        updateTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        switch(item.getItemId()) {
            case R.id.action_user_profile:
                Intent userProfileIntent = new Intent(getApplicationContext(),
                        UserProfileActivity.class);
                startActivity(userProfileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
