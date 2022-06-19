package com.margub.rediscacheinspringbootmysql.repositories;

import com.margub.rediscacheinspringbootmysql.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
