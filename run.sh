#!/bin/sh
cd $(dirname $0)

pushd ch2

pushd issue-manager-1
./gradlew clean build
popd

pushd issue-manager-2
./gradlew clean build
popd

pushd issue-manager-3
./gradlew clean build
popd

pushd issue-manager-4
./gradlew clean build
popd

pushd issue-manager-5
./gradlew clean build
popd

pushd issue-manager-6
./gradlew clean build
popd

popd
pushd ch3

pushd network-manager-1
./gradlew clean build
popd

pushd network-manager-2
./gradlew clean build
popd

pushd network-manager-3
./gradlew clean build
popd

pushd network-manager-4
./gradlew clean build
popd

pushd network-manager-5
./gradlew clean build
popd

pushd network-manager-6
./gradlew clean build
popd

pushd network-manager-7
./gradlew clean build
popd

pushd network-manager-8
./gradlew clean build
popd

popd
pushd ch4

pushd teammates-1
./gradlew clean build
popd

pushd teammates-2
./gradlew clean build
popd

pushd teammates-3
./gradlew clean build
popd

pushd teammates-4
./gradlew clean build
popd

pushd teammates-5
./gradlew clean build
popd

popd
pushd ch5

pushd teammates-1
./gradlew clean build
popd

pushd teammates-2
./gradlew clean build
popd

pushd teammates-3
./gradlew clean build
popd

pushd teammates-4
./gradlew clean build
popd

pushd teammates-5
./gradlew clean build
popd

popd

exit
