package com.margub.rediscacheinspringbootmysql.controllers;

import com.margub.rediscacheinspringbootmysql.entities.Invoice;
import com.margub.rediscacheinspringbootmysql.services.InvoiceService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/saveInv")
    public Invoice saveInvoice(@RequestBody @NotNull Invoice invoice){

        return invoiceService.saveInvoice(invoice);
    }
    @GetMapping("/allInv")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/getOne/{id}")
    public Invoice getOne(@PathVariable Integer id) {
        return invoiceService.getOneInvoice(id);
    }

    @PutMapping("/modify/{id}")
    public Invoice updateInvoice(@RequestBody Invoice invoice, @PathVariable Integer id){
        return invoiceService.updateInvoice(invoice, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoice(id);
        return "Employee with id: " + id + " deleted !";
    }

}
