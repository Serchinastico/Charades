package com.serchinastico.charades.base.usecases

import java.util.concurrent.ExecutorService

import android.os.Handler

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

/**
 * Trait representing an application use case.
 * It will handle calls from the main thread, execute the use case itself in another thread and
 * return its results back on the main thread.
 */
trait UseCase[R] {

  def execute(): Unit = {
    executor.submit(new Runnable {
      override def run(): Unit = {
        try {
          val result: R = runnable()
          notifySuccess(result)
        } catch {
          case e: Exception => notifyError(e)
        }
      }
    })
  }

  protected def mainThreadHandler: Handler
  protected def executor: ExecutorService
  protected def onSuccess: R => Unit = UseCase.identityOnSuccess
  protected def onError: Exception => Unit = UseCase.identityOnError
  protected def runnable(): R

  private def notifySuccess(result: R): Unit = {
    mainThreadHandler.post(new Runnable() {
      override def run(): Unit = {
        onSuccess(result)
      }
    })
  }

  private def notifyError(error: Exception): Unit = {
    mainThreadHandler.post(new Runnable() {
      override def run(): Unit = {
        onError(error)
      }
    })
  }
}

object UseCase {
  def identityOnSuccess[R](result: R): Unit = {}

  def identityOnError(error: Exception): Unit = {}
}