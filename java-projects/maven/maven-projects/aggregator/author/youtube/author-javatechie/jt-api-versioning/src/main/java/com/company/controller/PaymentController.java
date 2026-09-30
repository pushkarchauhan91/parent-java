package com.company.controller;

import com.company.dto.PaymentMethod;
import com.company.dto.PaymentV1;
import com.company.dto.PaymentV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    //path based versioning

    @GetMapping(params = "version=1")
    public PaymentV1 getPaymentV1() {
        //Assume : Real business logic executed here
        return new PaymentV1(
                500,
                "INR",
                "UPI"
        );
    }

    @GetMapping(params = "version=2")
    public PaymentV2 getPaymentV2() {
        //Assume : Real business logic executed here
        return new PaymentV2(
                500,
                "INR",
                25,
                "PARTIAL_REFUND",
                new PaymentMethod("UPI", "GooglePay")
        );
    }

    // path segmentation versioning
    // header versioning
    // parm versioning


}
