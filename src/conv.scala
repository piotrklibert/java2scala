import scala.util.Using


object http {
  import okhttp3.{OkHttpClient, Request, FormBody}
  val url = "http://javatoscala.com/"
  val client = new OkHttpClient()

  def post(original: String): String = {
    val formBody = new FormBody.Builder().add("original", original).build()
    val request = new Request.Builder().url(url).post(formBody).build()
    Using.resource( client.newCall(request).execute() ) { _.body().string() }
  }
}


object conv {
  import org.jsoup.Jsoup
  import org.jsoup.nodes.Element

  def byId(soup: Element, id: String) = Option(soup.getElementById(id))
  def byTag(soup: Element, tag: String) = Option(soup.getElementsByTag(tag).first)

  def convert(original: String): String = {
    val soup = Jsoup.parse(http.post(original))
    val elem = byId(soup, "converted").getOrElse {
      byId(soup, "convert-form").flatMap(byTag(_, "textarea")).get
    }
    elem.wholeText
  }
}


object Main extends App {
  import better.files._
  val (helpArg, dirArg) = ("--?h(elp)?".r, "--?d(irectory)?".r)
  val arg =
    args.toList match {
      case dirArg :: dir :: _ => dir
      case helpArg :: _       => "help"
      case _                  => "./src/java"
    }

  if (arg == "help") {
    println("java2scala: Java to Scala command-line converter.")
    System.exit(0)
  }

  val javaDir = File(arg)
  println(s"Searching for Java files in: ${javaDir}")
  assert(javaDir.exists)

  javaDir.listRecursively()
    .filter{ _.isRegularFile }
    .filter{ _.extension == Some(".java") }
    .tapEach(println)
    .map{ p => (p, p.contentAsString) }
    .map{ case (p, txt) => (p, conv.convert(txt)) }
    .map{ case (p, txt) =>
      val newPath = p.pathAsString.replace(".java", ".scala").toFile
      newPath.writeText(txt)
      (p, newPath)
    }
    .tapEach(_ => Thread.sleep(1000))
    .toList  // otherwise nothing would happen (all calls above are lazy)
}
