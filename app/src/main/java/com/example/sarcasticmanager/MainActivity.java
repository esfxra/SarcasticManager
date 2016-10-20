/*

*/


package com.example.sarcasticmanager;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    dHelper firstdb;
    // buttons
    Button but2;
    Button bview;
    //text field
    EditText get_task;
    EditText get_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Calls constructor to create database and table
        firstdb = new dHelper(this);

        // buttons
        but2 = (Button) findViewById(R.id.but2);
        bview = (Button) findViewById(R.id.bView);
        // insertTask
        get_task = (EditText) findViewById(R.id.get_task);
//        get_id = (EditText) findViewById(R.id.get_id);
        // taskComp

        //@TODO: Fix populating ListView from database
//        AddTask();
//        viewAll();
//
//        // Query for items from the database and get a cursor back
//        Cursor todoCursor = firstdb.getAllData();
//
//        // Find ListView to populate
//        ListView lvItems = (ListView) findViewById(R.id.listView);
//        // Setup cursor adapter using cursor from last step
//        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor);
//        // Attach cursor adapter to the ListView
//        lvItems.setAdapter(todoAdapter);

    }

    //adds data to database
    public void AddTask() {
        but2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = firstdb.insertTask(Integer.parseInt(get_id.getText().toString()), get_task.getText().toString());
                        if (isInserted)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //view all task in database
    public void viewAll() {
        bview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res;
                        res = firstdb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("id: ").append(res.getInt(0)).append("\n");
                            buffer.append("Task: ").append(res.getString(1)).append("\n");
                        }
                        //Show all data
                        showMessage("Data", buffer.toString());
                    }

                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}