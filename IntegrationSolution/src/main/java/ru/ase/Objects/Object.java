package ru.ase.Objects;

import java.util.ArrayList;

public class Object {

    private ArrayList<Double> startpoint = new ArrayList<>();
    private ArrayList<Double> endPoint = new ArrayList<>();
    private String OID;
    private String name;
    private String type;
    private String grade;
    private String partNumper;
    private Double thickness;
    private Double face;

    public Object(ArrayList<Double> startpoint, ArrayList<Double> endPoint, String OID, String name, String type, String grade, String partNumper, Double thickness, Double face) {
        this.startpoint = startpoint;
        this.endPoint = endPoint;
        this.OID = OID;
        this.name = name;
        this.type = type;
        this.grade = grade;
        this.partNumper = partNumper;
        this.thickness = thickness;
        this.face = face;
    }
}
