package com.billing.controller;


import com.billing.entity.Billing;
import com.billing.payload.Contact;
import com.billing.repository.BillingRepository;
import com.billing.service.Billingservice;
import com.billing.service.PdfGenerationService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class BillingController {

	@Autowired
	private PdfGenerationService pdfGenerationService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private RestTemplate restTemplate;

	 String baseurl = "http://localhost:8080/api/contact/";

	 @Autowired
	 private BillingRepository billingRepo;

	@Autowired
	private Billingservice billingService;

	@PostMapping("/generateInvoice/{id}")
	public Billing generateInvoice(@PathVariable Long id) {
		return billingService.generateInvoice(id);
	}

	@PostMapping("/generateInvoice/send/{id}")
	public ResponseEntity<String> sendInvoice(@PathVariable Long id) throws DocumentException, MessagingException, IOException {
		Billing billing = billingService.generateInvoice(id);
		billing.setTotalAmount(billing.calculateTotalAmount());

		InputStream pdfInputStream = pdfGenerationService.generatePdf(billing);

		// Send email with PDF attachment
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("muletushar6.com", "TeamNector");
		helper.setTo(billing.getEmail());
		helper.setSubject("Invoice for your purchase");
		helper.setText("Please find attached your invoice.");

		// Attach the PDF to the email
		ByteArrayDataSource pdfDataSource = new ByteArrayDataSource(pdfInputStream, "application/pdf");
		helper.addAttachment("invoice.pdf", pdfDataSource);

		javaMailSender.send(message);

		return ResponseEntity.ok("Invoice sent to " + billing.getEmail());
	}

	

}
