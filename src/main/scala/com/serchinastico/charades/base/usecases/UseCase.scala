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

package com.serchinastico.charades.base.usecases

import java.util.concurrent.ExecutorService

import android.os.Handler

/**
 * Trait representing an application use case.
 * It will handle calls from the main thread, execute the use case itself in another thread and
 * return its results back on the main thread.
 */
trait UseCase[I, O] {

  protected def executor: ExecutorService
  protected def responseHandler: Handler
  protected def onSuccess: Option[O => Unit] = None
  protected def onFailure: Option[Exception => Unit] = None

  protected def runnable(input: I): O

  def execute(input: I): Unit = {
    executor.submit(new Runnable {
      override def run(): Unit = {
        try {
          val output: O = runnable(input)
          notifySuccess(output)
        } catch {
          case e: Exception => notifyError(e)
        }
      }
    })
  }

  private def notifySuccess(output: O): Unit = {
    responseHandler.post(new Runnable() {
      override def run(): Unit = {
        onSuccess match {
          case Some(f) => f(output)
          case None => ()
        }
      }
    })
  }

  private def notifyError(error: Exception): Unit = {
    responseHandler.post(new Runnable() {
      override def run(): Unit = {
        onFailure match {
          case Some(f) => f(error)
          case None => ()
        }
      }
    })
  }
}