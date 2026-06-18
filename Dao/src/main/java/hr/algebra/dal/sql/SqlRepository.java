/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Agency;
import hr.algebra.model.Mission;
import hr.algebra.model.NasaItem;
import hr.algebra.model.Role;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_ARTICLE = "IDNasaItem";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";
 
    private static final String ID_MISSION = "IDMission";
    private static final String NAME = "Name";
    private static final String CODE = "Code";
    private static final String AGENCY = "Agency";
    private static final String LAUNCH_YEAR = "LaunchYear";
 
    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD_HASH = "PasswordHash";
    private static final String ID_ROLE = "IDRole";
    private static final String ROLE_NAME = "RoleName";
 
    private static final String CREATE_ARTICLE = "{ CALL createNasaItem (?,?,?,?,?,?) }";
    private static final String UPDATE_ARTICLE = "{ CALL updateNasaItem (?,?,?,?,?,?) }";
    private static final String DELETE_ARTICLE = "{ CALL deleteNasaItem (?) }";
    private static final String SELECT_ARTICLE = "{ CALL selectNasaItem (?) }";
    private static final String SELECT_ARTICLES = "{ CALL selectNasaItems }";
    private static final String DELETE_ALL_ARTICLES = "{ CALL deleteAllNasaItems }";
 
    private static final String CREATE_MISSION = "{ CALL createMission (?,?,?,?,?) }";
    private static final String UPDATE_MISSION = "{ CALL updateMission (?,?,?,?,?) }";
    private static final String DELETE_MISSION = "{ CALL deleteMission (?) }";
    private static final String SELECT_MISSION = "{ CALL selectMission (?) }";
    private static final String SELECT_MISSIONS = "{ CALL selectMissions }";
 
    private static final String LINK_NASA_ITEM_MISSION = "{ CALL linkNasaItemMission (?,?) }";
    private static final String UNLINK_NASA_ITEM_MISSION = "{ CALL unlinkNasaItemMission (?,?) }";
    private static final String SELECT_MISSIONS_FOR_NASA_ITEM = "{ CALL selectMissionsForNasaItem (?) }";
 
    private static final String SELECT_ROLE_BY_NAME = "{ CALL selectRoleByName (?) }";
    private static final String CREATE_USER = "{ CALL createUser (?,?,?,?) }";
    private static final String SELECT_USER_BY_USERNAME = "{ CALL selectUserByUsername (?) }";
 
    
    @Override
    public int createNasaItem(NasaItem article) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ARTICLE)) {
            stmt.setString(TITLE, article.getTitle());
            stmt.setString(LINK, article.getLink());
            stmt.setString(DESCRIPTION, article.getDescription());
            stmt.setString(PICTURE_PATH, article.getPicturePath());
            stmt.setString(PUBLISHED_DATE, article.getPublishedDate().format(NasaItem.DATE_FORMATTER));
            stmt.registerOutParameter(ID_ARTICLE, Types.INTEGER);
 
            stmt.executeUpdate();
            return stmt.getInt(ID_ARTICLE);
        }
    }
 
    @Override
    public void createNasaItems(List<NasaItem> articles) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ARTICLE)) {
            for (NasaItem article : articles) {
                stmt.setString(TITLE, article.getTitle());
                stmt.setString(LINK, article.getLink());
                stmt.setString(DESCRIPTION, article.getDescription());
                stmt.setString(PICTURE_PATH, article.getPicturePath());
                stmt.setString(PUBLISHED_DATE, article.getPublishedDate().format(NasaItem.DATE_FORMATTER));
                stmt.registerOutParameter(ID_ARTICLE, Types.INTEGER);
 
                stmt.executeUpdate();
            }
        }
    }
 
    @Override
    public void updateNasaItem(int id, NasaItem data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ARTICLE)) {
            stmt.setString(TITLE, data.getTitle());
            stmt.setString(LINK, data.getLink());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.setString(PICTURE_PATH, data.getPicturePath());
            stmt.setString(PUBLISHED_DATE, data.getPublishedDate().format(NasaItem.DATE_FORMATTER));
            stmt.setInt(ID_ARTICLE, id);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public void deleteNasaItem(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ARTICLE)) {
            stmt.setInt(ID_ARTICLE, id);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public Optional<NasaItem> selectNasaItem(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ARTICLE)) {
            stmt.setInt(ID_ARTICLE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapNasaItem(rs));
                }
            }
        }
        return Optional.empty();
    }
 
    @Override
    public List<NasaItem> selectNasaItems() throws Exception {
        List<NasaItem> articles = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ARTICLES); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                articles.add(mapNasaItem(rs));
            }
        }
        return articles;
    }
 
    @Override
    public void deleteAllNasaItems() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_ARTICLES)) {
            stmt.executeUpdate();
        }
    }
 
    @Override
    public int createMission(Mission mission) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MISSION)) {
            bindMission(stmt, mission);
            stmt.registerOutParameter(ID_MISSION, Types.INTEGER);
 
            stmt.executeUpdate();
            return stmt.getInt(ID_MISSION);
        }
    }
 
    @Override
    public void updateMission(int id, Mission data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MISSION)) {
            bindMission(stmt, data);
            stmt.setInt(ID_MISSION, id);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public void deleteMission(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MISSION)) {
            stmt.setInt(ID_MISSION, id);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public Optional<Mission> selectMission(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MISSION)) {
            stmt.setInt(ID_MISSION, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapMission(rs));
                }
            }
        }
        return Optional.empty();
    }
 
    @Override
    public List<Mission> selectMissions() throws Exception {
        List<Mission> missions = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MISSIONS); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                missions.add(mapMission(rs));
            }
        }
        return missions;
    }
 
    @Override
    public void linkNasaItemMission(int idNasaItem, int idMission) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(LINK_NASA_ITEM_MISSION)) {
            stmt.setInt(ID_ARTICLE, idNasaItem);
            stmt.setInt(ID_MISSION, idMission);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public void unlinkNasaItemMission(int idNasaItem, int idMission) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UNLINK_NASA_ITEM_MISSION)) {
            stmt.setInt(ID_ARTICLE, idNasaItem);
            stmt.setInt(ID_MISSION, idMission);
 
            stmt.executeUpdate();
        }
    }
 
    @Override
    public List<Mission> selectMissionsForNasaItem(int idNasaItem) throws Exception {
        List<Mission> missions = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MISSIONS_FOR_NASA_ITEM)) {
            stmt.setInt(ID_ARTICLE, idNasaItem);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    missions.add(mapMission(rs));
                }
            }
        }
        return missions;
    }
 
    @Override
    public Optional<Role> selectRoleByName(String name) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ROLE_BY_NAME)) {
            stmt.setString(NAME, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Role(rs.getInt(ID_ROLE), rs.getString(NAME)));
                }
            }
        }
        return Optional.empty();
    }
 
    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORD_HASH, user.getPasswordHash());
            stmt.setInt(ID_ROLE, user.getRole().getId());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
 
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }
 
    @Override
    public Optional<User> selectUserByUsername(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER_BY_USERNAME)) {
            stmt.setString(USERNAME, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role(rs.getInt(ID_ROLE), rs.getString(ROLE_NAME));
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD_HASH),
                            role));
                }
            }
        }
        return Optional.empty();
    }
 
    private NasaItem mapNasaItem(ResultSet rs) throws SQLException {
        return new NasaItem(
                rs.getInt(ID_ARTICLE),
                rs.getString(TITLE),
                rs.getString(LINK),
                rs.getString(DESCRIPTION),
                rs.getString(PICTURE_PATH),
                LocalDateTime.parse(rs.getString(PUBLISHED_DATE), NasaItem.DATE_FORMATTER));
    }
 
    private Mission mapMission(ResultSet rs) throws SQLException {
        int year = rs.getInt(LAUNCH_YEAR);
        Integer launchYear = rs.wasNull() ? null : year;
        return new Mission(
                rs.getInt(ID_MISSION),
                rs.getString(NAME),
                rs.getString(CODE),
                parseAgency(rs.getString(AGENCY)),
                launchYear);
    }
 
    private void bindMission(CallableStatement stmt, Mission mission) throws SQLException {
        stmt.setString(NAME, mission.getName());
        stmt.setString(CODE, mission.getCode());
        stmt.setString(AGENCY, mission.getAgency() == null ? null : mission.getAgency().name());
        if (mission.getLaunchYear() == null) {
            stmt.setNull(LAUNCH_YEAR, Types.INTEGER);
        } else {
            stmt.setInt(LAUNCH_YEAR, mission.getLaunchYear());
        }
    }
 
    private static Agency parseAgency(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Agency.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return Agency.OTHER;
        }
    }
}
