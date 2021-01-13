package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

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
            SwipeableRecyclerView rv = findViewById(R.id.recyclerView);
            rv.setLayoutManager(new LinearLayoutManager(this));
            MyAdapter adapter = new MyAdapter(this,quotes);
            rv.setAdapter(adapter);

            rv.setListener(new SwipeLeftRightCallback.Listener() {
                @Override
                public void onSwipedLeft(int position) {
                    Quote quote = quotes.get(position);
                    Intent intent = new Intent(QuotesView.this,QuoteEdit.class);
                    intent.putExtra("id",quote.getId());
                    intent.putExtra("author",quote.getAuthor());
                    intent.putExtra("quote",quote.getQuote());
                    startActivity(intent);
                }

                @Override
                public void onSwipedRight(int position) {
                    helper.deleteQuote(quotes.get(position).getId());
                    quotes.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(QuotesView.this,"Deleted.",Toast.LENGTH_SHORT).show();
                }
            });

            SwipeableRecyclerView.ItemDecoration itemDecoration = new
                        DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            rv.addItemDecoration(itemDecoration);
        }
        else{
            Toast.makeText(QuotesView.this,"You didn't save any Quote yet.",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuotesView.this,MainActivity.class);
        startActivity(intent);
    }


}