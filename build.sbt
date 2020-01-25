enablePlugins(ScalaJSBundlerPlugin)

name := "slinky-test"

scalaVersion := "2.12.10"

//resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")

/* If the library is typed up in `DefinitelyTyped` (has `-dt-` in the version string) you'll also need this.
 * The reason why is that versions strings there are just comments, and frequently wrong. Automatically including
 *  that would break your build.
 */
npmDependencies in Compile += "prop-types" -> "15.7.1"
npmDependencies in Compile += "react-bootstrap" -> "0.33.1"
npmDependencies in Compile += "react" -> "16.12.0"
npmDependencies in Compile += "react-dom" -> "16.12.0"
npmDependencies in Compile += "react-proxy" -> "1.1.8"

npmDevDependencies in Compile += "file-loader" -> "5.0.2"
npmDevDependencies in Compile += "style-loader" -> "1.1.3"
npmDevDependencies in Compile += "css-loader" -> "3.4.2"
npmDevDependencies in Compile += "html-webpack-plugin" -> "3.2.0"
npmDevDependencies in Compile += "copy-webpack-plugin" -> "5.1.1"
npmDevDependencies in Compile += "webpack-merge" -> "4.2.2"

libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.6.3"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.3"
libraryDependencies ++= Seq(ScalablyTyped.R.`react-bootstrap`)
libraryDependencies ++= Seq(ScalablyTyped.R.`react-slinky-facade`)
libraryDependencies ++= Seq(ScalablyTyped.P.`prop-types`)

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.0" % Test

scalacOptions += "-P:scalajs:sjsDefinedByDefault"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

version in webpack := "4.41.5"
version in startWebpackDevServer:= "3.10.1"

webpackResources := baseDirectory.value / "webpack" * "*"

webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
webpackConfigFile in Test := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot")
webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()

requireJsDomEnv in Test := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")

addCommandAlias("build", "fullOptJS::webpack")
