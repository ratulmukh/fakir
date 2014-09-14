import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "fakir"

version := "0.0"

scalaVersion := "2.11.2"

packageArchetype.java_application

packageDescription in Debian := "Fakir"

maintainer in Debian := "Ratul Mukhopadhyay"

libraryDependencies += "org.clapper" %% "argot" % "1.0.3"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

bashScriptExtraDefines += """addJava "-Dconfig.file=${app_home}/../conf/app.config""""


