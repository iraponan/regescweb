package br.eti.inovareti.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Control {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("nome", "Teste de Thymeleaf 2");
        return "htmls/hello"; // Pega os arquivos que estão na pasta de templates dentro de resources
    }
}
