package com.tester.pkgtest.ooa;

public abstract class Walk implements Exercise {

    private double distanceKm;

    public Walk(double x) {

        this.distanceKm = x;

    }

    public double distanceCovered() {
        return distanceKm;
    }
}
