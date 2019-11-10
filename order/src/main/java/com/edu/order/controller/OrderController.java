package com.edu.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    /*
    @Autowired
    UserOrderRepository userOrderRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    SiteMessageRepository siteMessageRepository;

    @PostMapping("/addOrder")
    public Order addOrder(@Valid @RequestBody Order userOrder) {
        userOrder.setCreateDateTime(LocalDateTime.now());
        userOrder.setUpdateDateTime(LocalDateTime.now());
        log.info(userOrder.toString());
        userOrder = userOrderRepository.save(userOrder);
        return userOrder;
    }

    @PostMapping("/placeOrder/username/{username}")
    @Transactional
    public Order placeOrder(@PathVariable String username, @Valid @RequestBody Order userOrder) {
        User buyer = userRepository.findByUserName(username);
        userOrder.setCreateDateTime(LocalDateTime.now());
        userOrder.setUpdateDateTime(LocalDateTime.now());
        userOrder.setOrderStatus(OrderStatus.DRAFT);
        userOrder.setBuyer(buyer);
        // add all product in cart into order
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByBuyUser(userOrder.getBuyer());
        List<UserOrderDetail> userOrderDetailList = new ArrayList<>();
        for(ShoppingCart shoppingCart : shoppingCartList) {
            userOrderDetailList.add(new UserOrderDetail(null, shoppingCart.getProduct(), shoppingCart.getNumberOfProduct()));
        }
        userOrder.setUserOrderDetailList(userOrderDetailList);
        log.info(userOrder.toString());
        userOrder = userOrderRepository.save(userOrder);
        // delete shopping cart
        shoppingCartRepository.deleteAll(shoppingCartList);

        return userOrder;
    }

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable String id) {
        log.info("getOrder, id=" + id);
        Order userOrder = userOrderRepository.findById(Long.valueOf(id)).get();
        return userOrder;
    }
    @GetMapping("/getReceipt/{id}")
    public Order getReceipt(@PathVariable String id) {
        log.info("getOrder, id=" + id);
        Order userOrder = userOrderRepository.findById(Long.valueOf(id)).get();
        if (!OrderStatus.DELIVERED.equals(userOrder.getOrderStatus())) {
            return null;
        }
        return userOrder;
    }
    @GetMapping("/getOrderList")
    public List<Order> getOrderList() {
        List<Order> userOrderList = userOrderRepository.findAll();
        return userOrderList;
    }
    @GetMapping("/getOrderList/buyer/{username}")
    public List<Order> getOrderListByBuyer(@PathVariable String username) {
        User buyer = userRepository.findByUserName(username);
        List<Order> userOrderList = userOrderRepository.findByBuyer(buyer);
        return userOrderList;
    }
    @GetMapping("/getOrderList/seller/{username}")
    public List<Order> getOrderListBySeller(@PathVariable String username) {
        User seller = userRepository.findByUserName(username);
        List<Order> userOrderList = userOrderRepository.findBySeller(seller);
        return userOrderList;
    }
    @PostMapping("/updateOrder")
    public Order updateOrder(@Valid @RequestBody Order userOrder) {
        userOrder.setUpdateDateTime(LocalDateTime.now());
        log.info(userOrder.toString());
        userOrder = userOrderRepository.save(userOrder);
        return userOrder;
    }
    @PostMapping("/updateOrderStatus/{id}/status/{status}")
    @Transactional
    public Order updateOrder(@PathVariable String id, @PathVariable String status) {
        log.info("updateOrder, id=" + id);
        Order userOrder = userOrderRepository.findById(Long.valueOf(id)).get();
        userOrder.setUpdateDateTime(LocalDateTime.now());
        if (OrderStatus.PAID.toString().equals(status)) {
            userOrder.getPayment().setPaymentStatus(PaymentStatus.PAID);
        } else if (OrderStatus.DELIVERED.toString().equals(status)){
            // send credit point to buyer
            User buyer = userRepository.findById(userOrder.getBuyer().getId()).get();
            buyer.setCreditPoint(buyer.getCreditPoint() + userOrder.getPayment().getAmount());
            userRepository.save(buyer);
        }
        userOrder.setOrderStatus(OrderStatus.valueOf(status));
        userOrder = userOrderRepository.save(userOrder);
        return userOrder;
    }

    @PostMapping("/cancelOrder/{id}")
    public Order cancelOrder(@PathVariable String id) {
        log.info("cancelOrder, id=" + id);
        Order userOrder = userOrderRepository.findById(Long.valueOf(id)).get();
        userOrder.setUpdateDateTime(LocalDateTime.now());
        if (userOrder.getPayment() != null) {
            userOrder.getPayment().setPaymentStatus(PaymentStatus.CANCEL);
        }
        userOrder.setOrderStatus(OrderStatus.CANCEL);
        // Notify Buyer by website message
        SiteMessage siteMessage = new SiteMessage(null, userOrder.getUserOrderDetailList().get(0).getProduct().getSeller(), "Your order (No: " + userOrder.getId() + ") is cancelled", userOrder.getBuyer(), Boolean.TRUE);
        siteMessage = siteMessageRepository.save(siteMessage);

        userOrder = userOrderRepository.save(userOrder);
        return userOrder;
    }
    */

}
