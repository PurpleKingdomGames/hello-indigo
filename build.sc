import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`
import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

import $ivy.`io.indigoengine::mill-indigo:0.15.0`, millindigo._

object helloindigo extends ScalaJSModule with MillIndigo {
  def scalaVersion   = "3.3.1"
  def scalaJSVersion = "1.13.2"

  val indigoVersion = "0.15.0"

  val indigoOptions: IndigoOptions =
    IndigoOptions.defaults
      .withTitle("Hello, Indigo!")
      .withBackgroundColor("black")

  val indigoGenerators: IndigoGenerators =
    IndigoGenerators.None

  def ivyDeps =
    Agg(
      ivy"io.indigoengine::indigo-json-circe::$indigoVersion",
      ivy"io.indigoengine::indigo::$indigoVersion",
      ivy"io.indigoengine::indigo-extras::$indigoVersion"
    )

  object test extends ScalaJSTests {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit::0.7.29"
    )

    def testFramework = "munit.Framework"

    // override def moduleKind  = T(mill.scalajslib.api.ModuleKind.CommonJSModule)
  }

  def buildGame() = T.command {
    T {
      compile()
      fastLinkJS()
      indigoBuild()()
    }
  }

  def runGame() = T.command {
    T {
      compile()
      fastLinkJS()
      indigoRun()()
    }
  }

}
