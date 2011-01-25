import sbt._

class RootProject(info: ProjectInfo) extends ParentProject(info)
{
  val scalatools_release = "Scala Tools Snapshot" at
  "http://scala-tools.org/repo-snapshots/"

  val liftVersion = "2.2"

  lazy val core = project("core", "Core", info => new CoreProject(this, info))
  lazy val web = project("web", "Web", info => new WebProject(this, info), core)

  // couldn't do this if SubProject was separate
  // lazy val printCustom =
  //     task { println(logStore.info); None }
}

protected class WebProject(parent: RootProject, info: ProjectInfo) extends DefaultWebProject(info) {
  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % parent.liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % parent.liftVersion % "compile->default",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default",
    "junit" % "junit" % "4.5" % "test->default",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test->default",
    "mysql" % "mysql-connector-java" % "5.1.13"
  ) ++ super.libraryDependencies
}

protected class CoreProject(parent: RootProject, info: ProjectInfo) extends DefaultProject(info) {
}
