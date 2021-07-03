package br.eti.inovareti.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Control {

    @GetMapping("/hello")
    public String hello() {
        return "htmls/hello"; // Pega os arquivos que estão na pasta de templates dentro de resources
    }
}
