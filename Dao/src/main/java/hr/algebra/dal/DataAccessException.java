/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

/**
 *
 * @author Petro Prydatkevych
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
 
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
