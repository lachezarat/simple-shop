package com.myproject.eshop.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.eshop.data.entities.*;
import com.myproject.eshop.data.models.service.OrderServiceModel;
import com.myproject.eshop.error.InvalidItemException;
import com.myproject.eshop.error.OrderNotFoundException;
import com.myproject.eshop.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final LaptopRepository laptopRepository;
    private final SmartphoneRepository smartphoneRepository;
    private final SmartwatchRepository smartwatchRepository;
    private final TabletRepository tabletRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository,
                            LaptopRepository laptopRepository, SmartphoneRepository smartphoneRepository,
                            SmartwatchRepository smartwatchRepository, TabletRepository tabletRepository,
                            UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.laptopRepository = laptopRepository;
        this.smartphoneRepository = smartphoneRepository;
        this.smartwatchRepository = smartwatchRepository;
        this.tabletRepository = tabletRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //TODO refactor
    @Override
    public OrderServiceModel saveOrder(String orderInfo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Order order = objectMapper.readValue(orderInfo, Order.class);

        List<Item> items = order.getOrderItems();

        List<Item> checkedItems = new ArrayList<>();

        items.forEach(item -> {
            switch (item.getCategory()) {

                case "laptop":
                    Laptop laptop = laptopRepository.findByBrandAndModel(item.getBrand(), item.getModel())
                            .orElseThrow(() -> new InvalidItemException("Invalid item!"));

                    Item laptopItem = modelMapper.map(laptop, Item.class);
                    laptopItem.setOrder(item.getOrder());
                    laptopItem.setQuantity(item.getQuantity());
                    laptopItem.setCategory(item.getCategory());

                    checkedItems.add(laptopItem);
                    break;

                case "smartphone":
                    Smartphone smartphone = smartphoneRepository.findByBrandAndModel(item.getBrand(), item.getModel())
                            .orElseThrow(() -> new InvalidItemException("Invalid item!"));

                    Item smartphoneItem = modelMapper.map(smartphone, Item.class);
                    smartphoneItem.setOrder(item.getOrder());
                    smartphoneItem.setCategory(item.getCategory());
                    smartphoneItem.setQuantity(item.getQuantity());

                    checkedItems.add(smartphoneItem);
                    break;

                case "smartwatch":
                    Smartwatch smartwatch = smartwatchRepository.findByBrandAndModel(item.getBrand(), item.getModel())
                            .orElseThrow(() -> new InvalidItemException("Invalid item!"));

                    Item smartwatchItem = modelMapper.map(smartwatch, Item.class);
                    smartwatchItem.setOrder(item.getOrder());
                    smartwatchItem.setCategory(item.getCategory());
                    smartwatchItem.setQuantity(item.getQuantity());

                    checkedItems.add(smartwatchItem);
                    break;

                case "tablet":
                    Tablet tablet = tabletRepository.findByBrandAndModel(item.getBrand(), item.getModel())
                            .orElseThrow(() -> new InvalidItemException("Invalid item!"));

                    Item tabletItem = modelMapper.map(tablet, Item.class);
                    tabletItem.setOrder(item.getOrder());
                    tabletItem.setCategory(item.getCategory());
                    tabletItem.setQuantity(item.getQuantity());

                    checkedItems.add(tabletItem);
                    break;
            }
        });

        orderRepository.saveAndFlush(order);
        itemRepository.saveAll(checkedItems);

        return modelMapper.map(order, OrderServiceModel.class);
    }

    @Override
    public List<OrderServiceModel> showUserOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return orderRepository.findAllByEmail(user.getEmail())
                .stream()
                .map(order -> modelMapper.map(order, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel showOrder(String username, String orderId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found!"));

        if (!order.getEmail().equals(user.getEmail())) {
            throw new OrderNotFoundException("Order not found!");
        } else {
            return modelMapper.map(order, OrderServiceModel.class);
        }
    }
}
