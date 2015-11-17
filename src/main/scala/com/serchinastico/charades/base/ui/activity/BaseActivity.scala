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

package com.serchinastico.charades.base.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.serchinastico.charades.base.ui.presenter.BasePresenter
import com.serchinastico.charades.base.ui.presenter.BasePresenter.View
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

trait BaseActivity extends FragmentActivity with View {

  var presenter: BasePresenter

  override def attachBaseContext(newBase: Context): Unit = {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    presenter.initialize()
  }

  override def onResume(): Unit = {
    super.onResume()
    presenter.update()
  }

  override def onPause(): Unit = {
    super.onPause()
    presenter.pause()
  }

  override def onDestroy(): Unit = {
    super.onDestroy()
    presenter.finish()
  }
}
