package com.ashokavoice.ashokavoice.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.ashokavoice.ashokavoice.model.Logros;


public class GeneradorPDF {
     public static ByteArrayInputStream generatePDF(List<Logros> logros) throws IOException {
        PDDocument document = new PDDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDFont pdfFont=PDType1Font.HELVETICA;
            contentStream.beginText();
            contentStream.setFont(pdfFont, 16);
            
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Logros del Usuario");
            

            contentStream.setFont(pdfFont, 12);
            float titleOffset = 50;
            float indentOffset = 70;
            for (Logros logro : logros) {
                contentStream.newLineAtOffset(-titleOffset+50,-20);
                contentStream.showText("- " + logro.getTitulo());
                contentStream.newLineAtOffset(indentOffset-50,-20);
                contentStream.showText("-- " + logro.getDescripcion());
                contentStream.newLineAtOffset(0,-20);
                contentStream.showText("-- " + logro.getArea());
                contentStream.newLineAtOffset(0,-20);
                contentStream.showText("-- " + logro.getFecha());
                contentStream.newLineAtOffset(-indentOffset+titleOffset,-20);
            }
            contentStream.endText();
            contentStream.close();

            document.save(outputStream);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
