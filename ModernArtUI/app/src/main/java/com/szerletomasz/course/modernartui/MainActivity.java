package com.szerletomasz.course.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout container = ( RelativeLayout ) findViewById( R.id.container1 );
        SeekBar seek = ( SeekBar ) findViewById( R.id.seekBar );

        seek.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (int i = 0; i < container.getChildCount(); ++i ) {
                    View child = container.getChildAt(i);
                    int color = Color.parseColor( (String) child.getTag() );

                    if ( color == getResources().getColor(R.color.white) ||
                            color == getResources().getColor(R.color.gray) ) {
                        continue;
                    };

                    int r = ( color >> 16 ) & 0x000000FF;
                    int g = ( color >> 8 ) & 0x000000FF;
                    int b = color & 0x000000FF;

                    int newR = (int) (r + ( 255 - 1.4 * r ) * (progress / 100.0));
                    int newG = (int) (g + ( 255 - 1.6 * g ) * (progress / 100.0));
                    int newB = (int) (b + ( 255 - 1.8 * b ) * (progress / 100.0));

                    child.setBackgroundColor(Color.rgb(newR, newG, newB));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        if (id == R.id.action_more_information) {
            new MoreInfoDialog().show(getFragmentManager(), "Dialog");;
        }

        return super.onOptionsItemSelected(item);
    }
}
