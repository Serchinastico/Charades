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
