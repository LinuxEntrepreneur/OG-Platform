/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.volatility.surface;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.opengamma.util.tuple.Pair;

/**
 * 
 * @author emcleod
 */
public class ConstantVolatilitySurfaceTest {
  private static final double SIGMA = 0.2;
  private static final Pair<Double, Double> XY = new Pair<Double, Double>(3., 5.);
  private static final double SHIFT = 0.01;
  private static final VolatilitySurface SURFACE = new ConstantVolatilitySurface(SIGMA);
  private static final double EPS = 1e-15;

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNull() {
    new ConstantVolatilitySurface(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNegativeVolatility() {
    new ConstantVolatilitySurface(-SIGMA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetVolatilityWithNullXY() {
    SURFACE.getVolatility(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetVolatilityWithNullX() {
    SURFACE.getVolatility(new Pair<Double, Double>(null, 2.));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetVolatilityWithNullY() {
    SURFACE.getVolatility(new Pair<Double, Double>(2., null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetParallelShift() {
    SURFACE.withParallelShift(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSingleShiftWithNullPair() {
    SURFACE.withSingleShift(null, 3.);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSingleShiftWithNullX() {
    SURFACE.withSingleShift(new Pair<Double, Double>(null, 2.), 3.);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSingleShiftWithNullY() {
    SURFACE.withSingleShift(new Pair<Double, Double>(2., null), 3.);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleShiftsWithNull() {
    SURFACE.withMultipleShifts(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleShiftsWithNullX() {
    SURFACE.withMultipleShifts(Collections.<Pair<Double, Double>, Double> singletonMap(new Pair<Double, Double>(null, 2.), 2.));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleShiftsWithNullY() {
    SURFACE.withMultipleShifts(Collections.<Pair<Double, Double>, Double> singletonMap(new Pair<Double, Double>(2., null), 2.));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleShiftsWithNullShift() {
    SURFACE.withMultipleShifts(Collections.<Pair<Double, Double>, Double> singletonMap(new Pair<Double, Double>(2., 2.), null));
  }

  @Test
  public void test() {
    assertEquals(SIGMA, SURFACE.getVolatility(XY), EPS);
    assertEquals(Collections.<Pair<Double, Double>> emptySet(), SURFACE.getXYData());
  }

  @Test
  public void testParallelShift() {
    final VolatilitySurface surface = SURFACE.withParallelShift(SHIFT);
    assertEquals(SIGMA + SHIFT, surface.getVolatility(XY), EPS);
  }

  @Test
  public void testSingleShift() {
    final VolatilitySurface surface = SURFACE.withSingleShift(XY, SHIFT);
    assertEquals(SIGMA + SHIFT, surface.getVolatility(XY), EPS);
  }

  @Test
  public void testMultipleShift() {
    final VolatilitySurface surface = SURFACE.withMultipleShifts(Collections.<Pair<Double, Double>, Double> singletonMap(XY, SHIFT));
    assertEquals(SIGMA + SHIFT, surface.getVolatility(XY), EPS);
  }
}
