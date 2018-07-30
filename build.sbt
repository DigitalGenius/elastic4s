lazy val root = Project("elastic4s", file("."))
  .settings(
    publish := {},
    publishArtifact := false,
    name := "elastic4s",
    mappings in (Compile, packageSrc) ++= {
      val base  = (Compile / sourceManaged).value
      val files = (Compile / managedSources).value
      files.map { f =>
        (f, f.relativeTo(base).get.getPath)
      }
    }
  )
  .aggregate(
    core,
    tcp,
    http,
    embedded,
    testkit,
    circe
  )

lazy val core = Project("elastic4s-core", file("elastic4s-core"))
  .settings(
    name := "elastic4s-core",
    libraryDependencies ++= Seq(
      "joda-time"                    % "joda-time"             % "2.9.9",
      "com.fasterxml.jackson.core"   % "jackson-core"          % JacksonVersion,
      "com.fasterxml.jackson.core"   % "jackson-databind"      % JacksonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonVersion
    )
  )

lazy val tcp = Project("elastic4s-tcp", file("elastic4s-tcp"))
  .settings(
    name := "elastic4s-tcp",
    libraryDependencies ++= Seq(
      "org.elasticsearch"          % "elasticsearch"           % ElasticsearchVersion,
      "org.locationtech.spatial4j" % "spatial4j"               % "0.6",
      "com.vividsolutions"         % "jts"                     % "1.13",
      "io.netty"                   % "netty-all"               % "4.1.16.Final",
      "org.apache.lucene"          % "lucene-core"             % LuceneVersion,
      "org.apache.lucene"          % "lucene-analyzers-common" % LuceneVersion,
      "org.apache.lucene"          % "lucene-backward-codecs"  % LuceneVersion,
      "org.apache.lucene"          % "lucene-grouping"         % LuceneVersion,
      "org.apache.lucene"          % "lucene-highlighter"      % LuceneVersion,
      "org.apache.lucene"          % "lucene-join"             % LuceneVersion,
      "org.apache.lucene"          % "lucene-memory"           % LuceneVersion,
      "org.apache.lucene"          % "lucene-misc"             % LuceneVersion,
      "org.apache.lucene"          % "lucene-queries"          % LuceneVersion,
      "org.apache.lucene"          % "lucene-queryparser"      % LuceneVersion,
      "org.apache.lucene"          % "lucene-sandbox"          % LuceneVersion,
      "org.apache.lucene"          % "lucene-spatial"          % LuceneVersion,
      "org.apache.lucene"          % "lucene-spatial-extras"   % LuceneVersion,
      "org.apache.lucene"          % "lucene-spatial3d"        % LuceneVersion,
      "org.apache.lucene"          % "lucene-suggest"          % LuceneVersion,
      "org.elasticsearch.client"   % "transport"               % ElasticsearchVersion,
      "org.apache.lucene"          % "lucene-join"             % LuceneVersion,
      "org.apache.logging.log4j"   % "log4j-api"               % Log4jVersion,
      "org.apache.logging.log4j"   % "log4j-core"              % Log4jVersion,
      "org.apache.logging.log4j"   % "log4j-1.2-api"           % Log4jVersion,
      "org.apache.logging.log4j"   % "log4j-slf4j-impl"        % Log4jVersion,
      "com.carrotsearch"           % "hppc"                    % "0.7.1",
      "joda-time"                  % "joda-time"               % "2.9.9",
      "com.fasterxml.jackson.core" % "jackson-core"            % JacksonVersion,
      "com.tdunning"               % "t-digest"                % "3.1"
    )
  )
  .dependsOn(core)

lazy val http = Project("elastic4s-http", file("elastic4s-http"))
  .settings(
    name := "elastic4s-http",
    libraryDependencies ++= Seq(
      "org.elasticsearch.client"     % "elasticsearch-rest-client" % ElasticsearchVersion,
      "org.apache.logging.log4j"     % "log4j-api"                 % Log4jVersion % "test",
      "com.fasterxml.jackson.core"   % "jackson-core"              % JacksonVersion,
      "com.fasterxml.jackson.core"   % "jackson-databind"          % JacksonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala"     % JacksonVersion exclude ("org.scala-lang", "scala-library")
    )
  )
  .dependsOn(core)

lazy val embedded = Project("elastic4s-embedded", file("elastic4s-embedded"))
  .settings(
    name := "elastic4s-embedded",
    libraryDependencies ++= Seq(
      "org.elasticsearch"                % "elasticsearch"            % ElasticsearchVersion,
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-smile" % JacksonVersion,
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor"  % JacksonVersion
    )
  )
  .dependsOn(tcp, http)

lazy val testkit = Project("elastic4s-testkit", file("elastic4s-testkit"))
  .settings(
    name := "elastic4s-testkit",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % ScalatestVersion
    )
  )
  .dependsOn(core, embedded, http)

lazy val circe = Project("elastic4s-circe", file("elastic4s-circe"))
  .settings(
    name := "elastic4s-circe",
    libraryDependencies += "io.circe" %% "circe-core"    % CirceVersion,
    libraryDependencies += "io.circe" %% "circe-generic" % CirceVersion,
    libraryDependencies += "io.circe" %% "circe-parser"  % CirceVersion
  )
  .dependsOn(core)

lazy val noPublishSettings = Seq(
  publish := {},
  publishLocal := {},
  publishArtifact := false
)
