package com.tester.pkgtest.ooa;

public abstract class Run implements Exercise {

    private double distanceKm;

    public Run(double x) {
        this.distanceKm = x;
    }

    public double distanceCovered() {
        return distanceKm;
    }
}
