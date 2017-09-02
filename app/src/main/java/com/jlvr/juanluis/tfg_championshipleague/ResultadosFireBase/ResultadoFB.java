package com.jlvr.juanluis.tfg_championshipleague.ResultadosFireBase;

import java.util.List;

/**
 * Created by juanluis on 11/05/2017.
 */

public class ResultadoFB {


        private Long GolesVisitante;
        private String EquipoLocal;
        private Long GolesLocal;
        private String Fecha;
        private String EquipoVisitante;

    private List<String> Partidos;



    public ResultadoFB() {
        //Es obligatorio incluir constructor por defecto
    }

    public ResultadoFB(String EquipoLocal,Long GolesLocal,String EquipoVisitante,Long GolesVisitante, String Fecha){
         this.GolesVisitante = GolesVisitante;
        this.EquipoLocal = EquipoLocal;
        this.GolesLocal = GolesLocal;
        this.Fecha = Fecha;
        this.EquipoVisitante=EquipoVisitante;

    }







    public Long getGolesVisitante() {
        return GolesVisitante;
    }

    public void setGolesVisitante(Long golesVisitante) {
        GolesVisitante = golesVisitante;
    }

    public String getEquipoLocal() {
        return EquipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        EquipoLocal = equipoLocal;
    }

    public Long getGolesLocal() {
        return GolesLocal;
    }

    public void setGolesLocal(Long golesLocal) {
        GolesLocal = golesLocal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEquipoVisitante() {
        return EquipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        EquipoVisitante = equipoVisitante;
    }

    public List<String> getPartidos() {
        return Partidos;
    }

    public void setPartidos(String partidos) {
        Partidos.add(partidos);
    }

    @Override
        public String toString() {
            return "Resultado{  Partido: ->" +
                    "EquipoLocal='" + EquipoLocal + '\'' +
                    ", EquipoVisitante=" + EquipoVisitante +
                    ", GolesLocal='" + GolesLocal + '\'' +
                    ", GolesVisitante=" + GolesVisitante +
                    "Fecha " + Fecha +
                    '}';

        }



}
