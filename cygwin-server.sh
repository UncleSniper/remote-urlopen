#!/bin/bash

here="$(dirname -- "${BASH_SOURCE-$0}")"
jar="$(cygpath -aw -- "$here/dist/org.unclesniper.remurlopen.jar")"

java -cp "$jar" org.unclesniper.remurlopen.URLOpenServer "$@"
