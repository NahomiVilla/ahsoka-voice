package com.ashokavoice.ashokavoice.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.service.ExtrasService;
import com.ashokavoice.ashokavoice.service.GeneradorPDF;

@RestController
@RequestMapping("/extra")
public class ExtrasController {
    private static final Logger logger = Logger.getLogger(ExtrasService.class.getName());

    @Autowired
    private ExtrasService extrasService;
    

    // Descargar datos
    @GetMapping("/download/{userId}")
    public ResponseEntity<InputStreamResource> descargarLogrosComoPDF(@PathVariable Long userId) throws IOException {
        // Obtener los logros del usuario
        List<Logros> logros = extrasService.obtenerLogrosPorUsuario(userId);

        logger.info("Generar el contenido del PDF");
        ByteArrayInputStream bis = GeneradorPDF.generatePDF(logros); // Asume que tienes una clase GeneradorPDF para generar el PDF

        // Configurar las cabeceras de la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "logros_usuario.pdf");

        // Devolver el archivo PDF como respuesta
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }
}