package com.jdc.ishop.model.service;

import java.time.LocalDate;
import java.util.List;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Invoice;
import com.jdc.ishop.model.service.impl.InvoiceServiceImpl;

public interface InvoiceService {

	static InvoiceService getInstance() {
		return new InvoiceServiceImpl();
	}

	List<Invoice> find(String login, Category category, LocalDate dateFrom, LocalDate dateTo);

}