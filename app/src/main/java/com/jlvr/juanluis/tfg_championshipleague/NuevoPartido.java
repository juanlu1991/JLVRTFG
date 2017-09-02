package com.jlvr.juanluis.tfg_championshipleague;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.ClasificacionObjeto;
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.clasificacionFB;
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.goleadoresFB;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.ResultadoFB;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.resultadosFB;

public class NuevoPartido extends AppCompatActivity {


    private Button buttonNP, buttonGuardar;

    private EditText fecha, equipoLocal, equipoVisitante, golesL, golesV;

    private ClasificacionObjeto equipoStored, equipoV;


    private Integer puntosL;
    private Integer puntosV;
    private Integer pgl;
    private Integer pgv;
    private Integer pel;
    private Integer pev;
    private Integer ppl;
    private Integer ppv;
    private Integer pjl;
    private Integer pjv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_partido);

        //botonOAnyadi
        buttonGuardar = (Button) findViewById(R.id.button2);
        //Implementamos el evento click del botón
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fecha = (EditText) findViewById(R.id.nuevaFecha);
                equipoLocal = (EditText) findViewById(R.id.nuevoEquipoLocal);
                equipoVisitante = (EditText) findViewById(R.id.nuevoEquipoVisitante);
                golesL = (EditText) findViewById(R.id.resLocal);
                golesV = (EditText) findViewById(R.id.resVisit);

                String fechaN = String.valueOf(fecha.getText());
                final String equiL = String.valueOf(equipoLocal.getText());
                final String equiV = String.valueOf(equipoVisitante.getText());
                final String gL = String.valueOf(golesL.getText());
                String gV = String.valueOf(golesV.getText());

                if (fechaN.equals(" ") ||
                        equiL.equals("Name") ||
                        equiV.equals("Name")
                        || gL.equals(" ") || gV.equals(" ")) {

                    Toast.makeText(getApplicationContext(),
                            R.string.datosconerror, Toast.LENGTH_LONG).show();
                    return;

                } else {

                    //Añadir partido nuevo en Resultados

                    final DatabaseReference resNuevo =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Resultados");

                    final Long golesLocal, golesVisitante;
                    golesLocal = Long.valueOf(gL);
                    golesVisitante = Long.valueOf(gV);


                    ResultadoFB res =
                            new ResultadoFB(equiL, golesLocal, equiV, golesVisitante, fechaN);

                    String titulopartido = "Resultado" + fechaN + equiL;
                    resNuevo.child(titulopartido).setValue(res);

                    ///Añadir en clasificacion
                    final DatabaseReference resNuevo2 =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Clasificacion");




                    ///////////////////Pruebas pruebas actualizar comprobacion existen
                    final DatabaseReference resNuevo3 =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Clasificacion").child(equiL);

                    DatabaseReference resNuevo4 =
                            FirebaseDatabase.getInstance().getReference().child("Liga")
                                    .child("Clasificacion").child(equiV);

                    final ChildEventListener childEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            ClasificacionObjeto equipoStored = dataSnapshot.getValue(ClasificacionObjeto.class);

                            if (equipoStored == null) {


                                //Si que existe, actualizamos los datos
                            } else {
                                // Identificamos si es local o visitante
                                // Actualizamos los datos de equipoStored con los que han llegado por parametro
                                equipoStored.setPJ(equipoStored.getPJ() + 1);
                                equipoStored.setPuntos(equipoStored.getPuntos());
                                equipoStored.setPG(equipoStored.getPG());
                                equipoStored.setPE(equipoStored.getPE());
                                equipoStored.setPP(equipoStored.getPP());

                                //Ha entrado el equipo local
                                if (equipoStored.getnombre().equals(equiL)) {
                                    if (golesLocal > golesVisitante) {
                                        equipoStored.setPuntos(equipoStored.getPuntos()+3);
                                        equipoStored.setPG(equipoStored.getPG()+1);
                                    } else if (golesLocal.equals(golesVisitante)) {
                                        equipoStored.setPuntos(equipoStored.getPuntos()+1);
                                        equipoStored.setPE(equipoStored.getPE()+1);
                                    } else {
                                        equipoStored.setPP(equipoStored.getPP() + 1);
                                    }
                                       resNuevo2.child(equiL).setValue(equipoStored);

                                    //Ha entrado equipo visitante
                                } else if (equipoStored.getnombre().equals(equiV)) {
                                    if (golesLocal < golesVisitante) {
                                        equipoStored.setPuntos(equipoStored.getPuntos()+3);
                                        equipoStored.setPG(equipoStored.getPG()+1);
                                    } else if (golesLocal.equals(golesVisitante)) {
                                        equipoStored.setPuntos(equipoStored.getPuntos()+1);
                                        equipoStored.setPE(equipoStored.getPE()+1);
                                    } else {
                                        equipoStored.setPP(equipoStored.getPP() + 1);
                                    }
                                    resNuevo2.child(equiV).setValue(equipoStored);
                                } else {
                                    Log.e("Error", "equipos no coinciden");
                                }

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
///////llamada al listene con la los dos equipos para ver si existen
                    ChildEventListener exis=  resNuevo2.orderByKey().equalTo(equiL).addChildEventListener(childEventListener);
                    ChildEventListener exis2=      resNuevo2.orderByKey().equalTo(equiV).addChildEventListener(childEventListener);
//si no existen
                if(!exis.equals(null) && !exis2.equals(null)) {

                    //no existen
                    puntosL = 0;
                    puntosV = 0;
                    pgl = 0;
                    pgv = 0;
                    pel = 0;
                    pev = 0;
                    ppl = 0;
                    ppv = 0;
                    pjl = 0;
                    pjv = 0;


                    ClasificacionObjeto equipo1 = new ClasificacionObjeto(equiL, puntosL, pjl, pgl, pel, ppl);
                    ClasificacionObjeto equipo2 = new ClasificacionObjeto(equiV, puntosV, pjv, pgv, pev, ppv);
                    resNuevo3.setValue(equipo1);
                    resNuevo4.setValue(equipo2);

                }






                }


            }


        });


        buttonNP = (Button) findViewById(R.id.button3);
        //Implementamos el evento click del botón
        buttonNP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent


                Intent intent =
                        new Intent(NuevoPartido.this, NuevoGoleador.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }

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

        switch (item.getItemId()) {

            case R.id.menu_seccion_1:
                Intent intent =
                        new Intent(NuevoPartido.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                break;
            case R.id.menu_seccion_2:
                Intent intent2 =
                        new Intent(NuevoPartido.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent2);
                break;
            case R.id.menu_seccion_3:
                Intent intent3 =
                        new Intent(NuevoPartido.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent3);
                break;


        }

        return super.onOptionsItemSelected(item);
    }


}

