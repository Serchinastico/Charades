package com.serchinastico.charades.newgame.ui.activity

import android.os.Bundle
import com.serchinastico.charades.R
import com.serchinastico.charades.base.ui.activity.BaseActivity
import com.serchinastico.charades.base.ui.presenter.BasePresenter
import com.serchinastico.charades.newgame.GetPlayers.Players
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter.View

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

class NewGameActivity extends BaseActivity with View {

  override var presenter: BasePresenter = new NewGamePresenter(this)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.new_game)
  }

  override def showPlayers(players: Players): Unit = {

  }
}
