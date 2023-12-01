package com.billing.service;

import com.billing.entity.Billing;
import com.billing.payload.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Service
//public class PdfGenerationService {
//
//    public InputStream generatePdf(Billing billing) throws DocumentException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//
//        document.open();
//
//        // Add invoice header
//        document.add(new Paragraph("Invoice for your purchase"));
//
//        // Add contact details (name, email, phone)
//        document.add(new Paragraph("Contact Name: " + billing.getName()));
//        document.add(new Paragraph("Email: " + billing.getEmail()));
//        document.add(new Paragraph("Phone Number: " + billing.getPhoneNumber()));
//        document.add(new Paragraph("---------------------------------------"));
//
//        // Add product details (name, price, quantity)
//        for (Product product : billing.getProducts()) {
//            document.add(new Paragraph("Product: " + product.getProductName()));
//            document.add(new Paragraph("Price: " + product.getProductPrice()));
//            document.add(new Paragraph("Quantity: " + product.getQuantity()));
//            document.add(new Paragraph("---------------------------------------"));
//        }
//
//        // Add total amount
//        document.add(new Paragraph("Total Amount: " + billing.getTotalAmount()));
//
//        document.close();
//
//        return new ByteArrayInputStream(outputStream.toByteArray());
//    }
//}
//public class PdfGenerationService {
//
//    public InputStream generatePdf(Billing billing) throws DocumentException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, outputStream);
//
//        document.open();
//
//        // Add title and header
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
//        Paragraph title = new Paragraph("Invoice", titleFont);
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);
//
//        document.add(new Paragraph("Invoice Number: " + billing.getId()));
//        document.add(new Paragraph("Date: " + LocalDate.now()));
//        document.add(new Paragraph("Customer Name: " + billing.getName()));
//        document.add(new Paragraph("Email: " + billing.getEmail()));
//        document.add(new Paragraph("Phone Number: " + billing.getPhoneNumber()));
//        document.add(new Paragraph(""));
//        document.add(new Paragraph("                                               "));
//
//        // Add table for product details
//        PdfPTable table = new PdfPTable(4);
//        table.setWidthPercentage(100);
//        table.addCell("Product Name");
//        table.addCell("Price");
//        table.addCell("Quantity");
//        table.addCell("Total");
//
//        for (Product product : billing.getProducts()) {
//            table.addCell(product.getProductName());
//            table.addCell(String.valueOf(product.getProductPrice()));
//            table.addCell(String.valueOf(product.getQuantity()));
//            table.addCell(String.valueOf(product.getProductPrice() * product.getQuantity()));
//        }
//
//        document.add(table);
//
//        // Add total amount
//        Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
//        Paragraph total = new Paragraph("Total Amount: " + billing.getTotalAmount(), totalFont);
//        total.setAlignment(Element.ALIGN_RIGHT);
//        document.add(total);
//
//        document.close();
//
//        return new ByteArrayInputStream(outputStream.toByteArray());
//    }
//}

//public class PdfGenerationService {
//
//    public InputStream generatePdf(Billing billing) throws DocumentException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//
//        document.open();
//
//        // Add title and header
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
//        Paragraph title = new Paragraph("Invoice", titleFont);
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);
//
//        document.add(new Paragraph("Invoice Number: " + billing.getId()));
//        document.add(new Paragraph("Date: " + LocalDate.now()));
//        document.add(new Paragraph("Customer Name: " + billing.getName()));
//        document.add(new Paragraph("Email: " + billing.getEmail()));
//        document.add(new Paragraph("Phone Number: " + billing.getPhoneNumber()));
//        document.add(new Paragraph(""));
//
//        // Add product details
//        Font productFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
//        PdfPTable table = new PdfPTable(3);
//        table.setWidthPercentage(100);
//        table.addCell(new PdfPCell(new Phrase("Product Name", productFont)));
//        table.addCell(new PdfPCell(new Phrase("Quantity", productFont)));
//        table.addCell(new PdfPCell(new Phrase("Price", productFont)));
//
//        for (Product product : billing.getProducts()) {
//            table.addCell(new PdfPCell(new Phrase(product.getProductName(), productFont)));
//            table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getQuantity()), productFont)));
//            table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getProductPrice()), productFont)));
//        }
//
//        document.add(table);
//
//        // Add total amount
//        Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
//        Paragraph total = new Paragraph("Total Amount: " + billing.getTotalAmount(), totalFont);
//        total.setAlignment(Element.ALIGN_RIGHT);
//        document.add(total);
//
//        // Add declaration
//        Font declarationFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
//        Paragraph declaration = new Paragraph("I hereby declare that the above information is true and accurate to the best of my knowledge.", declarationFont);
//        document.add(declaration);
//
//        // Add customer acknowledgment
//        Font acknowledgmentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
//        Paragraph acknowledgment = new Paragraph("Customer's Signature: _____________________\nDate: _________", acknowledgmentFont);
//        document.add(acknowledgment);
//
//        document.close();
//
//        return new ByteArrayInputStream(outputStream.toByteArray());
//    }
//}

public class PdfGenerationService {

    public InputStream generatePdf(Billing billing) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();


        // Load the image/logo
        String imagePath = "C:\\Users\\Lenovo\\Desktop\\CRM\\Vista Logos\\logo-transparent-png.png";
        Image logo = Image.getInstance(imagePath);
        logo.scaleToFit(100, 100); // Adjust the size as needed

// Add title and header with logo
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
        Paragraph title = new Paragraph("Invoice", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph logoParagraph = new Paragraph();
        logoParagraph.add(title);
        logoParagraph.add(glue);
        logoParagraph.add(new Chunk(logo, 0, 0, true));
        document.add(logoParagraph);






//        // Add title and header
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
//        Paragraph title = new Paragraph("Invoice", titleFont);
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);

        document.add(new Paragraph("Invoice Number: " + billing.getId()));
        document.add(new Paragraph("Date: " + LocalDate.now()));
        document.add(new Paragraph("Customer Name: " + billing.getName()));
        document.add(new Paragraph("Email: " + billing.getEmail()));
        document.add(new Paragraph("Phone Number: " + billing.getPhoneNumber()));
        document.add(new Paragraph(""));
        document.add(new Paragraph("                                 "));
        document.add(new Paragraph("                                 "));


        // Add product details
        Font productFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("Product Name", productFont)));
        table.addCell(new PdfPCell(new Phrase("Quantity", productFont)));
        table.addCell(new PdfPCell(new Phrase("Price", productFont)));

        for (Product product : billing.getProducts()) {
            table.addCell(new PdfPCell(new Phrase(product.getProductName(), productFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getQuantity()), productFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(product.getProductPrice()), productFont)));
        }

        document.add(table);

        // Add total amount
        Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Paragraph total = new Paragraph("Total Amount: " + billing.getTotalAmount(), totalFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);


        // Add declaration
        document.add(new Paragraph("                                 "));
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Paragraph declaration = new Paragraph("DECLARATION\n" +
                "We declare that this invoice shows actual price of the goods described\n" +
                "inclusive of taxes and that all particulars are true and correct.", boldFont);
        document.add(declaration);
        document.add(new Paragraph("                                 "));


        // Add customer acknowledgment
        Paragraph acknowledgment = new Paragraph("CUSTOMER ACKNOWLEDGEMENT\n" +
                "I " + billing.getName().toUpperCase() + " hereby confirm that the above said product/s are being\n" +
                "purchased for my internal / personal consumption and not for re-sale.", boldFont);
        document.add(acknowledgment);

        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}