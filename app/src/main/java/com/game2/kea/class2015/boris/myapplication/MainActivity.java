package com.game2.kea.class2015.boris.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_1204x600);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClickLoad(View v) {

        setContentView(R.layout.activity_main_1024x600);
    }

    public void buttonOnClickFarming(View v) {

        setContentView(R.layout.farming_view_1024x600);
    }

    public void buttonOnClickStart(View v) {

        setContentView(R.layout.create_character_1024x600);
    }

    public void buttonOnClickMenu(View v) {

        setContentView(R.layout.mainmenu_1204x600);
    }

    public void buttonOnClickExit(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

}
