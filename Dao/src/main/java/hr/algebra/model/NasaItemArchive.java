/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petro Prydatkevych
 */
@XmlRootElement(name = "nasaitemarchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class NasaItemArchive {
 
    @XmlElementWrapper(name = "nasaitems")
    @XmlElement(name = "nasaitem")
    private List<NasaItem> nasaItems = new ArrayList<>();
 
    public NasaItemArchive() {
    }
 
    public NasaItemArchive(List<NasaItem> nasaItems) {
        this.nasaItems = nasaItems;
    }
 
    public List<NasaItem> getNasaItems() {
        return nasaItems;
    }
}
