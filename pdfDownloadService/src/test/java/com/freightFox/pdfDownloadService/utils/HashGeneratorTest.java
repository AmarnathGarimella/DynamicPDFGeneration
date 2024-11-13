package com.freightFox.pdfDownloadService.utils;

import com.freightFox.pdfDownloadService.dto.PdfRequest;
import com.freightFox.pdfDownloadService.dto.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HashGeneratorTest {

    @Test
    public void shouldHaveSameHashIfListItemsOrderIsSame() {
        Product pro1 = new Product("Product1","12 Nos", BigDecimal.valueOf(12.00),BigDecimal.valueOf(144.00));
        Product pro2 = new Product("Product2","12 Nos", BigDecimal.valueOf(12.00),BigDecimal.valueOf(144.00));
        PdfRequest req1 = new PdfRequest(
                "seller1",
                "234567890ee",
                "sadd1",
                "buyer1",
                "234567890ee",
                "byad1",
                List.of(pro1,pro2)
        );
        PdfRequest req2 = new PdfRequest(
                "seller1",
                "234567890ee",
                "sadd1",
                "buyer1",
                "234567890ee",
                "byad1",
                List.of(pro1,pro2)
        );
        String h1 = HashGenerator.hashNestedObject(req1);
        String h2 = HashGenerator.hashNestedObject(req2);
        assertEquals(h1,h2);
    }


    @Test
    public void shouldHaveDifferentHashIfListItemsElementsIsDifferent() {
        Product pro1 = new Product("Product1","12 Nos", BigDecimal.valueOf(12.00),BigDecimal.valueOf(144.00));
        Product pro2 = new Product("Product2","12 Nos", BigDecimal.valueOf(12.00),BigDecimal.valueOf(144.00));
        PdfRequest req1 = new PdfRequest(
                "seller1",
                "234567890ee",
                "sadd1",
                "buyer1",
                "234567890ee",
                "byad1",
                List.of(pro1,pro2)
        );
        PdfRequest req2 = new PdfRequest(
                "seller1",
                "234567890ee",
                "sadd1",
                "buyer1",
                "234567890ee",
                "byad1",
                List.of(pro2,pro1)
        );
        String h1 = HashGenerator.hashNestedObject(req1);
        String h2 = HashGenerator.hashNestedObject(req2);
        assertNotEquals(h1,h2);

    }


}
