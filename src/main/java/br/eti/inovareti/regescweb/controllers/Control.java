package br.eti.inovareti.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Control {

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("htmls/hello"); // Arquivo HTML a ser redenrizado.
        mv.addObject("nome", "Teste de Thymeleaf 3");
        return mv;
    }
}
