package cradle.rancune.algo.concurrent

import java.util.concurrent.locks.LockSupport

/**
 * Created by Rancune@126.com on 2020/6/11.
 */

class Print {
    private val lock = Object()
    private var count = 0

    private inner class R(val name: String, val remain: Int) : Runnable {

        override fun run() {
            synchronized(lock) {
                while (count < 100) {
                    while (count % 3 != remain) {
                        try {
                            lock.wait()
                        } catch (e: Exception) {
                        }
                    }
                    if (count <= 100) {
                        println("Thread $name, count: ${count++}")
                    }
                    try {
                        lock.notifyAll()
                    } catch (e: Exception) {
                    }
                }
            }
        }
    }

    fun start() {
        val t1 = object : Thread(R("t1", 0), "t1") {}
        val t2 = object : Thread(R("t2", 1), "t2") {}
        val t3 = object : Thread(R("t3", 2), "t3") {}
        t1.start()
        t2.start()
        t3.start()
    }
}

class Test2 {
    var t1: Thread? = null
    var t2: Thread? = null
    var t3: Thread? = null
    var count = 0

    fun start() {
        t1 = object : Thread("t1") {
            override fun run() {
                while (true) {
                    println("Thread ${currentThread().name}, count: ${count++}")
                    LockSupport.unpark(t2)
                    if (count > 100) {
                        break
                    }
                    LockSupport.park()
                }
            }
        }
        t2 = object : Thread("t2") {
            override fun run() {
                while (true) {
                    LockSupport.park()
                    if (count > 100) {
                        break
                    }
                    println("Thread ${currentThread().name}, count: ${count++}")
                    LockSupport.unpark(t3)
                }
            }
        }
        t3 = object : Thread("t3") {
            override fun run() {
                while (true) {
                    LockSupport.park()
                    if (count > 100) {
                        break
                    }
                    println("Thread ${currentThread().name}, count: ${count++}")
                    LockSupport.unpark(t1)
                }
            }
        }

        t1?.start()
        t2?.start()
        t3?.start()
    }
}

fun main() {
    //Print().start()
    //Test2().start()
    val t = object : Thread("Test") {
        override fun run() {
            LockSupport.park()
            LockSupport.unpark(this)
            println("Thread ${currentThread().name}")
        }
    }
    t.start()
}