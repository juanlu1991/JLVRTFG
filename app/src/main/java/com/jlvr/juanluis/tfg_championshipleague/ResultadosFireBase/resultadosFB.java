package com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.clasificacionFB;
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.goleadoresFB;
import com.jlvr.juanluis.tfg_championshipleague.NuevoPartido;
import com.jlvr.juanluis.tfg_championshipleague.R;

public class resultadosFB extends AppCompatActivity {


        private static final String TAGLOG = "firebase-db";

        private RecyclerView lstResultados;

        FirebaseRecyclerAdapter mAdapter;

    private Button buttonOrdenar,buttonAnyadir,buttonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_fb);


        //Referencias a db:
        DatabaseReference dbResultados =
                FirebaseDatabase.getInstance().getReference().child("Liga")
                        .child("Resultados");

        RecyclerView recycler = (RecyclerView) findViewById(R.id.lstResultados);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =
                new FirebaseRecyclerAdapter<ResultadoFB, ResultadoFBHolder>(
                        ResultadoFB.class, R.layout.activity_lista_result, ResultadoFBHolder.class, dbResultados) {

                    @Override
                    public void populateViewHolder(ResultadoFBHolder resViewHolder, ResultadoFB res, int position) {
                        resViewHolder.setFecha(res.getFecha());
                        resViewHolder.setEquipoL(res.getEquipoLocal()+": ");
                        resViewHolder.setGolesLocal(res.getGolesLocal() + ".");
                        resViewHolder.setEquipoVisitante(res.getEquipoVisitante() + ": " );
                        resViewHolder.setGolesVisitante(res.getGolesVisitante() +"." );
                    }

                };
        recycler.setAdapter(mAdapter);



        //botonOrdenar
        buttonOrdenar = (Button)findViewById(R.id.buttonOrdenar);
        //Implementamos el evento click del botón
        buttonOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query consultaOrdena =
                        FirebaseDatabase.getInstance().getReference()
                                .child("Liga").child("Resultados")
                                .orderByChild("fecha");
                RecyclerView recyclerO = (RecyclerView) findViewById(R.id.lstResultados);
                FirebaseRecyclerAdapter mAdapter2;
                mAdapter2 =
                        new FirebaseRecyclerAdapter<ResultadoFB, ResultadoFBHolder>(
                                ResultadoFB.class, R.layout.activity_lista_result, ResultadoFBHolder.class, consultaOrdena) {

                            @Override
                            public void populateViewHolder(ResultadoFBHolder resViewHolder, ResultadoFB res, int position) {
                                resViewHolder.setFecha(res.getFecha());
                                resViewHolder.setEquipoL(res.getEquipoLocal()+": ");
                                resViewHolder.setGolesLocal(res.getGolesLocal() + ".");
                                resViewHolder.setEquipoVisitante(res.getEquipoVisitante() + ": " );
                                resViewHolder.setGolesVisitante(res.getGolesVisitante() +"." );
                            }

                        };
                recyclerO.setAdapter(mAdapter2);
            }
        });

        //botonOAnyadi
        buttonAnyadir = (Button)findViewById(R.id.buttonAnyadi);
        //Implementamos el evento click del botón
        buttonAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                //Creamos el Intent


                    Intent intent =
                            new Intent(resultadosFB.this, NuevoPartido.class);
                    //Iniciamos la nueva actividad
                    startActivity(intent);


            }
        });

        //botonEliminar
        /*
        buttonEliminar = (Button)findViewById(R.id.buttonEliminar);
        //Implementamos el evento click del botón
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference resNuevo =
                        FirebaseDatabase.getInstance().getReference().child("Liga")
                                .child("Resultados");
                resNuevo.child("partido5").removeValue();
            }
        });*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
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
                        new Intent(resultadosFB.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                break;
            case R.id.menu_seccion_2:
                Intent intent2 =
                        new Intent(resultadosFB.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent2);
                break;
            case R.id.menu_seccion_3:
                Intent intent3 =
                        new Intent(resultadosFB.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent3);
                break;



        }

        return super.onOptionsItemSelected(item);
    }
}
