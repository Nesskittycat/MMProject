package com.money.mmproject;

/*
        Created by: Nessa Cheevasuchin
                    Lukas Wygasch
                    California State University of Long Beach
                    Fall 2015
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private TextView MoneySpentValueTextView;
    private TransactionsDB db;
    private double spentAmount = 0;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView p = new ImageView(this);
        p = (ImageView) findViewById(R.id.logo);

        MoneySpentValueTextView = (TextView) findViewById(R.id.MoneySpentValueTextView);
        db = new TransactionsDB(getApplication());
        Cursor CR = db.getInformation(db);
        Calendar now = Calendar.getInstance();
        //Iterate through the database and calculate the amount spent per month
        if (CR.moveToFirst()) {
            do {
                String month = "";
                String year = "";
                String holder = "";
                // MM/dd/yyyy
                holder = CR.getString(0);
                System.out.println(holder.indexOf("/"));
                month = holder.substring(0, holder.indexOf("/"));
                year = holder.substring(holder.length() - 4);
                if (month.equals(Integer.toString(now.get(Calendar.MONTH) + 1))
                        && year.equals(Integer.toString(now.get(Calendar.YEAR)))) {
                    if (CR.getString(1).equals("")) {
                    }
                    else {
                        spentAmount += Double.parseDouble(CR.getString(1));
                    }
                } else {
                    CR.getString(1);
                }
                CR.getString(2);
                CR.getString(3);
            } while (CR.moveToNext());
        }

        if (spentAmount == 0) {
            MoneySpentValueTextView.setText("$ 0.00");
        }
        else{
            //set the amount spent per month in currency form
            String spent = NumberFormat.getCurrencyInstance().format(spentAmount);
            spent = spent.replaceAll("\\.00", "");
            MoneySpentValueTextView.setText( spent);
        }
        int resource_id= getResources().getIdentifier("bagmoney", "drawable", getPackageName());

        Drawable drawable = getResources().getDrawable(resource_id);
        p.setBackgroundColor(0);
        p.setImageDrawable(drawable);
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
        Intent myIntent = new Intent(MainActivity.this,addTransaction.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void onClickHistory(View view) {
        Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
        MainActivity.this.startActivity(myIntent);

    }

    public void onClickClearHistory(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        //set title
        alertDialogBuilder.setTitle("Delete: ");
        alertDialogBuilder.setMessage("Do you want to delete the history?")
                .setCancelable(false)
                .setPositiveButton("Yes", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TransactionsDB db = new TransactionsDB(getApplicationContext());
                        db.del();
                        Toast.makeText(getApplicationContext(), "The history has been cleared.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(myIntent);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

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
            case R.id.update_online:
                Intent updateOnline = new Intent(getApplicationContext(),
                        updateActivity.class);

                startActivity(updateOnline);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        //set title
        alertDialogBuilder.setTitle("Exit:");
        alertDialogBuilder.setMessage("Do you want to exit the application?")
                .setCancelable(false)
                .setPositiveButton("Yes", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        return super.onKeyDown(keyCode, event);
    }
}
