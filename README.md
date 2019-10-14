## Command-line interface to java2scala.com

### What

Command-line utility for converting source code in Java to Scala, via
http://javatoscala.com


### How

The fastest way:
1. Download a pre-built release from: https://github.com/piotrklibert/java2scala/releases/download/0.0.1/java2scala-converter.zip
2. Unzip it
3. Make sure the unzipped `./java2scala/bin/` directory is on your `PATH`
4. Run with `java2scala -d <dirname>`


### Why

Despite it being **abandoned and not develeped anymore**, the *java2scala.com*[^2]
is still up and - surprisingly enough - still works pretty well.

It's possible to build and run the software behind it locally[^1], but with the
last commit being in 2015 I found it really hard to make it work on Scala 2.13,
which is why I turned to the online solution.

I don't like manually copying and pasting dozens of files, so I wrote this
script, which searches given directory for Java files and converts all of them
to Scala files. Scala files are saved next to Java ones, with only extension
changed.

The script is written in Scala itself and I'm surprised at how short and clean
the implementation turned out, especially given the "static typing" and "on JVM"
properties of the language.

The resulting distribution is still 27Mb though...

[^1]: https://github.com/scalagen/scalagen

[^2]: https://github.com/koofr/javatoscala
