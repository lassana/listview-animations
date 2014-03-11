#!/bin/sh

export ANDROID_SDK_TOOLS_VERSION=22.3
export ANDROID_BUILD_TOOLS_VERSION=19.0.3
export ANDROID_API_LEVEL=19

PATH=$(echo $PATH | sed 's/\/opt\/android-sdk-linux//')

export ANDROID_HOME=$PWD/android-sdk-linux

export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/platform-tools
export PATH=$PATH:$ANDROID_HOME/build-tools

set -x

chmod +x drone/install_sdk.sh
./drone/install_sdk.sh > /dev/null

chmod +x gradlew
./gradlew assembleDebug

