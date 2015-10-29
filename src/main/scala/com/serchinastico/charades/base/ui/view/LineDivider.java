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

package com.serchinastico.charades.base.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.serchinastico.charades.R;
import com.serchinastico.charades.base.ui.view.linedivider.Line;
import com.serchinastico.charades.base.ui.view.linedivider.PointVector;

public class LineDivider extends View {

  private static final int DEFAULT_COLOR = Color.BLACK;
  private static final int DEFAULT_LINE_WIDTH = 5;
  private static final float DEFAULT_ANGLE = 0;
  private static final int DEFAULT_DASH_GAP = 10;
  private static final int DEFAULT_DASH_WIDTH = 15;

  private int color = DEFAULT_COLOR;
  private float lineWidth = DEFAULT_LINE_WIDTH;
  private float angle = DEFAULT_ANGLE;
  private float dashGap = DEFAULT_DASH_GAP;
  private float dashWidth = DEFAULT_DASH_WIDTH;

  public LineDivider(Context context) {
    this(context, null);
  }

  public LineDivider(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LineDivider(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public LineDivider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initialize(context, attrs);
  }

  private void initialize(Context context, AttributeSet attrs) {
    if (attrs == null) {
      return;
    }

    TypedArray a = context.getTheme().obtainStyledAttributes(
        attrs,
        R.styleable.LineDivider,
        0, 0);

    try {
      color = a.getColor(R.styleable.LineDivider_ldColor, DEFAULT_COLOR);
      lineWidth = a.getDimension(R.styleable.LineDivider_ldWidth, DEFAULT_LINE_WIDTH);
      angle = a.getFloat(R.styleable.LineDivider_ldAngle, DEFAULT_ANGLE);
      dashGap = a.getDimension(R.styleable.LineDivider_ldDashGap, DEFAULT_DASH_GAP);
      dashWidth = a.getDimension(R.styleable.LineDivider_ldDashWidth, DEFAULT_DASH_WIDTH);
    } finally {
      a.recycle();
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    PointVector bottomLeftCorner = PointVector.createPoint(0, 0);
    PointVector topRightCorner = PointVector.createPoint(getMeasuredWidth(), getMeasuredHeight());

    PointVector center = PointVector.createMidPoint(bottomLeftCorner, topRightCorner);
    Line lineDirection = Line.createWithPointAndAngle(center, angle);

    PointVector bottomMidPoint = PointVector.createPoint(center.getX(), 0);
    PointVector fromBottomLeftToCenter = PointVector.createVectorFromTwoPoints(bottomLeftCorner, center);
    PointVector fromBottomLeftToBottomMid = PointVector.createVectorFromTwoPoints(bottomLeftCorner, bottomMidPoint);

    PointVector intersection;
    if (Math.toRadians(angle) < fromBottomLeftToBottomMid.angle(fromBottomLeftToCenter)) {
      intersection = PointVector.createPoint(0, lineDirection.getY(0));
    } else {
      intersection = PointVector.createPoint(lineDirection.getX(0), 0);
    }

    PointVector fromIntersectionToCenter = PointVector.createVectorFromTwoPoints(intersection, center);
    PointVector limitPoint = fromIntersectionToCenter.scale(2);

    PointVector sourcePoint = intersection;
    PointVector targetPoint = sourcePoint.add(limitPoint);

    Path linePath = new Path();
    linePath.moveTo(sourcePoint.getFloatX(), sourcePoint.getFloatY());
    linePath.lineTo(targetPoint.getFloatX(), targetPoint.getFloatY());

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(lineWidth);
    paint.setColor(color);
    paint.setPathEffect(new DashPathEffect(new float[]{dashWidth, dashGap}, 0));
    canvas.drawPath(linePath, paint);
  }

}
