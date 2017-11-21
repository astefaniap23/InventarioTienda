package com.example.stefani.inventariotienda.main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.stefani.inventariotienda.R;
import java.util.List;


/**
 * Created by Stefani on 21/11/2017.
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private EditText txtLogin, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }


    public void insert(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        MyDbHelper usdbh = new MyDbHelper(getApplicationContext());

        SQLiteDatabase db = usdbh.getWritableDatabase();
        //Si la bd se abre correctamente

        Time time = new Time();
        time.setToNow();
        long id = time.toMillis(false);

        if (db != null) {
            Log.i(getClass().getSimpleName(), "base de datos existe");
            //Toast.makeText(getApplicationContext(),"base de datos existe", Toast.LENGTH_SHORT).show();
            db.execSQL("INSERT INTO " + UserContract.UserEntry.TABLE_NAME +
                    " VALUES (" + id +
                    "," + txtLogin.getText().toString() +
                    "," + txtPassword.getText().toString() +
                    "," + "'UserType')");

            Log.i(TAG, "getAllUsersFromDataBase(): "); // read data
            List<User> userList = usdbh.getAllUsersFromDataBase();
            for (User user : userList) {
                String log = "Id: " + user.getId() + " ,Password: " + user.getPassword() +
                        " ,Login: " + user.getLogin() + " ,UserType: " + user.getUserType();
                Log.i(TAG, "UserList: " + log);
            }

        } else {
            Log.i(getClass().getSimpleName(), "base de datos existe");
            //Toast.makeText(getApplicationContext(),"base de datos no existe", Toast.LENGTH_SHORT).show();
        }
        //cerramos la bd

        db.close();
    }


}
