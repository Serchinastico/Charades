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

package com.serchinastico.charades.mainmenu.ui.activity

import android.os.Bundle
import android.view
import android.view.View.OnClickListener
import android.widget.RelativeLayout
import com.serchinastico.charades.base.ui.activity.BaseActivity
import com.serchinastico.charades.mainmenu.ui.presenter.MainMenuPresenter
import com.serchinastico.charades.mainmenu.ui.presenter.MainMenuPresenter.View
import com.serchinastico.charades.newgame.ui.activity.NewGameActivity
import com.serchinastico.charades.{R, TR, TypedFindView}

class MainMenuActivity extends BaseActivity with View with TypedFindView {

  override val presenter: MainMenuPresenter = new MainMenuPresenter(this)
  lazy val newGameView = findView[RelativeLayout](TR.rl_new_game)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_main_menu)

    newGameView.setOnClickListener(new OnClickListener {
      override def onClick(v: view.View): Unit = {
        NewGameActivity.open(MainMenuActivity.this)
      }
    })
  }
}
