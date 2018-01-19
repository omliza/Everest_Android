#!/bin/bash

## This is the IOS version of run-tests.sh
## To use in cloud:
## 		Rename this file to "run-tests.sh"
##		Make sure that testdroid.properties has iOS configuration in place
## To run locally:
##		Make sure that testdroid.properties has iOS configuration in place
##		run "mvn -Dtest=TestName test"

export DYLD_LIBRARY_PATH=${DYLD_LIBRARY_PATH}:/opt/opencv249/lib
export JAVA_HOME=$(/usr/libexec/java_home)

# Name of the desired test script file without extension
#TEST=${TEST:="BitbarSampleTest"}

# SHORT_NAME should be the same as package name of your test script.
# This is used to relocate JUnit xml file and screenshots at the end of this script
#if [[ $TEST == *"BitbarSampleTest"* ]] 
#then
#	SHORT_NAME="sample"
#fi

## Cloud setup
echo "Setting UDID..."
echo $UDID
UDID="${echo $UDID}"
echo "UDID set to ${UDID}"

echo "Starting Appium ..."
/opt/appium/bin/appium.js -U ${UDID} --command-timeout 120  --no-reset >appium.log 2>&1 &

sleep 10

ps -ef|grep appium

echo "Extracting tests.zip..."
unzip tests.zip

## Start test execution
echo "Running test ${TEST}"
rm -rf screenshots

#/usr/local/bin/mvn -Dtest=${TEST} test

rm -rf screenshots

mkdir -p screenshots

mvn clean test




# JUnit results need to be at root as "TEST-all.xml"
cp target/surefire-reports/junitreports/*.xml TEST-all.xml
# Screenshots need to be at screenshots directory in root.
cp target/surefire-reports/*.png screenshots

zip -r surefire-reports.zip target/surefire-reports/