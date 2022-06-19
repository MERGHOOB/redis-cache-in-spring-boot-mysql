package com.margub.rediscacheinspringbootmysql.services;

import com.margub.rediscacheinspringbootmysql.entities.Invoice;
import com.margub.rediscacheinspringbootmysql.exceptions.InvoiceNotFoundException;
import com.margub.rediscacheinspringbootmysql.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value="Invoice", key="#invoiceId") // key  value should be same as  updateInvoice second variable name
    public Invoice updateInvoice(Invoice invoice, Integer invoiceId) {
        Invoice foundInvoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> {
            throw new InvoiceNotFoundException(INVOICE_NOT_FOUND);
        });
        foundInvoice.setInvName(invoice.getInvName());
        foundInvoice.setInvAmount(invoice.getInvAmount());
        return invoiceRepository.save(foundInvoice);
    }

    @Override
    @CacheEvict(value="Invoice", key="#invoiceId")
    public void deleteInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException(INVOICE_NOT_FOUND));
        invoiceRepository.delete(invoice);
    }

    @Override
    @Cacheable(value="Invoice", key="#invoiceId")
    public Invoice getOneInvoice(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException(INVOICE_NOT_FOUND));
    }

    @Override
    @Cacheable(value="Invoice")
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
