android.Plugin.androidBuild

// Specifying the Android target Sdk version
platformTarget in Android := "android-23"

// Application Name
name := """Charades"""

// Application Version
version := "1.0.0"

// Scala version
scalaVersion := "2.11.7"

// Repositories for dependencies
resolvers ++= Seq(Resolver.mavenLocal,
  DefaultMavenRepository,
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.typesafeIvyRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.defaultLocal,
  Resolver.jcenterRepo)

// Override the run task with the android:run
run <<= run in Android

compile <<= compile in Android

proguardScala in Android := true

useProguard in Android := true

proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions += "-target:jvm-1.7"

libraryDependencies += aar("uk.co.chrisjenx" % "calligraphy" % "2.1.0")

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.1.0" % "provided"

libraryDependencies += "io.realm" % "realm-android" % "0.84.2"

libraryDependencies += "com.android.support" % "recyclerview-v7" % "23.0.1"

fork in run := true