package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {

    public ProduitDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Produit object) throws SQLException {
        String req = "insert into Produit (nomProduit, fornisseur) values (?,?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNomProduit());
        this.preparedStatement.setString(2 , object.getFornisseur());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit object) throws SQLException {
        String req = " UPDATE Produit SET `nomProduit`=?,`fornisseur`=? WHERE  `id_produit`=?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNomProduit());
        this.preparedStatement.setString(2 , object.getFornisseur());
        this.preparedStatement.setLong(3,object.getId_produit());



        this.preparedStatement.execute();
    }

    @Override
    public void delete(Produit object) throws SQLException {
        String req = " DELETE FROM Produit WHERE id_produit=?;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1 , object.getId_produit());



        this.preparedStatement.execute();
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> mylist = new ArrayList<Produit>();
        String req = " select * from Produit" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Produit(this.resultSet.getLong(1) , this.resultSet.getString(2),
                    this.resultSet.getString(3)));


        }
        return mylist;
    }
}
