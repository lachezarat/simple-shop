package com.myproject.eshop.web.controllers;

import com.myproject.eshop.data.entities.Product;
import com.myproject.eshop.services.LaptopService;
import com.myproject.eshop.services.SmartphoneService;
import com.myproject.eshop.services.SmartwatchService;
import com.myproject.eshop.services.TabletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController extends BaseController {

    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final SmartwatchService smartwatchService;
    private final TabletService tabletService;

    @Autowired
    public CartController(LaptopService laptopService, SmartphoneService smartphoneService,
                          SmartwatchService smartwatchService, TabletService tabletService) {
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.smartwatchService = smartwatchService;
        this.tabletService = tabletService;
    }

    @GetMapping("/cart")
    public ModelAndView cart(HttpSession httpSession, ModelAndView modelAndView) {
        List<Product> items = (List<Product>) httpSession.getAttribute("cartProducts");
        modelAndView.addObject("items", items);

        return super.view(modelAndView, "cart");
    }

    @GetMapping("/cart/buy/{type}/{brand}/{model}")
    public ModelAndView buy(@PathVariable String type, @PathVariable String brand, @PathVariable String model,
                            HttpSession session) {

        if (session.getAttribute("cartProducts") == null) {
            List<Product> products = new ArrayList<>();
            session.setAttribute("cartProducts", products);
        }

        switch (type) {
            case "laptop":
                laptopService.addLaptopToCart(session, brand, model);
                break;
            case "smartphone":
                smartphoneService.addSmartphoneToCart(session, brand, model);
                break;
            case "smartwatch":
                smartwatchService.addSmartwatchToCart(session, brand, model);
                break;
            case "tablet":
                tabletService.addTabletToCart(session, brand, model);
                break;
        }

        return super.redirect("/cart");
    }
}
