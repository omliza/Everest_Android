#!/bin/bash

## This is the ANDROID version of run-tests.sh
## To use in cloud:
## 		Rename this file to "run-tests.sh"
##		Make sure that testdroid.properties has Android configuration in place
## To run locally:
##		Make sure that testdroid.properties has Android configuration in place
##		run "mvn -Dtest=TestName test"

export LD_LIBRARY_PATH=/opt/OpenCV/opencv-2.4.9/build/lib

# Name of the desired test script file without extension
TEST=${TEST:="BitbarSampleTest"}

# SHORT_NAME should be the same as package name of your test script.
# This is used to relocate JUnit xml file and screenshots at the end of this script
if [[ $TEST == *"BitbarSampleTest"* ]] 
then
	SHORT_NAME="sample"
fi

## Cloud setup
echo "Starting Appium ..."

/opt/appium/appium/bin/appium.js --command-timeout 120 >appium.log 2>&1 &

# Wait for appium to fully launch
sleep 10

ps -ef|grep appium

echo "Extracting tests.zip..."
unzip tests.zip

echo "Adding correct automationName to testdroid.properties..."
sed -i_bak -e '/automationName/d' testdroid.properties

APILEVEL=$(adb shell getprop ro.build.version.sdk)
APILEVEL="${APILEVEL//[$'\t\r\n']}"
echo "API level is: ${APILEVEL}"

if [ "$APILEVEL" -gt "16" ]; then
	echo "appium.automationName=android" >> testdroid.properties
else
	echo "appium.automationName=selendroid" >> testdroid.properties
fi

## Start test execution
echo "Running test ${TEST}"
rm -rf screenshots

mvn -o -Dtest=${TEST} test

# JUnit results need to be at root as "TEST-all.xml"
mv target/reports/local/TEST-${SHORT_NAME}.${TEST}.xml TEST-all.xml
# Screenshots need to be at screenshots directory in root.
mv target/reports/local/screenshots/${SHORT_NAME} screenshots