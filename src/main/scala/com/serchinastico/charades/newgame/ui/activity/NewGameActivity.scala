package com.serchinastico.charades.newgame.ui.activity

import android.content.{Context, Intent}
import android.os.Bundle
import android.widget.TextView
import com.serchinastico.charades.base.ui.activity.BaseActivity
import com.serchinastico.charades.newgame.domain.model.Player
import com.serchinastico.charades.newgame.GetPlayers.Players
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter.View
import com.serchinastico.charades.{R, TR, TypedFindView}

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

class NewGameActivity extends BaseActivity with View with TypedFindView {

  override val presenter = new NewGamePresenter(this)
  lazy val playersView = findView[TextView](TR.tv_players)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_new_game)
  }

  override def showPlayers(players: Players): Unit = {
    val playerNames: String = players.foldLeft("")((acc: String, player: Player) => {
      acc + " " + player.fullName
    })

    playersView.setText(playerNames)
  }
}

object NewGameActivity {
  def open(context: Context) = {
    val intent: Intent = new Intent(context, classOf[NewGameActivity])
    context.startActivity(intent)
  }
}
