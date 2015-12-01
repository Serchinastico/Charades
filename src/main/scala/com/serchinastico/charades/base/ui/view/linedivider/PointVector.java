/*
 * Copyright (C) 2015 Sergio Gutiérrez Mota
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
