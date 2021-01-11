package com.example.sqlitedatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String dbname = "notes";
    static final int version = 1;

    private String tableQuotes="quotestable";

    //CRUD

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+tableQuotes+"(id integer PRIMARY KEY AUTOINCREMENT, author varchar(50), quote text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table ";

    }

    public void deleteQuote(int quoteId){
        SQLiteDatabase db=getWritableDatabase();
        String sql="delete from "+tableQuotes+" where id = '"+quoteId+"' ";
        db.execSQL(sql);

    }

    public void insertIntoQuotes(Quote quote){

        SQLiteDatabase db=getWritableDatabase();
        String sql="insert into "+tableQuotes+" (author,quote)values('"+quote.getAuthor()+"','"+quote.getQuote()+"')";
        db.execSQL(sql);

    }

    public ArrayList<Quote> getAllQuotes(){
        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM "+tableQuotes;

        ArrayList<Quote> quotes = new ArrayList<Quote>();

        try{

            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            do {
                Quote quote = new Quote(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                quotes.add(quote);
            } while (cursor.moveToNext());

        }catch(Exception e){
            Log.e("AWAIS", "printQuotes: "+e.getMessage() );

            return null;
        }

        return quotes;
    }

    public void printQuotes(){

        SQLiteDatabase db=getReadableDatabase();

        String sql="SELECT * FROM "+tableQuotes;

        Log.i("AWAIS", "printQuotes: "+sql);

        try{

            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();

            do {
                String result = cursor.getInt(0) + "\t" + cursor.getString(1) + "\t" + cursor.getString(2);
                Log.i("AWAIS", "printQuotes: " + result);
            } while (cursor.moveToNext());

        }catch(Exception e){
            Log.e("AWAIS", "printQuotes: "+e.getMessage() );
        }

    }
}
