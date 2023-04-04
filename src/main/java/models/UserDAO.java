package models;

import java.sql.SQLException;
import java.util.List;

public class UserDAO extends BaseDAO<User>{
    public UserDAO() throws SQLException {
        super();
    }
    public Boolean inspect(User object) throws SQLException {
        String req= "SELECT * FROM User WHERE nom = ? AND motdepasse=?;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getMotdepasse());


        return this.preparedStatement.executeQuery().next();
    }

    @Override
    public void save(User object) throws SQLException {

    }

    @Override
    public void update(User object) throws SQLException {

    }

    @Override
    public void delete(User object) throws SQLException {

    }

    @Override
    public User getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }
}
