package org.example.laboratorio3.controller;

import org.example.laboratorio3.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Este controller se encargará de gestionar las solicitudes relacionadas con la página de inicio
@Controller
public class HomeController {
    private final UsuarioService usuarioService;

    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    public String inicio(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "home";
    }
}
