# "Hello, Indigo!"

![Screen grab of "Hello, Indigo!"](screenshot.png)

This is a small "hello, world"-like project to help people get started with indigo.

The git commit history shows how the demo was built up.

This demo has been built using Mill, but you can use SBT, please see the [set up guide](https://indigoengine.io/docs/quickstart/setup-and-configuration).

Assuming you have [Mill](http://www.lihaoyi.com/mill/) and http-server set up as the guide suggests, to run the demo, do the following from your command line:

```bash
mill helloindigo.buildGame
```

Which will generate output similar to:

```bash
> mill helloindigo.buildGame
(...)
[44/46] helloindigo.indigoBuildJS
dirPath: /Users/(...)/hello-indigo/out/helloindigo/indigoBuildJS/dest
Copying assets...
/Users/(...)/hello-indigo/out/helloindigo/indigoBuildJS/dest/index.html
[46/46] helloindigo.buildGame
```

Then:

1. `cd /Users/(...)/hello-indigo/out/helloindigo/indigoBuildJS/dest/`
2. `http-server -c-1`
3. Navigate to [http://127.0.0.1:8080/](http://127.0.0.1:8080/) in your browser of choice.

If you're running from a fresh checkout, make sure you click on the screen to see the dots! :-)
