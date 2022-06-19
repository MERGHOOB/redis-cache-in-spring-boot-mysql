package com.margub.rediscacheinspringbootmysql.services;

import com.margub.rediscacheinspringbootmysql.entities.Invoice;
import com.margub.rediscacheinspringbootmysql.exceptions.InvoiceNotFoundException;
import com.margub.rediscacheinspringbootmysql.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    public static final String INVOICE_NOT_FOUND = "Invoice Not Found!";
    @Autowired
    InvoiceRepository invoiceRepository;


    @Override
    public Invoice saveInvoice(Invoice inv) {
        return invoiceRepository.save(inv);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice, Integer invoiceId) {
        Invoice foundInvoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> {
            throw new InvoiceNotFoundException(INVOICE_NOT_FOUND);
        });
        foundInvoice.setInvName(invoice.getInvName());
        foundInvoice.setInvAmount(invoice.getInvAmount());
        return invoiceRepository.save(foundInvoice);
    }

    @Override
    public void deleteInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException(INVOICE_NOT_FOUND));
        invoiceRepository.delete(invoice);
    }

    @Override
    public Invoice getOneInvoice(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException(INVOICE_NOT_FOUND));
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
