package com.money.mmproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserProfileActivity extends AppCompatActivity {

    private EditText UserNameText;
    private EditText NameText;
    private EditText IncomeText;
    private EditText SavingText;
    private EditText SpendingText;
    private UserProfileDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Button btn = (Button) findViewById(R.id.SaveButton);
        UserNameText = (EditText) findViewById(R.id.UserNameEditText);
        NameText = (EditText) findViewById(R.id.NameEditText);
        IncomeText = (EditText) findViewById(R.id.IncomeEditText);
        SavingText = (EditText) findViewById(R.id.SavingEditText);
        SpendingText = (EditText) findViewById(R.id.SpendingEditText);
        db = new UserProfileDBHandler(getApplication());
        Cursor CR = db.getInformation(db);

        //Set text to the edit texts from database
        if(CR.moveToFirst()){
            UserNameText.setText(CR.getString(0));
            NameText.setText(CR.getString(1));
            IncomeText.setText(CR.getString(2));
            SavingText.setText(CR.getString(3));
            SpendingText.setText(CR.getString(4));

        }

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String first = UserNameText.getText().toString();
                String last = NameText.getText().toString();
                double income = Double.parseDouble(IncomeText.getText().toString());
                double saving = Double.parseDouble(SavingText.getText().toString());
                double spending = Double.parseDouble(SpendingText.getText().toString());

                //Check every box input
                if (UserNameText.getText().toString().trim().length() > 0
                        && NameText.getText().toString().trim().length() > 0
                        && IncomeText.getText().toString().trim().length() > 0
                        && SavingText.getText().toString().trim().length() > 0
                        && SpendingText.getText().toString().trim().length() > 0) {

                    //Database handler
                    Cursor CR = db.getInformation(db);
                    if(CR.moveToFirst()){
                        System.out.println("THIS HAS DATA.");
                        db.update(db, first, last, income, saving, spending);
                        System.out.println(CR.getString(0) + " " + CR.getString(1));
                    }else{
                        //insert new values into database
                        System.out.println("THIS HAS NO DATA");
                        db.insert(first, last, income, saving, spending);
                    }

                    Toast.makeText(getBaseContext(), "User Profile Saved",Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Please enter all the information.",
                            Toast.LENGTH_LONG).show();
                }

                return;
            }
        });

    }





}
