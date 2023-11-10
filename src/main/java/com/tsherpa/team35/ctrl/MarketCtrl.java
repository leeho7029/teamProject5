package com.tsherpa.team35.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("market")
public class MarketCtrl {

    @GetMapping("/marketList")
    public String list(Model model)throws Exception{
        return "market/marketList";
    }

    @PostMapping("/insert")
    public String insert(Model model)throws Exception{

        return "redirect:marketList";
    }

}
