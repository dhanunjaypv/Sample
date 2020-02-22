package com.bicgraphic.ods.product.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bicgraphic.ods.product.beans.Products;

@Repository
public interface IProductIngestionRepository extends MongoRepository<Products, String> {
 @Query("{Productid:'?0'}")
  public Products findByProductID(String productID) throws Exception;
// @Query("{customerAccountID:'?0'}")
// public Products findByCustomerAccountID(String customerAccountID) throws Exception;
}
