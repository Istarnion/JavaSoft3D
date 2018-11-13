#!/bin/bash

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
pushd ${SCRIPTPATH}

make
if [[ $? -eq 0 ]]; then
    pushd bin
    java Main
    popd
fi

popd

