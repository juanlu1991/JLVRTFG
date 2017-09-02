package com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.clasificacionFB;
import com.jlvr.juanluis.tfg_championshipleague.NuevoGoleador;
import com.jlvr.juanluis.tfg_championshipleague.R;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.resultadosFB;

public class goleadoresFB extends AppCompatActivity {
    private static final String TAGLOG = "firebase-db";
    private RecyclerView lstGoleadoes;
    private Button bngoleador;
    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goleadores_fb);

        //Referencias a db:
        Query consultaGoleadores =
                FirebaseDatabase.getInstance().getReference()
                        .child("Liga").child("Goleadores")
                        .orderByChild("goles");

        RecyclerView recycler = (RecyclerView) findViewById(R.id.lstgoleadores);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =
                new FirebaseRecyclerAdapter<GoleadoresObject, GoleadoresHolder>(
                        GoleadoresObject.class, R.layout.activity_lista_goleadores, GoleadoresHolder.class, consultaGoleadores) {

                    @Override
                    public void populateViewHolder(GoleadoresHolder resViewHolder, GoleadoresObject res, int position) {
                        resViewHolder.setnombre(res.getnombre());
                        resViewHolder.setgoles(res.getgoles());
                    }
                };
                     final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                        layoutManager.setReverseLayout(true);
                        layoutManager.setStackFromEnd(true);
                        recycler.setLayoutManager(layoutManager);
                        recycler.setAdapter(mAdapter);


        //botonOAnyadi
        bngoleador = (Button)findViewById(R.id.bng);
        //Implementamos el evento click del botón
        bngoleador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(goleadoresFB.this, NuevoGoleador.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });//fin button goleador

    }//fin oncreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menulateral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {

            case R.id.menu_seccion_1:
                Intent intent =
                        new Intent(goleadoresFB.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                break;
            case R.id.menu_seccion_2:
                Intent intent2 =
                        new Intent(goleadoresFB.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent2);
                break;
            case R.id.menu_seccion_3:
                Intent intent3 =
                        new Intent(goleadoresFB.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent3);
                break;



        }

        return super.onOptionsItemSelected(item);
    }
}
