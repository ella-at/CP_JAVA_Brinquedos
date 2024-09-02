package com.example.springboot.controllers;

import com.example.springboot.models.ProductModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.repositories.ProductRepository;

@Controller
public class FormController {

    @Autowired
    ProductRepository repo;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("product", new ProductRecordDto());
        return "form";
    }


    @PostMapping("/form")
    public String saveProduct(@ModelAttribute("product") @Validated ProductRecordDto productRecordDto, Model model) {
        ProductModel productModel = new ProductModel();
        productModel.setNome(productRecordDto.getNome());
        productModel.setPreco(productRecordDto.getPreco());
        productModel.setTipo(productRecordDto.getTipo());
        productModel.setDescricao(productRecordDto.getDescricao());
        repo.save(productModel);

        return "redirect:/lista";
    }
}
