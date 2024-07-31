package com.tester.pkgtest.exceptions;

import java.io.IOException;

public class Parent {

    public void process() throws IOException {
        throw new IOException();

    }
}
