package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends BaseDAO<Credit>{
    public CreditDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Credit object) throws SQLException {
        String req = "insert into Credit (id_client,montant, DateCredit , etat) values (?,?,? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setDouble(1 , object.getId_client());
        this.preparedStatement.setDouble(2 , object.getMontant());
        this.preparedStatement.setDate(3 , object.getDateCredit());
        this.preparedStatement.setBoolean(4 , object.getEtat());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Credit object) throws SQLException {
        String req = " UPDATE Credit SET `montant`=?,`DateCredit`=?,`etat`=? WHERE  `id_credit`=?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setDouble(1 , object.getMontant());
        this.preparedStatement.setDate(2 , object.getDateCredit());
        this.preparedStatement.setBoolean(3 , object.getEtat());
        this.preparedStatement.setLong(4 , object.getId_credit());



        this.preparedStatement.execute();
    }

    @Override
    public void delete(Credit object) throws SQLException {
        String req = " DELETE FROM Credit WHERE id_credit=?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_credit());



        this.preparedStatement.execute();
    }

    @Override
    public Credit getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Credit> getAll() throws SQLException {
        List<Credit> mylist = new ArrayList<Credit>();
        String req = " select * from Credit" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Credit(this.resultSet.getLong(1),this.resultSet.getLong(2) , this.resultSet.getDouble(3),
                    this.resultSet.getDate(4),this.resultSet.getBoolean(5) ));


        }
        return mylist;
    }
}
