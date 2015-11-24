package com.money.mmproject;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

/*
http://karanbalkar.com/2014/05/display-graphs-using-graphview-in-android/

http://www.android-graphview.org/documentation
*/

public class MainActivity <E extends DataPointInterface>extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onStart() {
        super.onStart();




        Log.d("debug", "LineGraph In the onStart() event");
    }

    public void onRestart() {
        super.onRestart();
        Log.d("debug", "LineGraph In the onRestart() event");
    }

    public void onResume() {
        super.onResume();
        Log.d("debug", "LineGraph In the onResume() event");

        /*


        />
             app:seriesType="line"

        * */
        LineGraphSeries<DataPoint> seriesLineGraph =
                new LineGraphSeries<DataPoint>(
                        new DataPoint[] {
                                new DataPoint(0,1),
                                new DataPoint(1,5),
                                new DataPoint(2,3),
                                new DataPoint(3,2),
                                new DataPoint(4,6)
                        });

        BarGraphSeries<DataPoint> seriesBarGraph = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        GraphView mGraphView = (GraphView) findViewById(R.id.graphLine);
        //GraphViewSeriesStyle mGraphViewSeriesStyle = new GraphViewSeriesStyle (Color.BLUE, 3);


        mGraphView.addSeries(seriesBarGraph);

        /*
        GraphView graphView;
        GraphViewSeries exampleSeries =
             new GraphViewSeries(
                  new GraphViewData[] {
                  new GraphViewData(1, 2.0d)
                , new GraphViewData(2, 1.5d)
                , new GraphViewData(3, 2.5d)
                , new GraphViewData(4, 1.0d)
        });
        graphView = new LineGraphView(this, "323.3");
        //graphView.addSeries(exampleSeries); // data
    */

    }

    public void onPause() {
        super.onPause();
        Log.d("debug", "LineGraph In the onPause() event");
    }

    public void onStop() {
        super.onStop();
        Log.d("debug", "LineGraph In the onStop() event");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("debug", "LineGraph In the onDestroy() event");
    }
}

/*working code
    <com.jjoe64.graphview.helper.GraphViewXML
        android:layout_width="match_parent"
        android:layout_height="100dip"
        app:seriesData="0=5;2=5;3=0;4=2"
        android:id="@+id/graphLine"/>
        
 LineGraphSeries<DataPoint> seriesLineGraph =
                new LineGraphSeries<DataPoint>(
                        new DataPoint[] {
                                new DataPoint(0,1),
                                new DataPoint(1,5),
                                new DataPoint(2,3),
                                new DataPoint(3,2),
                                new DataPoint(4,6)
                        });

        BarGraphSeries<DataPoint> seriesBarGraph = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        GraphView mGraphView = (GraphView) findViewById(R.id.graphLine);
        //GraphViewSeriesStyle mGraphViewSeriesStyle = new GraphViewSeriesStyle (Color.BLUE, 3);


        mGraphView.addSeries(seriesBarGraph);


 */