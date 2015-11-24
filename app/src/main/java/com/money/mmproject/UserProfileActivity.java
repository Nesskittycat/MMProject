package com.money.mmproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Nessa on 11/20/15.
 */
public class UserProfileActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);

    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.SaveButton:
                return;

        }
    }


}
