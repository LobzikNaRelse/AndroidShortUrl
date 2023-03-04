package com.example.kursdiplomandroid;

import android.view.View;
import android.widget.TextView;

public class ProgramViewHolder
{
    TextView addedArt;

    ProgramViewHolder(View v)
    {
        addedArt = v.findViewById(R.id.addedArt);
    }
}
