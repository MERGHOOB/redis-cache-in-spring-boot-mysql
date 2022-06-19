package com.margub.rediscacheinspringbootmysql.services;

import com.margub.rediscacheinspringbootmysql.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice saveInvoice(Invoice inv);
    Invoice updateInvoice(Invoice invoice, Integer invoiceId);
    void deleteInvoice(Integer invoiceId);
    Invoice getOneInvoice(Integer invoiceId);
    List<Invoice> getAllInvoices();

}
