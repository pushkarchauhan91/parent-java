package com.company.services;

import com.company.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao dao;

    public PaymentDao getDao() {
        return dao;
    }

    public void setDao(PaymentDao dao) {
        this.dao = dao;
    }

}
