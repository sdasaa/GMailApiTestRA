#!/bin/bash

#   Naming convention: The reason is suffixed testRunner'Sh'.sh is that arrays() is not present in normal shell
#   and only present in bash shell, to run testRunner'Bash'.sh you need bash shell as base image
#
#   Purpose:
#   1. Run the Test suite without maven and using java
#     1.a) java -DdynamicVariables -cp "libs/*" org.testng.TestNG testng.xml
#       (ex) java -DREFERSH_TOKEN=12345 -cp "libs/*" org.testng.TestNG RegressionTests.xml
#     1.b) Package the java & test.jars and place with along with all the dependencies in libs dir
#     1.c) Copy libs, java & test/resources, testRunner script inside target/dockerResources dir
#     1.d) Build a docker image and copy this dockerResources dir.
#   2. Can pass dynamic variables to this script through docker compose using env file
#     2.a) Get Test run parameters like RefreshToken, testSuiteName, isParalell, threadCount
#     2.b) If none of the values are passed, it will take up default values
#   3. Usage : testRunnerBash.sh <RefreshToken> <testSuiteName> <isParalell> <threadCount>

# Building the final java command as an array
ENV_ARGS=
THREAD_COUNT_OVERRIDE="-threadcount"
TESTNG_TEST_OUTPUT="./test-output"
ALLURE_RESULTS="./allure-results"
RESULTS_ARCHIVE="./resultsArchive"

# Env variables being passed
REFRESH_TOKEN=${REFRESH_TOKEN}
THREAD_COUNT=${THREAD_COUNT}
TEST_SUITE=${TEST_SUITE:-smoke}
TEST_SUITE_XML="./testSuites/${TEST_SUITE}Tests.xml"

echo " ================================================================================ "
echo " The following env variables have been passed via docker-compose.yaml file "
echo " 			TEST_SUITE        :   ${TEST_SUITE}				          "
echo " 			REFRESH_TOKEN		  :   ${REFRESH_TOKEN}			        "
echo " 			CLIENT_ID		      :   ${CLIENT_ID}			            "
echo " 			CLIENT_SECRET		  :   ${CLIENT_SECRET}			        "
echo " 			USER_ID		        :   ${USER_ID}			              "
echo " 			THREAD_COUNT		  :   ${THREAD_COUNT}               "
echo " ================================================================================ "

if [ -n "$TEST_SUITE" ]; then
    ENV_ARGS="-DTEST_SUITE=$TEST_SUITE"
fi

if [ -n "$REFRESH_TOKEN" ]; then
    ENV_ARGS="$ENV_ARGS -DREFRESH_TOKEN=$REFRESH_TOKEN"
fi

if [ -n "$CLIENT_ID" ]; then
    ENV_ARGS="$ENV_ARGS -DCLIENT_ID=$CLIENT_ID"
fi

if [ -n "$CLIENT_SECRET" ]; then
    ENV_ARGS="$ENV_ARGS -DCLIENT_SECRET=$CLIENT_SECRET"
fi

if [ -n "$USER_ID" ]; then
    ENV_ARGS="$ENV_ARGS -DUSER_ID=$USER_ID"
fi

if [ -n "$THREAD_COUNT" ]; then
    THREAD_COUNT_OVERRIDE="$THREAD_COUNT_OVERRIDE $THREAD_COUNT"
fi

echo " =============================== Test Start ===================================== "

echo "Environment Variables passed: ${ENV_ARGS}"
echo "Running command:"
echo "java -Djava.util.logging.ConsoleHandler.level=ALL -Djansi.force=true $ENV_ARGS -cp './libs/*' org.testng.TestNG $THREAD_COUNT_OVERRIDE $TEST_SUITE_XML 2>&1 | cat"

java "-Djava.util.logging.ConsoleHandler.level=ALL" "-Djansi.force=true" $ENV_ARGS -cp './libs/*' org.testng.TestNG $THREAD_COUNT_OVERRIDE "$TEST_SUITE_XML" 2>&1 | cat
EXIT_CODE=$?

echo " =========================== Archiving Results ================================== "

if [ ! -d "${RESULTS_ARCHIVE}" ]; then
    mkdir "${RESULTS_ARCHIVE}"
fi

if [ -d "${TESTNG_TEST_OUTPUT}" ] && [ -d "${ALLURE_RESULTS}" ]; then
  cp -rv "${TESTNG_TEST_OUTPUT}" "${ALLURE_RESULTS}" "${RESULTS_ARCHIVE}"
fi

echo " =============================== Test End ====================================== "

exit $EXIT_CODE