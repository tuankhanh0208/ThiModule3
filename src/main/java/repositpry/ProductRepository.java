package repositpry;

import connect.ConnectDatabase;
import entity.Category;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository<Product> {
    private final Connection connection;

    public ProductRepository() throws SQLException {
        this.connection = ConnectDatabase.getConnection();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select p.id as id, p.name as name, p.price as price, " +
                "p.quantity as quantity, p.color as color, p.description as description, " +
                "c.cid as cid, c.categoryName as categoryName " +
                "from product p " +
                "left join category c on p.categoryID = c.cid";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("color"),
                        resultSet.getString("description"),
                        category
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void add(Product product) {
        String sql = "insert into product (name,price,quantity,color,description,CategoryID) values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId().getCid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "select p.id as id, p.name as name, p.price as price, " +
                "p.quantity as quantity, p.color as color, p.description as description, " +
                "c.cid as cid, c.categoryName as categoryName " +
                "from product p " +
                "left join category c on p.categoryID = c.cid" +
                "where p.id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category categories = new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("color"),
                        resultSet.getString("description"),
                        categories
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void update(int id,Product product) {
        String sql = "update product set name=?,price=?,quantity=?,color=?,description=?,CategoryID=? where id=? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId().getCid());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(int id) {
        String sql = "delete from product where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Product> findByName(String username){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id AS id, p.name AS name, p.price AS price, " +
                "p.quantity AS quantity, p.color AS color, p.description AS description, " +
                "c.cid AS cid, c.categoryName AS categoryName " +
                "FROM product p " +
                "LEFT JOIN category c ON p.categoryID = c.cid " +  // Thêm khoảng trắng ở đây
                "WHERE LOWER(p.name) LIKE LOWER(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + username +"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category categories = new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                Product product =  new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("color"),
                        resultSet.getString("description"),
                        categories
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    @Override
    public List<Product> findByPrice(double price){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id AS id, p.name AS name, p.price AS price, " +
                "p.quantity AS quantity, p.color AS color, p.description AS description, " +
                "c.cid AS cid, c.categoryName AS categoryName " +
                "FROM product p " +
                "LEFT JOIN category c ON p.categoryID = c.cid " +  // Thêm khoảng trắng ở đây
                "WHERE price =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + price +"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category categories = new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                Product product =  new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("color"),
                        resultSet.getString("description"),
                        categories
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
