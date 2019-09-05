package cn.dksy.service;

import cn.dksy.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有
     * @return
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加
     * @param product
     */
    void save(Product product);
}
