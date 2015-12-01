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

package com.serchinastico.charades.base.ui.presenter

import com.serchinastico.charades.base.ui.presenter.BasePresenter.View

trait BasePresenter {
  val view: View

  def initialize(): Unit = {}
  def update(): Unit = {}
  def pause(): Unit = {}
  def finish(): Unit = {}
}

object BasePresenter {
  trait View
}

