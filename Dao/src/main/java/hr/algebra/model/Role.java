/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.Objects;

/**
 *
 * @author Petro Prydatkevych
 */
public class Role {
    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String USER = "USER";
 
    private int id;
    private String name;
 
    public Role() {
    }
 
    public Role(String name) {
        this.name = name;
    }
 
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role other)) {
            return false;
        }
        return Objects.equals(name, other.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
 
    @Override
    public String toString() {
        return name;
    }
}
