package com.money.mmproject;

import static com.money.mmproject.Constants.FIRST_COLUMN;
import static com.money.mmproject.Constants.SECOND_COLUMN;
import static com.money.mmproject.Constants.THIRD_COLUMN;
import static com.money.mmproject.Constants.FOURTH_COLUMN;
import static com.money.mmproject.Constants.FIFTH_COLUMN;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> list;
    private TransactionsDB db;
    private Context context = this;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView listView=(ListView)findViewById(R.id.listView1);

        list=new ArrayList<HashMap<String,String>>();


        db = new TransactionsDB(getApplication());
        Cursor CR = db.getInformation(db);
        Cursor checkNull = db.getInformation(db);

        if (checkNull == null || checkNull.getCount() <= 0) {
            Toast.makeText(getApplicationContext(), "There is no transaction history yet.",
                    Toast.LENGTH_LONG).show();
        }
        if (CR.moveToFirst()) {
            int i =1;
            do {
                HashMap<String,String> temp=new HashMap<String, String>();


                //id start from 0 in the database but change to 1 when showing to the user
                temp.put(FIRST_COLUMN, Integer.toString(i) );
                //date
                temp.put(SECOND_COLUMN, CR.getString(0));
                //category
                temp.put(THIRD_COLUMN, "$" + CR.getString(1) );
                //paid amount
                temp.put(FOURTH_COLUMN, CR.getString(2));
                //description
                temp.put(FIFTH_COLUMN,  CR.getString(3));
                list.add(temp);
                i++;
            } while (CR.moveToNext());
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            boolean option = true;
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                pos = position+1;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                //set title
                alertDialogBuilder.setTitle("Delete:");
                alertDialogBuilder.setMessage("Do you want to delete the item with the same information?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                double am = 0;
                                String category = "";
                                String des = "";
                                String date = "";


                                Cursor c = db.getInformation(db);
                                int j = 1;
                                //if this button is clicked, proceed.
                                if (c.moveToFirst()) {
                                    do {
                                        if (j == pos) {
                                            date = c.getString(0);
                                            if(c.getString(1).equals("")) {
                                                am = 0;
                                            }
                                            else {
                                                am = Double.parseDouble(c.getString(1));
                                            }
                                            category = c.getString(2);
                                            des = c.getString(3);
                                            j++;
                                            break;
                                        }
                                        c.getString(0);
                                        c.getString(1);
                                        c.getString(2);
                                        c.getString(3);
                                        j++;
                                    } while (c.moveToNext()) ;
                                }

                                db.delete_byRow(date, am, category, des);
                                Intent intent1 = new Intent(HistoryActivity.this,
                                        HistoryActivity.class);
                                finish();
                                startActivity(intent1);

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


        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(HistoryActivity.this,MainActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
