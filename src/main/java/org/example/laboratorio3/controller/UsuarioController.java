package org.example.laboratorio3.controller;

import jakarta.validation.Valid;
import org.example.laboratorio3.entity.Usuarios;
import org.example.laboratorio3.service.RankingService;
import org.example.laboratorio3.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// este controlador se encargara de gestionar las solicitudes relacionadas con los usuarios
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RankingService rankingService;   // <-- inyectado

    public UsuarioController(UsuarioService usuarioService,
                             RankingService rankingService) {
        this.usuarioService = usuarioService;
        this.rankingService = rankingService;
    }

    @ModelAttribute("usuario")
    public Usuarios prepararUsuario() { return new Usuarios(); }

    @GetMapping("/registro")
    public String form() { return "usuarios/registro"; }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") Usuarios usuario,
                            BindingResult br,
                            RedirectAttributes ra) {
        if (br.hasErrors()) return "usuarios/registro";
        usuarioService.guardar(usuario);
        ra.addFlashAttribute("ok","Usuario registrado");
        return "redirect:/home";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        model.addAttribute("ranking", rankingService.listarOrdenado());
        return "usuarios/ranking";
    }
}
