/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Petro Prydatkevych
 */
public enum Agency {
    NASA("NASA"),
    ESA("European Space Agency"),
    CSA("Canadian Space Agency"),
    JAXA("Japan Aerospace Exploration Agency"),
    ROSCOSMOS("Roscosmos"),
    SPACEX("SpaceX"),
    OTHER("Other");
 
    private final String fullName;
 
    Agency(String fullName) {
        this.fullName = fullName;
    }
 
    public String getFullName() {
        return fullName;
    }
 
    @Override
    public String toString() {
        return fullName;
    }
}
