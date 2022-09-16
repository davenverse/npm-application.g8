ThisBuild / organization := "$organization$"
ThisBuild / licenses := Seq(License.MIT) // TODO Make Configurable

ThisBuild / scalaVersion := "$scala_version$"
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

val catsV = "2.7.0"
val catsEffectV = "3.3.12"
val fs2V = "3.2.7"
val http4sV = "0.23.13"
val munitCatsEffectV = "1.0.7"

name := "$name$"

libraryDependencies ++= Seq(
  "org.typelevel"               %%% "cats-core"                  % catsV,
  "org.typelevel"               %%% "cats-effect"                % catsEffectV,

  // fs2-io has extremely helpful utilities like Files
  // "co.fs2"                      %%% "fs2-core"                   % fs2V,
  // "co.fs2"                      %%% "fs2-io"                     % fs2V,

  // process has tools to run shell scripts and programs, very handy
  // "io.chrisdavenport"           %%% "process" % "0.0.2",

  // Http Elements you may find useful
  // "org.http4s" %%% "http4s-ember-client" % http4sV,
  // "org.http4s" %%% "http4s-ember-server" % http4sV,
  // "org.http4s" %%% "http4s-circe" % http4sV,

  "org.typelevel"               %%% "munit-cats-effect-3"        % munitCatsEffectV         % Test,

)

// JS Configuration
enablePlugins(ScalaJSPlugin)
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)}
// Required for applications
scalaJSUseMainModuleInitializer := true

// NPM Configuration
enablePlugins(NpmPackagePlugin)
npmPackageAuthor := "$author_name$"
npmPackageDescription := "$project_description$"
npmPackageKeywords := Seq()
npmPackageBinaryEnable := true
// Optimizes the output js
npmPackageStage := org.scalajs.sbtplugin.Stage.FullOpt
// Establishes this has an executable, not necessary for work intended as npm libraries.
npmPackageAdditionalNpmConfig := {
  Map(
    "bin" -> _root_.io.circe.Json.obj(
      // This needs to be set to the value you want your application to be called.
      "$binary_name$" -> _root_.io.circe.Json.fromString("main.js")
    )
  )
}