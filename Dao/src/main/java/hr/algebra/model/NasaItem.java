/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "link", "description", "picturePath", "publishedDate", "missions"})
public final class NasaItem implements Comparable<NasaItem> {
 
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
 
    @XmlAttribute
    private int id;
    private String title;
    private String link;
    private String description;
    private String picturePath;
 
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    private LocalDateTime publishedDate;
 
    @XmlElementWrapper(name = "missions")
    @XmlElement(name = "mission")
    private Set<Mission> missions = new TreeSet<>();
 
    public NasaItem() {
    }
 
    public NasaItem(String title, String link, String description, String picturePath, LocalDateTime publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
    }
 
    public NasaItem(int id, String title, String link, String description, String picturePath, LocalDateTime publishedDate) {
        this(title, link, description, picturePath, publishedDate);
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getLink() {
        return link;
    }
 
    public void setLink(String link) {
        this.link = link;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getPicturePath() {
        return picturePath;
    }
 
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
 
    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }
 
    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }
 
    public Set<Mission> getMissions() {
        return missions;
    }
 
    public void setMissions(Set<Mission> missions) {
        this.missions = (missions == null) ? new TreeSet<>() : missions;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NasaItem other)) {
            return false;
        }
        return Objects.equals(link, other.link);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
 
    @Override
    public int compareTo(NasaItem o) {
        if (publishedDate == null && o.publishedDate == null) {
            return 0;
        }
        if (publishedDate == null) {
            return 1;
        }
        if (o.publishedDate == null) {
            return -1;
        }
        return o.publishedDate.compareTo(publishedDate);
    }
 
    @Override
    public String toString() {
        return id + " - " + title;
    }
}

