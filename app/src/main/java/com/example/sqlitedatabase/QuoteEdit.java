package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuoteEdit extends AppCompatActivity {

    private EditText name;
    private EditText quote;
    private Button save;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_edit);

        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        quote = findViewById(R.id.quote);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();

        id = data.getInt("id");
        name.setText(data.getString("author"));
        quote.setText(data.getString("quote"));

        DatabaseHelper helper=new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().length() != 0 && quote.getText().length() != 0) {
                    helper.updateQuote(new Quote(id, name.getText().toString(),quote.getText().toString()));
                    Toast.makeText(QuoteEdit.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuoteEdit.this,QuotesView.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(QuoteEdit.this,"Fields Can't be empty",Toast.LENGTH_LONG).show();
            }
        });
    }
}