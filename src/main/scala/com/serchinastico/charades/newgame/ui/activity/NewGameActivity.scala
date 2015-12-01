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

package com.serchinastico.charades.newgame.ui.activity

import android.content.{Intent, Context}
import android.os.Bundle
import android.widget.EditText
import com.serchinastico.charades.base.ui.activity.BaseActivity
import com.serchinastico.charades.newgame.GetPlayers.Players
import com.serchinastico.charades.newgame.domain.model.Player
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter.View
import com.serchinastico.charades.{R, TR, TypedFindView}

class NewGameActivity extends BaseActivity with View with TypedFindView {

  override val presenter = new NewGamePresenter(this)
  lazy val numberOfRoundsView = findView[EditText](TR.et_number_of_rounds)
  lazy val roundTimeView = findView[EditText](TR.et_round_time)
  lazy val numberOfTeamsView = findView[EditText](TR.et_number_of_teams)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_new_game)
  }

  override def showPlayers(players: Players): Unit = {
  }
}

object NewGameActivity {
  def open(context: Context) = {
    val intent: Intent = new Intent(context, classOf[NewGameActivity])
    context.startActivity(intent)
  }
}
