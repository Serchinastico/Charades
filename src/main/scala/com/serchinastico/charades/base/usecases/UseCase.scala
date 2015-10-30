package com.serchinastico.charades.base.usecases

import java.util.concurrent.{ExecutorService, Executors}

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
 * It will handle calls from the main thread, execute the use case itself in another thread and return its results back
 * on the main thread.
 */
trait UseCase[R] {

  private def executor: ExecutorService = Executors.newCachedThreadPool()

  protected def successCallback(result: R): Unit = {}

  protected def errorCallback(error: Exception): Unit = {}

  protected def execute(runnable: () => R): Unit = {
    executor.submit(new Runnable {
      override def run(): Unit = {
        try {
          val result: R = runnable()
          successCallback(result)
        } catch {
          case e: Exception => errorCallback(e)
        }
      }
    })
  }

}
