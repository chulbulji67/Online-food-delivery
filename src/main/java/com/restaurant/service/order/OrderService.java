package com.restaurant.service.order;

import com.restaurant.dto.OrderDto;
import com.restaurant.model.Order;

import java.util.List;

public interface OrderService {
    public OrderDto CreateOrder(Order order);

    public OrderDto getOrderById(long id);

    public List<OrderDto> getAllOrderOfAUserById(long userId);
    public List<OrderDto> getAllOrderOfARestaurantById(long restaurantId);

    public OrderDto updateOrderById(long id, Order updateOrder);

    public String cancelOrderById(long orderId);


}
