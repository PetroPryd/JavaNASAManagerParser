/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Petro Prydatkevych
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "code", "agency", "launchYear"})
public class Mission implements Comparable<Mission> {
 
    @XmlAttribute
    private int id;
    private String name;
    private String code;
    private Agency agency;
    private Integer launchYear;
 
    public Mission() {
    }
 
    public Mission(String name, String code, Agency agency, Integer launchYear) {
        this.name = name;
        this.code = code;
        this.agency = agency;
        this.launchYear = launchYear;
    }
 
    public Mission(int id, String name, String code, Agency agency, Integer launchYear) {
        this(name, code, agency, launchYear);
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public Agency getAgency() {
        return agency;
    }
 
    public void setAgency(Agency agency) {
        this.agency = agency;
    }
 
    public Integer getLaunchYear() {
        return launchYear;
    }
 
    public void setLaunchYear(Integer launchYear) {
        this.launchYear = launchYear;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mission other)) {
            return false;
        }
        return Objects.equals(name, other.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
 
    @Override
    public int compareTo(Mission o) {
        return String.valueOf(name).compareToIgnoreCase(String.valueOf(o.name));
    }
 
    @Override
    public String toString() {
        return name;
    }
}
