package com.atelem.vlc.upv.mor.eltiempo;

import java.sql.*;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {
    private EditText Poblacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Array [] Consulta (View view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //new Thread(new Runnable() {
           // public void run() {

                Poblacion = findViewById(R.id.editText);
                Log.d("busqueda", String.valueOf(Poblacion));
                String driver = "com.mariadb.jdbc.Driver";

                try {
                    System.out.println("hola1");
                    /*Cargamos el driver del conector JDBC.*/
                    Class.forName(driver).newInstance();
                } catch (Exception e) {

                    e.printStackTrace();
                }

                //INICIALIZACIONES Y DEFINICIONES
                Connection con;
                Statement st;
                ResultSet rs;
                String SQL = "SELECT CPRO, CMUN, NOMBRE FROM Codigo Where='" + Poblacion + "'";
                Array[] out = new Array[20];


                //CONEXIONES
                try {
                    con = DriverManager.getConnection("jdbc:mariadb://servermor.asuscomm.com:1562/eltiempo", "root", "mor");
                    //con = DriverManager.getConnection("jdbc:mysql://servermor.asuscomm.com:1562/eltiempo", "root", "mor");
                    System.out.println("hola2");
                    st = con.createStatement();
                    System.out.println("hola3");
                    rs = st.executeQuery(SQL);
                    for (int i = 0; rs.next(); i++)
                    {
                        out[i] = rs.getArray(i);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        //    }

        //}).start();

        return out;
    }
}
