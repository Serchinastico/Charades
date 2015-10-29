/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Sergio Gutiérrez Mota
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.serchinastico.charades.base.ui.view.linedivider;

public class Line {
  private final double slope;
  private final double yIntercept;

  private Line(double slope, double yIntercept) {
    this.slope = slope;
    this.yIntercept = yIntercept;
  }

  /**
   * Creates a new line that crosses in the specified point and has the provided angle.
   *
   * @param point          the point where the line passes
   * @param angleInDegrees the angle of the line in degrees. An angle of 0 is considered as an horizontal line and
   *                       increasing angle values are interpreted in anticlockwise direction just as in a unit circle.
   *                       Valid values are in the range [0-180]
   */
  public static Line createWithPointAndAngle(PointVector point, float angleInDegrees) {
    double angleInRadians = Math.toRadians(angleInDegrees);
    double slope = Math.tan(angleInRadians);
    double yIntercept = point.getY() - (slope * point.getX());

    return new Line(slope, yIntercept);
  }

  public double getX(double y) {
    return (y - yIntercept) / slope;
  }

  public double getY(double x) {
    return slope * x + yIntercept;
  }

  @Override
  public String toString() {
    return "Line{" +
        "slope=" + slope +
        ", yIntercept=" + yIntercept +
        '}';
  }
}
