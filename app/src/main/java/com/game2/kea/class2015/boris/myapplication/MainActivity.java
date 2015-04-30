package com.game2.kea.class2015.boris.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{

    protected Controller control;

    protected void onDraw()
    {

        Canvas c = new Canvas();
        Paint p=new Paint();
        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.bear);
        p.setColor(Color.RED);
        c.drawBitmap(b, 0, 0, p);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        control = new Controller(this);
        super.onCreate(savedInstanceState);

       setContentView(R.layout.mainmenu_1204x600);
    }

    //Auto-generated
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        onDraw();
        return true;
    }

    //Helper for the GUI refresh at Farming View
    public void Kappa(final String plyrstr, final String mobname, final String mobarmor, final String plyrstraftr, final Drawable img, final int next_lvl_exp_req, final int experience, final String plyrgold, final String plyrlvl,final String mobmaxhp,final String mobhp){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DisplayFarming(plyrstr, mobname, mobarmor, plyrstraftr,img, next_lvl_exp_req, experience, plyrgold, plyrlvl,mobmaxhp,mobhp);
            }
        });
    }

    //Refresh the GUI in the Farming View
    public void DisplayFarming(String plyrstr, String mobname, String mobarmor, String plyrstraftr, Drawable img, int next_lvl_exp_req, int experience, String plyrgold, String plyrlvl,String mobmaxhp,String mobhp)
    {
        ProgressBar xp = (ProgressBar)findViewById(R.id.progressBar_exp);
        xp.setMax(next_lvl_exp_req);
        xp.setProgress(experience);

        ProgressBar mobhpbar = (ProgressBar)findViewById(R.id.progressBar_mob_hp);
        mobhpbar.setMax(Integer.parseInt(mobmaxhp));
        mobhpbar.setProgress(Integer.parseInt(mobhp));

        double a = Double.parseDouble(mobhp) / Double.parseDouble(mobmaxhp);

        if(a*100 > 60)
        mobhpbar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        if(a*100 < 60 && a*100 > 20)
            mobhpbar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

        if(a*100 < 20)
            mobhpbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        TextView xpbar = (TextView)findViewById(R.id.textView_pbar);
        xpbar.setText(experience+"/"+next_lvl_exp_req);

        TextView mobhptxt = (TextView)findViewById(R.id.textView_mobhp);
        mobhptxt.setText(mobhp+"/"+mobmaxhp);

        TextView lvl = (TextView)findViewById(R.id.textView_lvl_value);
        lvl.setText(plyrlvl);

        TextView gold = (TextView)findViewById(R.id.textView_gold_value);
        gold.setText(plyrgold);

        TextView playerstr = (TextView)findViewById(R.id.textView_player_str_value);
        playerstr.setText(plyrstraftr);

        TextView mname = (TextView)findViewById(R.id.textView_mob_name_value);
        mname.setText(mobname);

        TextView marmor = (TextView)findViewById(R.id.textView_mob_armor_value);
        marmor.setText(mobarmor);

        ImageView playerpic = (ImageView)findViewById(R.id.imageView_player_img);
        int path = R.drawable.char_1;
        playerpic.setImageResource(path);

        ImageView mobpic = (ImageView)findViewById(R.id.imageView_mob_img);

        switch(mobname) {
            case "wolf":      path = R.drawable.wolf;
                mobpic.setImageResource(path);           break;
            case "bear":       path = R.drawable.bear;
                mobpic.setImageResource(path);          break;
            case "tiger":      path = R.drawable.tiger;
                mobpic.setImageResource(path);           break;

        }

    }

    // Auto-generated code
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



    //BUTTON LISTENERS HEAR
    //----------------------------------------------------------------------------

    //Set layout to Town
    public void buttonOnClickBackToTown(View v)
    {
        setContentView(R.layout.activity_main_1024x600);
    }

        // Load the last save
    public void buttonOnClickLoad(View v)
    {

        setContentView(R.layout.activity_main_1024x600);
    }

    // Save character and Start a new Game
    public void buttonOnClickCreateandLoad(View v)
    {
        TextView name = (TextView)findViewById(R.id.textView_name);
        Spinner race = (Spinner)findViewById(R.id.spinner_race);
        Spinner classs = (Spinner)findViewById(R.id.spinner_class);
        TextView origin = (TextView)findViewById(R.id.textview_origin);
        ImageView image = (ImageView)findViewById(R.id.imageView_create);


       Drawable a = image.getBackground();

        control.createPlayer(name.getText().toString(),race.getSelectedItem().toString(),classs.getSelectedItem().toString(),origin.getText().toString(),a);
        setContentView(R.layout.activity_main_1024x600);
    }
    // OPen farming view
    public void buttonOnClickFarming(View v)
    {

        setContentView(R.layout.farming_view_1024x600);
    }

    // GO to character-creation
    public void buttonOnClickStart(View v)
    {

        setContentView(R.layout.create_character_1024x600);

        Spinner race_spin = (Spinner)findViewById(R.id.spinner_race);


        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter
                .createFromResource(this, R.array.race_array,
                        android.R.layout.simple_spinner_item);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        race_spin.setAdapter(adapter_day);
        race_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ImageView whoamiwith = (ImageView)findViewById(R.id.imageView_create);

                if(position == 0)
                {
                    Drawable new_image= getResources().getDrawable(R.drawable.char_1);
                    whoamiwith.setBackgroundDrawable(new_image);
                }
                if(position == 1)
                {
                    Drawable new_image= getResources().getDrawable(R.drawable.char_2);
                    whoamiwith.setBackgroundDrawable(new_image);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //Back to Main-menu listener
    public void buttonOnClickMenu(View v)
    {

        setContentView(R.layout.mainmenu_1204x600);
    }

    //Goes to Invetory View
    public void buttonOnClickInventory(View v)
    {

        setContentView(R.layout.inventorylayout_1024x600);

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    // listener for the Start farming button
    public void buttonOnClickFarmingStart(View v)
    {

        Button btnNewButton = (Button)findViewById(R.id.button_Start);


        if (btnNewButton.getText() == "Stop")
        {
            btnNewButton.setText("Start");
            Drawable new_image= getResources().getDrawable(R.drawable.start_farming_button);
            btnNewButton.setBackgroundDrawable(new_image);

                control.farming = false;
        } else
        {
            btnNewButton.setText("Stop");
            Drawable new_image= getResources().getDrawable(R.drawable.stop_farming_button);
            btnNewButton.setBackgroundDrawable(new_image);
           Thread queryThread = new Thread()
        {
            public void run()
            {
                if (control.farming) {
                    control.farming = false;

                } else {
                    control.farming = true;
                    control.Farming();
                }
            }

        };
            queryThread.start();
        }
    }

    //exit buttons listener
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
    //----------------------------------------------------------------------------


}
