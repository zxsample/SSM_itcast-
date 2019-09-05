package cn.dksy.controller;

import cn.dksy.entity.Product;
import cn.dksy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author JAVASM
 * @title: ProductController
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/8/31 16:10
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")   //jsr250   参数是一个string数组，直接写允许访问的权限名称  测试成功  不是admin的访问这个方法将403错误  可在web.xml配置403错误自定义页面
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        return mv;

    }

    /**
     * 产品添加
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @RolesAllowed("SALE")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll";
    }





}