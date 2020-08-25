# "Hello, Indigo!"

![Screen grab of "Hello, Indigo!"](screenshot.png)

This is a small "hello, world"-like project to help people get started with indigo.

## Prerequisites

You will need to have a JDK, NPM, and Mill installed.

If you intend to follow the instructions below, you will also need to have Electron globally installed.

On a mac, install with:

```bash
npm install -g electron
```

On linux, the above might work, but if when you try to run the demo you see repeated messaged stating that Electron failed to install correctly, this has been seen to solve the problem:

```bash
sudo npm install -g electron --unsafe-perm=true --allow-root
```

## Running the demo

Assuming you have [Mill](http://www.lihaoyi.com/mill/) and electron set up as the guide suggests, to run the demo, do the following from your command line:

```bash
mill helloindigo.runGame
```

The git commit history shows how the demo was built up.

This demo has been built using Mill, but you can use SBT, please see the [set up guide](https://indigoengine.io/docs/quickstart/setup-and-configuration).


