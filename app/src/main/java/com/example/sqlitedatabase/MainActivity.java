package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText quote;
    private Button save;
    private Button myQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.save);
        myQuote = findViewById(R.id.myquote);

        DatabaseHelper helper=new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.name);
                quote = findViewById(R.id.quote);

                if(name.getText().length() != 0 && quote.getText().length() != 0) {
                    helper.insertIntoQuotes(new Quote(name.getText().toString(), quote.getText().toString()));
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
                    name.setText("");
                    quote.setText("");
                }
                else
                    Toast.makeText(MainActivity.this,"Fields Can't be empty",Toast.LENGTH_LONG).show();
            }
        });

        myQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuotesView.class);
                startActivity(intent);
            }
        });
    }


}