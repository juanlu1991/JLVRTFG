package com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase;

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
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.goleadoresFB;
import com.jlvr.juanluis.tfg_championshipleague.R;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.resultadosFB;

public class clasificacionFB extends AppCompatActivity {

    private  final String TAGLOG = "firebase-db";
    private RecyclerView lstClasificacion;
    FirebaseRecyclerAdapter adaptadorClasificacion;
    private Button buttonOrdenarC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacionfb);


        //Referencias a db:
        Query dbclasifcacion =
                FirebaseDatabase.getInstance().getReference()
                        .child("Liga").child("Clasificacion").orderByChild("puntos");

         RecyclerView recycler = (RecyclerView) findViewById(R.id.lstClasificacion);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adaptadorClasificacion =
                new FirebaseRecyclerAdapter<ClasificacionObjeto, ClasificacionHolder>(
                        ClasificacionObjeto.class, R.layout.activity_lista__clasificacion, ClasificacionHolder.class, dbclasifcacion) {


                    @Override
                    public void populateViewHolder(ClasificacionHolder resViewHolder, ClasificacionObjeto res, int position) {

                        resViewHolder.setnombre(res.getnombre()+" :");
                        resViewHolder.setPuntos(res.getPuntos()+".");
                        resViewHolder.setPartidosJugados(res.getPJ()+".");
                        resViewHolder.setPG(res.getPG()+".");
                        resViewHolder.setPE(res.getPE()+".");
                        resViewHolder.setPP(res.getPP()+".");

                    }
                };

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adaptadorClasificacion);

        //  recycler.notifyAll();

        //botonOrdenar
        buttonOrdenarC = (Button)findViewById(R.id.buttonOrdenarC);
        //Implementamos el evento click del botón

        buttonOrdenarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =
                        new Intent(clasificacionFB.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);


                /*   Query consultaOrdenaC =
                        FirebaseDatabase.getInstance().getReference()
                                .child("Liga").child("Clasificacion")
                                .orderByChild("puntos");
                RecyclerView recyclerO = (RecyclerView) findViewById(R.id.lstClasificacion);

                FirebaseRecyclerAdapter mAdapter3;
                mAdapter3 =
                        new FirebaseRecyclerAdapter<ClasificacionObjeto, ClasificacionHolder>(
                                ClasificacionObjeto.class, R.layout.activity_lista__clasificacion, ClasificacionHolder.class, consultaOrdenaC) {

                            @Override
                            public void populateViewHolder(ClasificacionHolder resViewHolder, ClasificacionObjeto res, int position) {
                                resViewHolder.setnombre(res.getnombre()+" :");
                                resViewHolder.setPuntos(res.getPuntos()+".");
                                resViewHolder.setPartidosJugados(res.getPJ()+".");
                                resViewHolder.setPG(res.getPG()+".");
                                resViewHolder.setPE(res.getPE()+".");
                                resViewHolder.setPP(res.getPP()+".");

                            }
                        };


                recyclerO.setAdapter(mAdapter3);*/
            }


        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adaptadorClasificacion.cleanup();
    }


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
                        new Intent(clasificacionFB.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                break;
            case R.id.menu_seccion_2:
                Intent intent2 =
                        new Intent(clasificacionFB.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent2);
                break;


            case R.id.menu_seccion_3:
                Intent intent3 =
                        new Intent(clasificacionFB.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

