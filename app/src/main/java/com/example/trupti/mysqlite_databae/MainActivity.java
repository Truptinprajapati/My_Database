package com.example.trupti.mysqlite_databae;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Mydatabasehelper mydb;

    EditText e_id, e_fname, e_lname, e_marks;
    Button btninsert, btnupdate, btnview, btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new Mydatabasehelper(this);

        e_id = (EditText) findViewById(R.id.u_id);
        e_fname = (EditText) findViewById(R.id.fname);
        e_lname = (EditText) findViewById(R.id.lname);
        e_marks = (EditText) findViewById(R.id.marks);




        btninsert = (Button) findViewById(R.id.insert);
        btnview = (Button)findViewById(R.id.view);
        btnupdate=(Button)findViewById(R.id.update);
        btndelete=(Button)findViewById(R.id.delete);

        AddData();
        viewAll();
        UpdataData();
        DeleteData();

    }


    //....................................................................................................
    private void DeleteData() {
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleterow = mydb.deleteData(e_id.getText().toString());
                if (deleterow > 0)
                {
                    Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Data not deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //..................................................................................................


    private void UpdataData() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate=mydb.updataeData(e_id.getText().toString(),
                        e_fname.getText().toString(),
                        e_lname.getText().toString(),
                        e_marks.getText().toString());
                if (isupdate == true)
                {
                    Toast.makeText(MainActivity.this,"data successfull updated",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



    //....................................................................................................
    private void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr=mydb.getAllData();
                if (cr.getCount()==0)
                {
                    showMessage("Error","Nothing found");
                    return;

                }
                    StringBuffer buffer=new  StringBuffer();
                    while ((cr.moveToNext()))
                    {
                        buffer.append("ID :"+cr.getString(0)+"\n");
                        buffer.append("FName :"+cr.getString(1)+"\n");
                        buffer.append("Lname :"+cr.getString(2)+"\n");
                        buffer.append("Marks :"+cr.getString(3)+"\n");
                    }
                    showMessage("Data",buffer.toString());
            }
        });


    }


    //.........................................................................................

    private void AddData() {
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = mydb.insertData(e_fname.getText().toString(),
                e_lname.getText().toString(),
                e_marks.getText().toString());

                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    //....................................................................................................
    public  void showMessage(String title,String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}












