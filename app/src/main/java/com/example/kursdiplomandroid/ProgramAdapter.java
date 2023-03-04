package com.example.kursdiplomandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String>
{
    Context context;
    ArrayList<String> fullArt = new ArrayList<String>();
    ArrayList<String> smallArt = new ArrayList<String>();

    public ProgramAdapter(Context context, ArrayList<String> smallArt, ArrayList<String> fullArt)
    {
        super(context, R.layout.listrow, R.id.addedArt, smallArt);
        this.context = context;
        this.smallArt = smallArt;
        this.fullArt = fullArt;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View singleItem = convertView;
        ProgramViewHolder holder = null;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.listrow, parent, false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else{

            holder = (ProgramViewHolder) singleItem.getTag();
        }
        holder.addedArt.setText(smallArt.get(position));

        singleItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Uri webpage = Uri.parse(fullArt.get(position));

                if (!fullArt.get(position).startsWith("http://www.") && !fullArt.get(position).startsWith("https://www.")) {
                    webpage = Uri.parse("http://www." + fullArt.get(position));
                }

                Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, webpage);
                context.startActivity(openLinksIntent);
            }
        });
        return singleItem;
    }


}
