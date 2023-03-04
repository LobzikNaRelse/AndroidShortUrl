package com.example.kursdiplomandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;

    private EditText full_art;

    private EditText small_art;

    private Button add_btn;

    private ArrayList<String> allFull = new ArrayList<String>();
    private ArrayList<String> allSmall = new ArrayList<String>();
    // я не использовал HashMap т.к. не позволяет создать 2 одинаковых значения в одном из полей
    // то есть нельзя будет создать 2 одинаковых сокращенных ссылки, но и 2 одинаковых длинных



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListArt);
        full_art = findViewById(R.id.full_art);
        small_art = findViewById(R.id.small_art);
        add_btn = findViewById(R.id.btn_add);

        add_btn.setOnClickListener(view ->
        {
            int count = 0;

            if (!small_art.getText().toString().equals("") && !full_art.getText().toString().equals(""))
            {
                //System.out.println("не пусто");
                for (String i:allSmall)
                {
                    //System.out.println(small_art.getText());
                    if (small_art.getText().toString().equals(i))
                    {
                        count=+1;
                        System.out.println(count);
                        break;
                    }
                }

                if (count > 0)
                {
                    //System.out.println(count);
                    add_btn.setText("Такая ссылка уже есть");
                    small_art.setText("");
                } else
                {
                    allFull.add(full_art.getText().toString());
                    allSmall.add(small_art.getText().toString());
                    //System.out.println(allFull);
                    //System.out.println(allSmall + "spisok");
                    addToList();
                    add_btn.setText("Готово");
                }

            } else
            {
                add_btn.setText("Поля не должны быть пустыми");
            }
        });

    }

    private void addToList()
    {
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                R.layout.listrow, R.id.addedArt);
//        listView.setAdapter(adapter);
        // первая версия, без перехода

        ProgramAdapter programAdapter = new ProgramAdapter(this, allSmall, allFull);
        listView.setAdapter(programAdapter);
        // у меня не получилось в стандартный адаптер, запихнуть 2 параметра (имя и юрл)
        // я пробовал HashMap, Map, List, ArrayList я не понял как запихнуть, что бы он работал
        // по этому пришлось 95% найти в интернете, сделанный адаптер, который наследуется от первоначального

        // у меня получилось все, но когда открываю ссылку, рендер идет как-то неправильно, самого сайта(в браузере).
        // 1 раз из 5 даже крашнулось из-за этого, надеюсь что проблема в виртуальной машине
        // и такая же проблема при ошибке 404, просто рендер браузера. Мб надо больше памяти на виртуальную машину выделить
    }
}