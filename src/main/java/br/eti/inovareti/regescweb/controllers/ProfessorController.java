package br.eti.inovareti.regescweb.controllers;

import br.eti.inovareti.regescweb.dto.RequisicaoFormProfessor;
import br.eti.inovareti.regescweb.enums.StatusProfessor;
import br.eti.inovareti.regescweb.models.Professor;
import br.eti.inovareti.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("htmls/professores/index");
        List<Professor> professors = this.professorRepository.findAll();
        mv.addObject("professores", professors);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor novoProfessor) {
        ModelAndView mv = new ModelAndView("htmls/professores/new");
        mv.addObject("listStatusProfessor", StatusProfessor.values());
        return mv;
    }

    // Web Parameter Tampering.
    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor novoProfessor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("\n********** FORMULARIO COM ERROS **********\n");
            ModelAndView mv = new ModelAndView("htmls/professores/new");
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else {
            Professor professor = novoProfessor.toProfessor();
            this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores/" + professor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("htmls/professores/show");
            mv.addObject(professor);
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor novoProfessor) {
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            novoProfessor.fromProfessor(professor);
            ModelAndView mv = new ModelAndView("htmls/professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor novoProfessor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else {
            Optional<Professor> optional = this.professorRepository.findById(id);

            if (optional.isPresent()) {
                Professor professor = novoProfessor.toProfessor(optional.get());
                this.professorRepository.save(professor);

                return new ModelAndView("redirect:/professores/" + professor.getId());
            }
            // não achou um registro na tabela Professor com o id informado
            else {
                System.out.println("############ NÃO ACHOU O PROFESSOR DE ID "+ id + " ############");
                return this.retornaErroProfessor("UPDATE ERROR: Professor #" + id + " não encontrado no banco!");
            }

        }
    }

    private ModelAndView retornaErroProfessor(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/professores");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
