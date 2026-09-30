package com.company.template;

import com.company.template.impl.CSVDataProcessor;
import com.company.template.impl.DataProcessor;
import com.company.template.impl.PDFDataProcessor;

public class TemplateTester {
    public static void main(String[] args) {

        DataProcessor csvProcessor = new CSVDataProcessor();
        csvProcessor.process();

        System.out.println("------------------");

        DataProcessor pdfProcessor = new PDFDataProcessor();
        pdfProcessor.process();

    }
}