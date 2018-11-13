#!/bin/bash

make
if [[ $? -eq 0 ]]; then
    pushd bin
    java Main
    popd
fi

