package repositpry;

import entity.Category;
import entity.Product;

import java.util.List;

public interface IProductRepository <E>{
    List<E> getAll();

    List<Category> getAllCategory();

    void add(E product);

    E findById(int id);

    void update(int id,E product);

    void delete(int id);


    List<Product> findByName(String username);

    List<Product> findByPrice(double price);
}
