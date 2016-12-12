package com.github.fedragon.anybar

import sbt._
import Keys._

object AnyBarPlugin extends AutoPlugin {

  object DotStyles {
    sealed trait DotStyle { def value: String }
    object Black extends DotStyle { val value = "black" }
    object Blue extends DotStyle { val value = "blue" }
    object Cyan extends DotStyle { val value = "cyan" }
    object Exclamation extends DotStyle { val value = "exclamation" }
    object Green extends DotStyle { val value = "green" }
    object Orange extends DotStyle { val value = "orange" }
    object Purple extends DotStyle { val value = "purple" }
    object Question extends DotStyle { val value = "question" }
    object Yellow extends DotStyle { val value = "yellow" }
    object White extends DotStyle { val value = "white" }
  }

  object autoImport {
    lazy val anybar = taskKey[Unit]("Interacts with AnyBar")
  }

  import autoImport._
  import DotStyles._

  override def trigger = allRequirements

  def anyBarAfter[T](
    t: TaskKey[T],
    c: Configuration,
    onError: DotStyle,
    onSuccess: DotStyle): Seq[Project.Setting[_]] =
    (t in c) <<= (t in c) mapR {
      case Inc(inc: Incomplete) =>
        AnyBar(onError.value)
        throw inc
      case Value(value) =>
        AnyBar(onSuccess.value)
        value
    }

  val anyBarAfterCompile: Seq[Def.Setting[_]] =
    anyBarAfter(compile, Compile, Exclamation, Green)

  val anyBarAfterTest: Seq[Def.Setting[_]] =
    anyBarAfter(test, Test, Exclamation, Green)
}

object AnyBar {
  import java.net.{DatagramPacket, DatagramSocket, InetAddress}

  private val Host = InetAddress.getByName("localhost")
  private val Port = 1738

  def apply(newStyle: String) = {
    val socket = new DatagramSocket
    val data = newStyle.getBytes
    val packet = new DatagramPacket(data, data.size, Host, Port)

    socket.send(packet)
    socket.close()
  }
}