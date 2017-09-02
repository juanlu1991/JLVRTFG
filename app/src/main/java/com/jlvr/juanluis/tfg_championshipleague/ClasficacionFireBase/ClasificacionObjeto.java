package com.jlvr.juanluis.tfg_championshipleague.ClasficacionFireBase;

/**
 * Created by juanluis on 12/05/2017.
 */

public class ClasificacionObjeto {

    private Integer Puntos,PJ,PG,PE,PP;
    private String nombre;
    public ClasificacionObjeto(){}

    public ClasificacionObjeto(String Equipo,Integer Puntos,Integer jugados,Integer ganados,Integer empate, Integer perdido){
        this.nombre=Equipo;
        this.Puntos=Puntos;
        this.PJ=jugados;
        this.PG=ganados;
        this.PE=empate;
        this.PP=perdido;
    }

    public Integer getPJ() {
        return PJ;
    }

    public void setPJ(Integer jugados) {
        this.PJ = jugados;
    }

    public Integer getPG() {
        return PG;
    }

    public void setPG(Integer ganados) {
        this.PG = ganados;
    }

    public Integer getPE() {
        return PE;
    }

    public void setPE(Integer empatados) {
        this.PE = empatados;
    }

    public Integer getPP() {
        return PP;
    }
    public void setPP(Integer perdidos) {
        this.PP = perdidos;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String equipo) {
        this.nombre = equipo;
    }

    public Integer getPuntos() {
        return Puntos;
    }

    public void setPuntos(Integer puntos) {
        this.Puntos = puntos;
    }




    @Override
    public String toString() {
        return "Clasificacion{" +
                "puntos=" + Puntos +
                ", jugados=" + PJ +
                ", ganados=" + PG +
                ", empatados=" + PE +
                ", perdidos=" + PP +
                ", equipo='" + nombre + '\'' +
                '}';
    }
}
