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

package com.serchinastico.charades.base.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.serchinastico.charades.base.ui.presenter.BasePresenter
import com.serchinastico.charades.base.ui.presenter.BasePresenter.View
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

trait BaseActivity extends FragmentActivity with View {

  val presenter: BasePresenter

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
