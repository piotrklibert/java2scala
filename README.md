## Command-line interface to java2scala.com

Despite it being abandoned and not develeped anymore, the http://java2scala.com
is still up and - surprisingly enough - still works pretty well. It's possible
to build and run the software behind it locally, but I found it way too tedious
for converting a dozen files.

That being said, a dozen or two Java files are still a lot of clicking to
convert, and I'm allergic to clicking, so I wrote a little script for converting
files from the command-line.

The script is written in Scala itself and I'm surprised at how short and clean
the implementation turned out, especially given the "static typing" and "on JVM"
properties of the language.

The resulting distribution is still 27Mb though...
