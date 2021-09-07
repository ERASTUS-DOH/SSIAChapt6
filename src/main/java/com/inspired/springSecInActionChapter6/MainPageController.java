package com.inspired.springSecInActionChapter6;

import com.inspired.springSecInActionChapter6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {

    private ProductService productService;

    @Autowired
    public MainPageController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Authentication a, Model model){
        model.addAttribute("username", a.getName());
        model.addAttribute("products", productService.findAll());
        return  "main.html";
    }
}
