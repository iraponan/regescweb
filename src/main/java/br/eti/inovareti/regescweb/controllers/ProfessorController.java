package br.eti.inovareti.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class ProfessorController {

    @GetMapping("/professores")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("htmls/professores/index");
        return mv;
    }
}
