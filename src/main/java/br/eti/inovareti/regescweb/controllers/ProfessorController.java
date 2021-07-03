package br.eti.inovareti.regescweb.controllers;

import br.eti.inovareti.regescweb.enums.StatusProfessor;
import br.eti.inovareti.regescweb.models.Professor;
import br.eti.inovareti.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("htmls/professores/index");
        List<Professor> professors = this.professorRepository.findAll();
        mv.addObject("professores", professors);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("htmls/professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    // Web Parameter Tampering.
    @PostMapping("/professores")
    public ModelAndView create(Professor professor) {
        ModelAndView mv = new ModelAndView("redirect:/professores");
        //System.out.println("\n" + professor + "\n");
        return mv;
    }
}
