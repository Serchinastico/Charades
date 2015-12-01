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
