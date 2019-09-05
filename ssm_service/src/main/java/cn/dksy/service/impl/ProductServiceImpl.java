package cn.dksy.service.impl;

import cn.dksy.dao.ProductDao;
import cn.dksy.entity.Product;
import cn.dksy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JAVASM
 * @title: ProductServiceImpl
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/8/31 16:14
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    /**
     * 添加
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}