package com.money.mmproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onclickWeekly(View view){
        //Intent myIntent = new Intent(MainActivity.this,xxx_PUTYOURCLASSNAMEINHERE_xxx.class);
        //MainActivity.this.startActivity(myIntent);
    }

    public void onclickAnnualy(View view){
        //Intent myIntent = new Intent(MainActivity.this,xxx_PUTYOURCLASSNAMEINHERE_xxx.class);
        //MainActivity.this.startActivity(myIntent);
    }

    public void onclickTransaction(View view){
        //Intent myIntent = new Intent(MainActivity.this,xxx_PUTYOURCLASSNAMEINHERE_xxx.class);
        //MainActivity.this.startActivity(myIntent);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
