import Defaults._

sbtPlugin := true

organization := "com.github.fedragon"

name := "sbt-anybar"

description := "Interact with AnyBar from SBT"

homepage := Some(url("https://github.com/fedragon/sbt-anybar"))

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some(
    "snapshots" at nexus + "content/repositories/snapshots"
  )
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <licenses>
    <license>
      <name>MIT</name>
      <url>https://github.com/fedragon/sbt-anybar/blob/master/LICENSE</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/fedragon/sbt-anybar</connection>
    <developerConnection>scm:git:git@github.com:fedragon/sbt-anybar</developerConnection>
    <url>github.com/fedragon/sbt-anybar</url>
  </scm>
  <developers>
    <developer>
      <id>fedragon</id>
      <name>Federico Ragona</name>
    </developer>
  </developers>
)
