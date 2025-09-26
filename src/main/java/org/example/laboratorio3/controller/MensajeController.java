package org.example.laboratorio3.controller;

import jakarta.validation.Valid;
import org.example.laboratorio3.entity.Mensajes;
import org.example.laboratorio3.entity.Usuarios;
import org.example.laboratorio3.service.MensajeService;
import org.example.laboratorio3.service.RankingService;
import org.example.laboratorio3.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Este controlador se encargar de gestionar las solicitudes relacionadas con los mensajes

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;
    private final UsuarioService usuarioService;
    private final RankingService rankingService; // opcional si usas tabla ranking

    public MensajeController(MensajeService mensajeService,
                             UsuarioService usuarioService,
                             RankingService rankingService) {
        this.mensajeService = mensajeService;
        this.usuarioService = usuarioService;
        this.rankingService = rankingService;
    }

    @GetMapping("/nuevo")
    public String form(@RequestParam(required = false) Integer destinatarioId, Model model) {
        Mensajes m = new Mensajes();
        if (destinatarioId != null) {
            usuarioService.porId(destinatarioId).ifPresent(m::setDestinatario);
        }
        model.addAttribute("mensaje", m);
        model.addAttribute("usuarios", usuarioService.listar());
        return "mensajes/nuevo";
    }

    @PostMapping("/enviar")
    public String enviar(@Valid @ModelAttribute("mensaje") Mensajes mensaje,
                         BindingResult br, Model model, RedirectAttributes ra) {

        if (br.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            return "mensajes/nuevo";
        }

        // Normaliza reglas básicas (si no lo haces con triggers/validador custom)
        if (mensaje.getRegaloTipo().name().equals("Flor")) {
            mensaje.setRegaloColor("amarilla");
        }

        mensajeService.enviar(mensaje);

        // Si quieres mantener la tabla ranking desde la app
        if (mensaje.getDestinatario() != null) {
            Usuarios dest = mensaje.getDestinatario();
            rankingService.incrementar(dest);
        }

        ra.addFlashAttribute("ok", "Mensaje enviado.");
        return "redirect:/home";
    }

    @GetMapping("/recibidos/{usuarioId}")
    public String recibidos(@PathVariable Integer usuarioId, Model model) {
        model.addAttribute("mensajes", mensajeService.recibidos(usuarioId));
        model.addAttribute("total", mensajeService.totalRecibidos(usuarioId));
        return "mensajes/recibidos";
    }
}
