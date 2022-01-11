import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`
import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

import $ivy.`io.indigoengine::mill-indigo:0.11.0`, millindigo._

object helloindigo extends ScalaJSModule with MillIndigo {
  def scalaVersion   = "3.1.0"
  def scalaJSVersion = "1.8.0"

  val gameAssetsDirectory: os.Path = os.pwd / "assets"
  val showCursor: Boolean          = true
  val title: String                = "Hello, Indigo!"
  val windowStartWidth: Int        = 550
  val windowStartHeight: Int       = 400

  def buildGame() = T.command {
    T {
      compile()
      fastOpt()
      indigoBuild()()
    }
  }

  def runGame() = T.command {
    T {
      compile()
      fastOpt()
      indigoRun()()
    }
  }

  val indigoVersion = "0.11.0"

  def ivyDeps =
    Agg(
      ivy"io.indigoengine::indigo-json-circe::$indigoVersion",
      ivy"io.indigoengine::indigo::$indigoVersion",
      ivy"io.indigoengine::indigo-extras::$indigoVersion"
    )

  def scalacOptions = super.scalacOptions() ++ ScalacOptions.compile

  override def useECMAScript2015 = T(true)

  object test extends Tests {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit::0.7.29"
    )

    def testFramework = "munit.Framework"

    override def moduleKind        = T(mill.scalajslib.api.ModuleKind.CommonJSModule)
    override def jsEnvConfig       = T(JsEnvConfig.NodeJs(args = List("--dns-result-order=ipv4first")))
    override def useECMAScript2015 = T(true)

    def scalacOptions = super.scalacOptions() ++ ScalacOptions.test
  }

}

object ScalacOptions {

  lazy val compile: Seq[String] =
    Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8",                  // Specify character encoding used by source files.
      "-feature",               // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros", // Allow macro definition (besides implementation and application)
      "-language:higherKinds",         // Allow higher-kinded types
      "-language:implicitConversions", // Allow definition of implicit functions called views
      "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
      "-Xfatal-warnings"               // Fail the compilation if there are any warnings.
      // "-language:strictEquality"       // Scala 3 - Multiversal Equality
    )

  lazy val test: Seq[String] =
    Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8",                  // Specify character encoding used by source files.
      "-feature",               // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros", // Allow macro definition (besides implementation and application)
      "-language:higherKinds",         // Allow higher-kinded types
      "-language:implicitConversions", // Allow definition of implicit functions called views
      "-unchecked"                     // Enable additional warnings where generated code depends on assumptions.
    )

}
