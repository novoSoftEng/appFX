package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {
    public CommandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Commande object) throws SQLException {
        String req = "insert into Commande (id_client, dateCommande) values (?,?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1 , object.getId_client());
        this.preparedStatement.setDate(2 , object.getDateCommande());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Commande object) throws SQLException {
        String req = " UPDATE Commande SET 'id_client'=?,`dateCommande`=? WHERE  `id_commande`=?";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1 , object.getId_client());
        this.preparedStatement.setDate(2 , object.getDateCommande());
        this.preparedStatement.setLong(3 , object.getId_commande());



        this.preparedStatement.execute();
    }

    @Override
    public void delete(Commande object) throws SQLException {
        String req = " DELETE FROM Commande WHERE id_commande=?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_commande());



        this.preparedStatement.execute();

    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> mylist = new ArrayList<Commande>();
        String req = " select * from Commande" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add(new Commande(this.resultSet.getLong(1),this.resultSet.getLong(2),this.resultSet.getDate(3)));


        }
        return mylist;
    }
    }


