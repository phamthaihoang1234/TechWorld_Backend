package com.example.TechWorld.repository;

import java.util.List;

import com.example.TechWorld.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TechWorld.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "select * from products p\r\n" + //
            "order by entered_date, sold desc\r\n" + //
            "limit 10")
    List<Product> getTrendingProducts();

    @Query(nativeQuery = true, value = "select * from products p order by sold desc limit 10")
    List<Product> getBestSellerProducts();

    @Query(nativeQuery = true, value = "select * from products p order by entered_date desc limit 10")
    List<Product> getHighlightProducts();


    List<Product> findByStatusTrue();

    List<Product> findByStatusTrueOrderBySoldDesc();

    List<Product> findByStatusTrueOrderByQuantityDesc();

    List<Product> findByStatusTrueOrderByEnteredDateDesc();

    List<Product> findByCategory (Category cate);

    Product findByProductIdAndStatusTrue(Long id);

    @Query(value = "Select p.* From products p \r\n"
            + "left join rates r on p.product_id = r.product_id\r\n"
            + "group by p.product_id , p.name\r\n"
            + "Order by  avg(r.rating) desc, RAND()", nativeQuery = true)
    List<Product> findProductRated();


    @Query(value = "select * \n" +
            "from products as p\n" +
            "left join rates as r on p.product_id = r.product_id\n" +
            "group by p.product_id , p.name\n" +
            "order by avg( r.rating ) desc , rand() ", nativeQuery = true)
    List<Product> findProductByRated();

    @Query(value = "(Select p.*, avg(r.rating) Rate From products p \r\n"
            + "left join rates r on p.product_id = r.product_id\r\n"
            + "Where (p.category_id = ?) and (p.product_id != ?)\r\n"
            + "group by p.product_id , p.name)\r\n"
            + "union\r\n"
            + "(Select p.*, avg(r.rating) Rate From products p \r\n"
            + "left join rates r on p.product_id = r.product_id\r\n"
            + "Where p.category_id != ?\r\n"
            + "group by p.product_id , p.name)\r\n"
            + "Order by category_id = ? desc, Rate desc", nativeQuery = true)
    List<Product> findProductSuggest(Long id, Long id2, Long id3, Long id4);

}
