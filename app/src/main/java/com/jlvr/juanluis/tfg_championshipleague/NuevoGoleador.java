package com.jlvr.juanluis.tfg_championshipleague;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.clasificacionFB;
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.GoleadoresObject;
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.goleadoresFB;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.resultadosFB;

public class NuevoGoleador extends AppCompatActivity {

    private Button buttonGuardar;

    private EditText jugador,goles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_goleador);


        buttonGuardar = (Button)findViewById(R.id.button2);
        //Implementamos el evento click del botón
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jugador = (EditText) findViewById(R.id.nombreJG);
                goles = (EditText) findViewById(R.id.golesAnotado);
                final String j = String.valueOf(jugador.getText());
                final String g = String.valueOf(goles.getText());

                if(g.equals("") ||
                        j.equals("Name")||
                        j.equals("") ){

                    Toast.makeText(getApplicationContext(),
                            R.string.datosconerror, Toast.LENGTH_LONG).show();
                    return;

                }else{
                    //Añadir pnuevos goleadroes

                    final DatabaseReference newGole =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Goleadores");

                    final DatabaseReference resNuevo3 =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Goleadores").child("Goleador"+j);

                    final ChildEventListener childEventListener = new ChildEventListener() {


                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            GoleadoresObject goleobj = dataSnapshot.getValue(GoleadoresObject.class);
                            if(goleobj==null){
                                //no existe gloleador




                            }else{
                                //existe
                                goleobj.setgoles(goleobj.getgoles()+Long.valueOf(g));
                                goleobj.setnombre((goleobj.getnombre()));
                                String nodogoleadoreso = "Goleador"+j;

                                newGole.child(nodogoleadoreso).setValue(goleobj);


                            }
                            Toast.makeText(getApplicationContext(),
                                    R.string.datossinerror, Toast.LENGTH_LONG).show();
                            return;
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };

                    ChildEventListener exis4=  newGole.orderByKey().equalTo("Goleador"+j).addChildEventListener(childEventListener);

                    if(!exis4.equals(null) ) {
                        GoleadoresObject equipo2 = new GoleadoresObject(j, 0L);

                        resNuevo3.setValue(equipo2);

                    }

                   }//fin else







            }//fin onclick



        });//fin bton guardar



            }//fin oncreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menulateral, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {

            case R.id.menu_seccion_1:
                Intent intent =
                        new Intent(NuevoGoleador.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                break;
            case R.id.menu_seccion_2:
                Intent intent2 =
                        new Intent(NuevoGoleador.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent2);
                break;
            case R.id.menu_seccion_3:
                Intent intent3 =
                        new Intent(NuevoGoleador.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent3);
                break;



        }

        return super.onOptionsItemSelected(item);
    }

    }

