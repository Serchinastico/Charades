/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Sergio Gutiérrez Mota
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.serchinastico.charades.base.ui.view.linedivider;

import com.serchinastico.charades.base.math.MathExtension;

/**
 * Class representing a point or a vector in a cartesian system.
 */
public class PointVector {

  private final Type type;
  private final double x;
  private final double y;

  private PointVector(Type type, double x, double y) {
    this.type = type;
    this.x = x;
    this.y = y;
  }

  public static PointVector createPoint(double x, double y) {
    return new PointVector(Type.POINT, x, y);
  }

  public static PointVector createVector(double x, double y) {
    return new PointVector(Type.VECTOR, x, y);
  }

  public static PointVector createVectorFromTwoPoints(PointVector p1, PointVector p2) {
    double vx = p2.getX() - p1.getX();
    double vy = p2.getY() - p1.getY();
    return new PointVector(Type.VECTOR, vx, vy);
  }

  public static PointVector createMidPoint(PointVector p1, PointVector p2) {
    double px = MathExtension.getMidValue(p1.getX(), p2.getX());
    double py = MathExtension.getMidValue(p1.getY(), p2.getY());
    return new PointVector(Type.POINT, px, py);
  }

  private Type getType() {
    return type;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public float getFloatX() {
    return (float) x;
  }

  public float getFloatY() {
    return (float) y;
  }

  public PointVector add(PointVector pv) {
    return new PointVector(pv.getType(), getX() + pv.getX(), getY() + pv.getY());
  }

  public PointVector scale(double scale) {
    return createVector(getX() * scale, getY() * scale);
  }

  public double dot(PointVector pv) {
    return getX() * pv.getX() + getY() * pv.getY();
  }

  public double getMagnitude() {
    return Math.sqrt(getX() * getX() + getY() * getY());
  }

  public double angle(PointVector pv) {
    return Math.acos(dot(pv) / (getMagnitude() * pv.getMagnitude()));
  }

  public PointVector normalize() {
    double magnitude = getMagnitude();
    return createVector(getX() / magnitude, getY() / magnitude);
  }

  @Override
  public String toString() {
    return "PointVector{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  private enum Type {
    POINT, VECTOR
  }
}
