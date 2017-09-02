package com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jlvr.juanluis.tfg_championshipleague.R;

/**
 * Created by juanluis on 12/05/2017.
 */

public class ClasificacionHolder extends RecyclerView.ViewHolder {

    private  View mViewc;

    public ClasificacionHolder(View itemView) {
        super(itemView);
        mViewc = itemView;

    }

    public  void setnombre(String equipo) {
        TextView field = (TextView) mViewc.findViewById(R.id.equipoC);
        field.setText(equipo);
    }

    public void setPuntos(String puntos) {
        TextView field = (TextView) mViewc.findViewById(R.id.puntosT);
        field.setText((puntos));
    }

    public void setPartidosJugados(String partidos) {
        TextView field = (TextView) mViewc.findViewById(R.id.partidosJ);
        field.setText((partidos));
    }

    public void setPG(String ganados) {
        TextView field = (TextView) mViewc.findViewById(R.id.partidosG);
        field.setText((ganados));
    }

    public void setPE(String empatados) {
        TextView field = (TextView) mViewc.findViewById(R.id.partidosE);
        field.setText((empatados));
    }

    public void setPP(String perdidos) {
        TextView field = (TextView) mViewc.findViewById(R.id.partidosP);
        field.setText((perdidos));
    }


}
