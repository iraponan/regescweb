package br.eti.inovareti.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Control {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        request.setAttribute("nome", "Teste de Thymeleaf");
        return "htmls/hello"; // Pega os arquivos que estão na pasta de templates dentro de resources
    }
}
