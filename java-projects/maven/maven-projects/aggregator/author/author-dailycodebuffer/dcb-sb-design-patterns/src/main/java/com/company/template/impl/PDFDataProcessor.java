package com.company.template.impl;

public class PDFDataProcessor extends DataProcessor {

    @Override
    protected void readData() {
        System.out.println("Reading data from PDF file");
    }

    @Override
    protected void processData() {
        System.out.println("Processing PDF data");
    }

    @Override
    protected void writeData() {
        System.out.println("Writing data to PDF output");
    }
}