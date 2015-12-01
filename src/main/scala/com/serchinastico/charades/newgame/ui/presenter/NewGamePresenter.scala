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

package com.serchinastico.charades.newgame.ui.presenter

import com.serchinastico.charades.base.ui.presenter.BasePresenter
import com.serchinastico.charades.newgame.GetPlayers._
import com.serchinastico.charades.newgame.ui.presenter.NewGamePresenter.View
import com.serchinastico.charades.newgame.usecases.GetPlayers

class NewGamePresenter(override val view: View) extends BasePresenter {
  override def update(): Unit = {
    super.update()
    new GetPlayers.Builder().withOnSuccess((players: Players) => {
      view.showPlayers(players)
    }).build().execute()
  }
}

object NewGamePresenter {

  trait View extends BasePresenter.View {
    def showPlayers(players: Players)
  }

}
