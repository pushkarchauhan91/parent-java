package com.company.template.impl;

public abstract class DataProcessor {

    // Template method (final to prevent override)
    public final void process() {
        readData();
        processData();
        writeData();
    }

    protected abstract void readData();

    protected abstract void processData();

    protected abstract void writeData();
}