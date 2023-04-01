package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends  BaseDAO<Client> {
    public ClientDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Client object) throws SQLException {
        String req = "insert into Client (nom, prenom , NumTelephone) values (?,? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getPrenom());
        this.preparedStatement.setString(3 , object.getNumTelephone());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Client object) throws SQLException {
        String req = " UPDATE Client SET `nom`=?,`prenom`=?,`NumTelephone`=? WHERE  `id_client`=?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getPrenom());
        this.preparedStatement.setString(3 , object.getNumTelephone());
        this.preparedStatement.setLong(4,object.getId_client());



        this.preparedStatement.execute();
    }

    @Override
    public void delete(Client object) throws SQLException {
        String req = " DELETE FROM Client WHERE id_client=?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_client());



        this.preparedStatement.execute();
    }

    @Override
    public Client getOne(Long id) throws SQLException {
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Client> getAll() throws SQLException{
        List<Client> mylist = new ArrayList<Client>();
        String req = " select * from Client" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){
            Long id= this.resultSet.getLong(1);
            mylist.add( new Client(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3),this.resultSet.getString(4)));


        }
        return mylist;

    }
    public List<Credit> getCredit( Client object) throws SQLException {
        List<Credit> mylist = new ArrayList<Credit>();
        String req = " select * from Credit WHERE id_client = ?" ;

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_client());
        this.resultSet =  this.preparedStatement.executeQuery();

        while (this.resultSet.next()){

            mylist.add( new Credit(this.resultSet.getLong(1),this.resultSet.getLong(2) , this.resultSet.getDouble(3),
                    this.resultSet.getDate(4),this.resultSet.getBoolean(5) ));


        }
        return mylist;
    }
    public double getCreditTotal(Client object) throws SQLException {
        String req="SELECT SUM(montant) FROM Credit WHERE id_client=?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_client());



        return this.preparedStatement.executeQuery().getDouble(1);
    }
}