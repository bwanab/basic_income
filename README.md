# basic_income

A reimplementation of Chris Stucchio's "Modelling a Basic Income with Python and Monte Carlo Simulation" using
clojure and Incanter.

See http://www.chrisstucchio.com/blog/2013/basic_income_vs_basic_job.html

The motivation for this project is that I've been using Incanter for various statistica analysis. I was impressed with the
simulation that Chris did and thought I'd try to reimplement it using Incanter and it turned out to be relatively easy.

No attempt has been made to improve on the original or change it in any way. As a disclaimer, I don't necessarily agree or
disagree with Chris' conclusions, but I am impressed by his willingness to build a logical framework to test his ideas
in the context of an otherwise contentious issue.

One caveat on the implementation. In Chris' python version using matplotlib, he's able to produce a nice pair of histograms
that show the results on the same x-axis. Using the built-in functionality of Incanter, I couldn't see a way to do that so
my implementation simply prints two separate histograms on their own x-axis, so you've got to look more carefully to get the
picture that the original makes evident.

## Usage

I assume you have leiningen working.

```sh
clone the repo
cd to where you put it
lein repl
```

```clj
(use 'basic-income.core)
```

## License

Copyright © 2013 Bill Allen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
