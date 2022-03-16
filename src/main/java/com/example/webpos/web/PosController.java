package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PosController {

    private PosService posService;

    private String refreshModel(Model model){
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/")
    public String pos(Model model) {
        return refreshModel(model);
    }

    @GetMapping("/add")
    public String add(@RequestParam(name="pid")String pid, Model model){
        posService.add(pid, 1);
        return refreshModel(model);
    }

    @GetMapping("/item/remove")
    public String itemRemove(@RequestParam(name="pid")String pid, Model model){
        posService.getCart().itemAmountDecline(Integer.parseInt(pid), 1);
        return refreshModel(model);
    }

    @GetMapping("/item/add")
    public String itemAdd(@RequestParam(name="pid")String pid, Model model){
        posService.getCart().itemAmountAdd(Integer.parseInt(pid), 1);
        return refreshModel(model);
    }

    @GetMapping("/item/delete")
    public String itemDelete(@RequestParam(name="pid")String pid, Model model){
        posService.getCart().itemDelete(Integer.parseInt(pid));
        return refreshModel(model);
    }

    @GetMapping("/cancel")
    public String cancel(Model model){
        posService.getCart().cancel();
        return refreshModel(model);
    }

    @GetMapping("/charge")
    public String charge(Model model){
        return cancel(model);
    }
}
