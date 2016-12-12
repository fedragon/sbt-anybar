# sbt-anybar

Updates [AnyBar](https://github.com/tonsky/AnyBar) dot style in response to task results.

## Installation

Add the following line to your local (`project/plugins.sbt`) or global (`~/.sbt/0.13/plugins/plugins.sbt`) configuration:

    resolvers ++= Seq(
        "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"
    )

    addSbtPlugin("com.github.fedragon" % "sbt-anybar" % "1.0.0")

## Usage

Import it in your `build.sbt`:

    import AnyBarPlugin._

and configure it to run after you `compile`:

    anyBarAfterCompile 

and/or `test`:

    anyBarAfterTest

### Hook it to other tasks (or change dot style)

Lower-level function `anyBarAfter` can be used to configure the dot styles or to react to tasks other than `compile` and `test`. For example:

    import DotStyles._

    anyBarAfter(compile, Test, Exclamation, Green)

will update the AnyBar dot every time `compile` task in `Test` config (= `test:compile`) completes, setting it to `green` if compilation succeeded or to `exclamation` if if failed.
All AnyBar styles are defined as subclasses of `DotStyle`.