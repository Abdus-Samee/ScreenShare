package sample;

import java.io.Serializable;

public class robo implements Serializable {
    private double abscissa;
    private double ordinate;

    public void setAbscissa(double x) { abscissa = x; }

    public void setOrdinate(double y) { ordinate = y; }

    public double getAbscissa() { return abscissa; }

    public double getOrdinate() { return ordinate; }
}
