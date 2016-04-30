package com.example.seth3511.coil_calculator;

/**
 * Created by Seth3511 on 4/29/2016.
 */
public class Calculator {

    double guage;
    double resistence;
    double diameter;
    double rho;

    public Calculator(){
        rho=1.45;
    }

    public void setResistence(double resistence){
        this.resistence=resistence;
    }

    public void setDiameter(double diameter){
        this.diameter=diameter;
    }

    public void setGuage(double guage){
        this.guage=0.127*Math.pow(92,((36-guage)/39));
    }

    public int calculate(){
        double length=(Math.pow(guage/2,2)*Math.PI*resistence)/rho;
        double circumference=diameter*2*Math.PI;

        return (int)(length/circumference);
    }
}
