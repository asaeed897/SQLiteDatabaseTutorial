package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class QuotesView extends AppCompatActivity {

    ArrayList<Quote> quotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_view);

        DatabaseHelper helper=new DatabaseHelper(this);
        quotes = helper.getAllQuotes();

        if(quotes != null){
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            MyAdapter adapter = new MyAdapter(this,quotes);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);
        }
        else{
            Toast.makeText(QuotesView.this,"You didn't save any Quote yet.",Toast.LENGTH_LONG).show();
        }



    }

}