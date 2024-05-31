package com.ashokavoice.ashokavoice.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.service.ExtrasService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/extra")
public class ExtrasController {
 
    @Autowired
    private ExtrasService extrasService;

    // Descargar datos
    @GetMapping("/download/{idUsuario}")
    public void descargarDatos(@PathVariable Long idUsuario, HttpServletResponse response) throws IOException {
        response.setContentType("Mis_Logros/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"logros.csv\"");
        extrasService.writeLogrosToCsv(response.getWriter(), idUsuario);
    }
}
