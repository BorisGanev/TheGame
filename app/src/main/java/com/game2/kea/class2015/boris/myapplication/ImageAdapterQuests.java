package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oliwer on 08/05/2015.
 */
public class ImageAdapterQuests extends BaseAdapter{
    private Context mContext;
    public ArrayList<Quest> list;
    private static LayoutInflater inflater=null;

    public ImageAdapterQuests(Context c,ArrayList<Quest> list)
    {
        mContext = c;
        this.list = list;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
   /** public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(435, 89));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0,0, 0, 0);
            
        } else
        {
            imageView = (ImageView) convertView;
        }


        imageView.setImageResource(R.drawable.questpanelsign);

        return imageView;
    }
*/

   public View getView(int position, View convertView, ViewGroup parent)
   {
       TextView imageView;
       if (convertView == null) {
           // if it's not recycled, initialize some attributes
           imageView = new TextView(mContext);
           imageView.setLayoutParams(new GridView.LayoutParams(435, 89));

           imageView.setPadding(0,0, 0, 0);

       } else
       {
           imageView = (TextView) convertView;
       }


       imageView.setBackgroundResource(R.drawable.questpanelsign);

       imageView.setText(list.get(position).getName());

       imageView.setGravity(View.TEXT_ALIGNMENT_CENTER);

       imageView.setTextColor(Color.BLUE);
       imageView.setId(position);

       return imageView;
   }

/*
   public class Holder
   {
       TextView tv;
       ImageView img;
   }

   public View getView(final int position, View convertView, ViewGroup parent) {
       // TODO Auto-generated method stub

       Holder holder=new Holder();
       View rowView;

       rowView = inflater.inflate(R.layout.program_list, null);
       holder.tv=(TextView) rowView.findViewById(R.id.textView1);


       holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

       holder.tv.setWidth(holder.img.getWidth()); holder.tv.setHeight(holder.img.getHeight());
       holder.tv.setGravity(View.TEXT_ALIGNMENT_CENTER);

       holder.tv.setText(list.get(position).getName());

       rowView.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               Toast.makeText(mContext, "You Clicked " + position, Toast.LENGTH_LONG).show();
           }
       });

       return rowView;
   }*/
}
