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

package com.serchinastico.charades.newgame.usecases

import com.serchinastico.charades.base.usecases.BackgroundUseCase
import com.serchinastico.charades.newgame.GetPlayers.Players
import com.serchinastico.charades.newgame.domain.model.Player

class GetPlayers(override val onSuccess: Option[Players => Unit] = None,
                 override val onFailure: Option[Exception => Unit] = None) extends BackgroundUseCase[Unit, Players] {

  override protected def runnable(input: Unit): Players = {
    Array(new Player("https://www.google.es", "Sergio"))
  }
}

object GetPlayers {

  class Builder() {
    var onSuccess: Option[Players => Unit] = None
    var onFailure: Option[Exception => Unit] = None

    def withOnSuccess(onSuccess: Players => Unit): Builder = {
      this.onSuccess = Some(onSuccess)
      this
    }

    def withOnFailure(onFailure: Exception => Unit): Builder = {
      this.onFailure = Some(onFailure)
      this
    }

    def build(): GetPlayers = {
      new GetPlayers(onSuccess, onFailure)
    }
  }

}
