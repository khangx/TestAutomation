package com.qa;

/**
 * Created by Robbie on 9/23/2016.
 * Skeletal defensive coding to keep some features intact
 */
public interface BaseFactoryImp {
    /**
     * Setup browser and test landing page
     * Adjust your annotation based on your test needs
     */
    void testSetup();

    /**
     * Tear down firefox
     * Adjust your annotation based on your test needs
     */
    void testTearDown();
}
