package com.freightFox.pdfDownloadService.controller;

import com.freightFox.pdfDownloadService.dto.PdfRequest;
import com.freightFox.pdfDownloadService.service.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/pdf")
public class PdfControllerMain {

        Logger LOG = LoggerFactory.getLogger(PdfControllerMain.class);

        @Autowired
        private PdfService pdfService;

        @PostMapping("/generate")
        public ResponseEntity<?> generatePdf(@RequestBody PdfRequest pdfRequest) {
            try {
                String filePath = pdfService.generatePdf(pdfRequest);
                return ResponseEntity.ok(filePath);
            } catch (Exception e) {
                LOG.error("",e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating PDF");
            }
        }

        @GetMapping("/download")
        public ResponseEntity<Resource> downloadPdf(@RequestParam String fileName) throws FileNotFoundException {
            File file = new File(Paths.get("pdf-storage",fileName).toString());
            if (file.exists()) {
                InputStreamResource resource = null;
                resource = new InputStreamResource(new FileInputStream(file));

                LOG.info("File is being sent.");

                return ResponseEntity.ok()
                        .contentLength(file.length())
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                        .body(resource);
            } else {
                LOG.error("{} File Not Found",fileName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

}
