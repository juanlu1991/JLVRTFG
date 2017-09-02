package com.jlvr.juanluis.tfg_championshipleague;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase.clasificacionFB;
import com.jlvr.juanluis.tfg_championshipleague.GoleadoresFireBase.goleadoresFB;
import com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase.resultadosFB;

import java.util.Locale;

public class PrincipalActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    ///Menu lateral
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private ListView ndList;
    private ScrimInsetsFrameLayout sifl;
    ///
    private Button button0, button9, button1, buttonIdioma;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textview;
    private Locale locale;
    private Configuration config = new Configuration();


    private SignInButton btnSignIn;
    private Button btnSignOut;
    // private Button btnRevoke;
    private TextView txtNombre;
    private TextView txtEmail;

    private GoogleApiClient apiClient;
    private static final int RC_SIGN_IN = 1001;

    private ProgressDialog progressDialog;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
//////7Menu laterla


        /////////////////////////////////////
        ////////////////////////////////////////
        ////////////////////////
     //  setContentView(R.layout.activity_menu_lateral);
        //setContentView(R.layout.activity_contenido);

        //appbar = (Toolbar)findViewById(R.id.appbar);
        //setSupportActionBar(appbar);

      //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.side_nav_bar);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ////////
        ///////
        //Menu del Navigation Drawer
/*


       drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;
                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Intent intent =
                                        new Intent(PrincipalActivity.this, clasificacionFB.class);
                                //Iniciamos la nueva actividad
                                startActivity(intent);
                                break;
                            case R.id.menu_seccion_2:
                                Intent intent2 =
                                        new Intent(PrincipalActivity.this, resultadosFB.class);
                                //Iniciamos la nueva actividad
                                startActivity(intent2);
                                break;

                        }

                        if(fragmentTransaction) {

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                      //  drawerLayout.closeDrawers();

                        return true;
                    }
                });
*/
/////////////////////////////////////////////////////////////////

        btnSignIn = (SignInButton) findViewById(R.id.sign_in_button);
        btnSignOut = (Button) findViewById(R.id.sign_out_button);
        // btnRevoke = (Button)findViewById(R.id.revoke_button);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

        //API
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();


        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Personalización del botón de login

        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setColorScheme(SignInButton.COLOR_LIGHT);
        btnSignIn.setScopes(gso.getScopeArray());

        //Eventos de los botones

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                button1.setVisibility(View.GONE);
                                button2.setVisibility(View.GONE);
                                button3.setVisibility(View.GONE);
                               // button4.setVisibility(View.GONE);
                                updateUI(false);
                            }
                        });
            }
        });


        updateUI(false);

        /////////////////////////////////////////////


        //PAra un boton primero en xml y segundo en java:





        button1 = (Button) findViewById(R.id.boton1);
        //Implementamos el evento click del botón
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent


                Intent intent =
                        new Intent(PrincipalActivity.this, clasificacionFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
        button2 = (Button) findViewById(R.id.boton2);
        //Implementamos el evento click del botón
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(PrincipalActivity.this, resultadosFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.boton3);
        //Implementamos el evento click del botón
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(PrincipalActivity.this, goleadoresFB.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        button4 = (Button) findViewById(R.id.botonayuda);
        //Implementamos el evento click del botón
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(PrincipalActivity.this, Ayuda.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        //button Idioma
        buttonIdioma = ((Button) findViewById(R.id.buttonIdioma));
        textview = ((TextView) findViewById(R.id.bienvenida));

        buttonIdioma.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }
                });

    }


    //////////////////////////////  respuesta de conectar con google
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Error de conexion!", Toast.LENGTH_SHORT).show();
        Log.e("GoogleSignIn", "OnConnectionFailed: " + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //Usuario logueado --> Mostramos sus datos
            GoogleSignInAccount acct = result.getSignInAccount();
            txtNombre.setText(acct.getDisplayName());
            txtEmail.setText(acct.getEmail());
            updateUI(true);
        } else {
            //Usuario no logueado --> Lo mostramos como "Desconectado"
            updateUI(false);
        }
    }
//hacer visibles cuando conectado
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);

            // btnRevoke.setVisibility(View.VISIBLE);

            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
           // button4.setVisibility(View.VISIBLE);


        } else {
            txtNombre.setText("Desconectado");
            txtEmail.setText("Desconectado");

            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
         //   button4.setVisibility(View.VISIBLE);
            // btnRevoke.setVisibility(View.GONE);

        }
    }
//contectar a google
    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(apiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Silent SignI-In");
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }


    /////////////////////////mostrar dialogo cambio idioma

    private void showDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.str_buttonIdioma));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch (which) {
                    case 0:
                        locale = new Locale("en");
                        config.locale = locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale = locale;
                        break;

                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(PrincipalActivity.this, PrincipalActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }

    /*   @Override
       public boolean onCreateOptionsMenu(Menu menu) {
           // Inflate the menu; this adds items to the action bar if it is present.
           getMenuInflater().inflate(R.menu.menulateral, menu);
           return true;
       }
   */


}
