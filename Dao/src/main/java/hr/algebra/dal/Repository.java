/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Mission;
import hr.algebra.model.NasaItem;
import hr.algebra.model.Role;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;


public interface Repository {

    int createNasaItem(NasaItem article) throws Exception;
 
    void createNasaItems(List<NasaItem> articles) throws Exception;
 
    void updateNasaItem(int id, NasaItem data) throws Exception;
 
    void deleteNasaItem(int id) throws Exception;
 
    Optional<NasaItem> selectNasaItem(int id) throws Exception;
 
    List<NasaItem> selectNasaItems() throws Exception;
 
    void deleteAllNasaItems() throws Exception;
 
    int createMission(Mission mission) throws Exception;
 
    void updateMission(int id, Mission data) throws Exception;
 
    void deleteMission(int id) throws Exception;
 
    Optional<Mission> selectMission(int id) throws Exception;
 
    List<Mission> selectMissions() throws Exception;
 
    void linkNasaItemMission(int idNasaItem, int idMission) throws Exception;
 
    void unlinkNasaItemMission(int idNasaItem, int idMission) throws Exception;
 
    List<Mission> selectMissionsForNasaItem(int idNasaItem) throws Exception;
 
    Optional<Role> selectRoleByName(String name) throws Exception;
 
    int createUser(User user) throws Exception;
 
    Optional<User> selectUserByUsername(String username) throws Exception;
}
