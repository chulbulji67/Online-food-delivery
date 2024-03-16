package com.restaurant.service.order;

import com.restaurant.dto.OrderDto;
import com.restaurant.exception.addressnotfoundexception.AddressNotFoundExceptin;
import com.restaurant.exception.deliverypersonexception.DeliveryPersonNotFoundException;
import com.restaurant.exception.ordernotfoundexception.OrderNotFoundExceptionException;
import com.restaurant.exception.restaurantexception.RestaurantNotFoundException;
import com.restaurant.exception.userexception.UserNotFoundException;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDto CreateOrder(Order order) {
        //Check UserExist or Not
        User user = userRepository.findById(order.getUser().getId()).orElseThrow(()->new UserNotFoundException("User Not found in which you want to add Order"));
        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant().getId()).orElseThrow(()->new RestaurantNotFoundException("Restaurant Not found in which you want to add Order"));
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(order.getDeliveryPerson().getId()).orElseThrow(()->new DeliveryPersonNotFoundException("Delivery Person Not found in which you want to add Order"));
        Address deliveryAddress = addressRepository.findById(order.getDeliveryAddress().getId()).orElseThrow(()->new AddressNotFoundExceptin("Delivery Address not not found in which you want to create order"));
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setDeliveryPerson(deliveryPerson);
        order.setDeliveryAddress(deliveryAddress);
        return mapOrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundExceptionException("Order Not found with the given id"));
        return mapOrderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrderOfAUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not found for which you are fetching all orders"));
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream().map(OrderServiceImpl::mapOrderToOrderDto).toList();
    }

    @Override
    public List<OrderDto> getAllOrderOfARestaurantById(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new RestaurantNotFoundException("User Not found for which you are fetching all orders"));
        List<Order> orders = orderRepository.findByRestaurant(restaurant);
        return orders.stream().map(OrderServiceImpl::mapOrderToOrderDto).toList();
    }

    @Override
    public OrderDto updateOrderById(long id, Order updateOrder) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundExceptionException("Order Not Found which You want to update"));
        if(updateOrder.getDeliveryAddress() != null){
            order.setDeliveryAddress(updateOrder.getDeliveryAddress());
        }
        if(updateOrder.getTotalAmount() != 0){
            order.setTotalAmount(updateOrder.getTotalAmount());
        }
        return mapOrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public String cancelOrderById(long orderId) {
        orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundExceptionException("Order Not Found which You want to update"));
        orderRepository.deleteById(orderId);
        return "Order Canceled Successfully";
    }

    public static OrderDto mapOrderToOrderDto(Order order){
        return OrderDto.builder()
                .orderDate(order.getOrderDate())
                .deliveryAddressId(order.getDeliveryAddress().getId())
                .status(order.getStatus())
                .reviewText(order.getReview().getReviewText())
                .totalAmount(order.getTotalAmount())
                .deliveryPersonName(order.getDeliveryPerson().getUser().getUsername())
                .restaurantName(order.getRestaurant().getName())
                .build();
    }
}
