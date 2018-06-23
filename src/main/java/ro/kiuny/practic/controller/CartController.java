package ro.kiuny.practic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.kiuny.practic.dto.CartDto;
import ro.kiuny.practic.dto.CartProductDto;
import ro.kiuny.practic.dto.ProductDto;
import ro.kiuny.practic.model.*;
import ro.kiuny.practic.model.converter.ProductConverter;
import ro.kiuny.practic.repository.LineItemRepository;
import ro.kiuny.practic.repository.OrderRepository;
import ro.kiuny.practic.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class CartController {

    private Logger logger = LoggerFactory.getLogger(CartController.class);

    private final ProductRepository productRepository;
    private final LineItemRepository lineItemRepository;
    private final OrderRepository orderRepository;
    private final ProductConverter productConverter;

    public CartController(ProductRepository productRepository, LineItemRepository lineItemRepository, OrderRepository orderRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.lineItemRepository = lineItemRepository;
        this.orderRepository = orderRepository;
        this.productConverter = productConverter;
    }

    @GetMapping(path = "/products")
    public List<ProductDto> products() {
        logger.debug("CartController > products");

        List<ProductDto> products = productRepository.findAll().stream().map(productConverter::toDto).collect(Collectors.toList());

        logger.debug("CartController > products: {}", products);
        return products;
    }

    @GetMapping(path = "/cart/count")
    public Integer count() {
        logger.debug("CartController > count");

        int count = lineItemRepository.getCartItems().stream().mapToInt(LineItem::getQuantity).sum();

        logger.debug("CartController > count: {}", count);
        return count;
    }

    @GetMapping(path = "/cart")
    public CartDto cart() {
        logger.debug("CartController > cart");


        List<LineItem> productsInCart = lineItemRepository.getCartItems();

        int totalItems = productsInCart.stream().mapToInt(LineItem::getQuantity).sum();
        List<CartProductDto> productDtos = productsInCart.stream()
                .map(lineItem -> new CartProductDto(
                        lineItem.getLineItemPk().getProduct().getId(),
                        lineItem.getQuantity(),
                        lineItem.getLineItemPk().getProduct().getName()))
                .collect(Collectors.toList());
        CartDto cartDto = new CartDto(totalItems, productDtos, "", "");

        logger.debug("CartController > cart: {}", cartDto);

        return cartDto;
    }


    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addToCart(@RequestBody Map<String, String> request) {
        logger.debug("CartController > addToCart");

        Long productId = Long.parseLong(request.get("productId"));
        Product product = productRepository.findById(productId).get();

        Optional<Order> orderOptional = orderRepository.findByStatus(OrderStatus.CART);
        Order order = orderOptional.orElseGet(() -> orderRepository.save(new Order(null, 0, null, OrderStatus.CART, null)));


        Optional<LineItem> existingItem = lineItemRepository.findById(new LineItem.LineItemPk(product, order));

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
            lineItemRepository.save(existingItem.get());
            return;
        }

        lineItemRepository.save(new LineItem(new LineItem.LineItemPk(product, order), 1));
    }

    @PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLineItem(@RequestBody Map<String, String> request) {
        logger.debug("CartController > updateLineItem");

        Long productId = Long.parseLong(request.get("productId"));
        Integer quantity = Integer.parseInt(request.get("quantity"));

        Optional<Order> orderOptional = orderRepository.findByStatus(OrderStatus.CART);
        Order order = orderOptional.get();
        Product product = productRepository.findById(productId).get();


        Optional<LineItem> existingItem = lineItemRepository.findById(new LineItem.LineItemPk(product, order));

        existingItem.get().setQuantity(quantity);
        lineItemRepository.save(existingItem.get());
    }

    @PostMapping(path = "/order")
    public void createOrUpdateOrder(@RequestParam String email, @RequestParam String address) {
        logger.debug("CartController > createOrUpdateOrder");

        Optional<Order> orderOptional = orderRepository.findByStatus(OrderStatus.CART);

        Order order = orderOptional.get();
        order.setContactInfo(new ContactInfo(email, address));

        orderRepository.save(order);
    }

    @PostMapping(path = "/submit")
    public void submitOrder() {
        logger.debug("CartController > submitOrder");


        Optional<Order> orderOptional = orderRepository.findByStatus(OrderStatus.CART);

        if (!orderOptional.isPresent()) {
            return;
        }

        Order order = orderOptional.get();
        order.setDate(LocalDateTime.now());
        order.setStatus(OrderStatus.SUBMITTED);

        orderRepository.save(order);
    }

}
