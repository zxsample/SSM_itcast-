package cn.dksy.service;

import cn.dksy.entity.Orders;

import java.util.List;

public interface OrdersService {


    List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
