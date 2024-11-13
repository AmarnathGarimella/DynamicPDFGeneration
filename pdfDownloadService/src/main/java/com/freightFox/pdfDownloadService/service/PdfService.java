package com.freightFox.pdfDownloadService.service;

import com.freightFox.pdfDownloadService.dto.PdfRequest;
import com.freightFox.pdfDownloadService.utils.HashGenerator;
import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class PdfService {
    Logger LOG = LoggerFactory.getLogger(PdfService.class);
    @Autowired
    private TemplateEngine templateEngine;

    private static final String PDF_DIRECTORY = "pdf-storage/";

    @PostConstruct
    public void init() {
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public String generatePdf(PdfRequest pdfRequest) throws Exception {

        // Check if PDF for this data already exists
        String hash = HashGenerator.hashNestedObject(pdfRequest);
        String filePath = PDF_DIRECTORY + "invoice" + hash + ".pdf";

        File file = new File(filePath);
        if (file.exists()) {
            // Return existing file if already generated
            LOG.info("File already exists sending the existing File. {}",filePath);
            return file.getName();
        }

        // Generate PDF if not found
        Context context = new Context();
        context.setVariable("seller", pdfRequest.getSeller());
        context.setVariable("sellerAddress",pdfRequest.getSellerAddress());
        context.setVariable("sellerGstin",pdfRequest.getSellerGstin());
        context.setVariable("buyer", pdfRequest.getBuyer());
        context.setVariable("buyerAddress",pdfRequest.getBuyerAddress());
        context.setVariable("buyerGstin",pdfRequest.getBuyerGstin());
        context.setVariable("items",pdfRequest.getItems());

        // Generate HTML using Thymeleaf
        String htmlContent = templateEngine.process("invoice", context);

        // Convert HTML to PDF using iText
        try (OutputStream os = new FileOutputStream(filePath)) {
            HtmlConverter.convertToPdf(htmlContent, os);
        }
        catch (Exception e){
            LOG.error("error occurred: {}",e.getMessage());
        }

        LOG.info("{} :invoice generated",filePath);
        return file.getName();
    }
}
