package com.phonedev.dbmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtCodigo, txtProducto, txtPrecio, txtFabricante;
    Button btnAgregar, btnModificar, btnEliminar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigo=(EditText) findViewById(R.id.txtCodigo);
        txtProducto=(EditText) findViewById(R.id.txtProducto);
        txtPrecio=(EditText) findViewById(R.id.txtPrecio);
        txtFabricante=(EditText) findViewById(R.id.txtFabricante);
        btnAgregar=(Button)findViewById(R.id.btnAgregar);
        btnModificar=(Button)findViewById(R.id.btnModificar);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnBuscar=(Button)findViewById(R.id.btnBuscar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ejecutarServicio("http://192.168.1.126:80/pruebasdb/insertar_venta.php");
            }
        });
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", txtCodigo.getText().toString());
                parametros.put("producto", txtProducto.getText().toString());
                parametros.put("precio", txtPrecio.getText().toString());
                parametros.put("fabricante", txtFabricante.getText().toString());
                return super.getParams();
            }
        };
        RequestQueue requestQueue=  Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
