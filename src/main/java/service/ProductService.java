package service;

import entity.Category;
import entity.Product;
import repositpry.IProductRepository;
import repositpry.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements IProductService<Product> {
    private final IProductRepository<Product> iProductRepository = new ProductRepository();

    public ProductService() throws SQLException {
    }

    @Override
    public List<Product> getAll() {
        return this.iProductRepository.getAll();
    }

    @Override
    public List<Category> getAllCategory() {
        return this.iProductRepository.getAllCategory();
    }

    @Override
    public void add(Product product) {
        this.iProductRepository.add(product);
    }

    @Override
    public Product findById(int id) {
        return this.iProductRepository.findById(id);
    }

    @Override
    public void update(int id,Product product) {
        this.iProductRepository.update(id,product);
    }

    @Override
    public void delete(int id) {
        this.iProductRepository.delete(id);
    }

    @Override
    public List<Product> findByName(String username) {
        return this.iProductRepository.findByName(username);
    }

    @Override
    public List<Product> findByPrice(double price) {
        return this.iProductRepository.findByPrice(price);
    }
}
