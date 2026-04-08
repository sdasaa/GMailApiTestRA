# Purpose:
#   1) Copy the following resources of the project to the image
#       a) Compile & Package the latest code, resources and cnResources with maven
#       b) Copy the above mentioned artifacts into target/dockerResources
#       c) build the image:
#           c.1) docker build -t <User/ImageName/tagName> <buildContext>
#           c.2) BuildContext (That . at the end of docker build cmd, is more like cp of java)
#                   * Imagine buildContext as the classpath.
#                   * All paths mentioned in Dockerfile should be wrt buildContext and not wrt to Dockerfile location
#                   * (Ex:) Structure: projectRoot/dir1/Dockerfile & Dir to copy to image pRoot/dir2/dir3
#                   * BuildConext : <pRoot> | Dockerfile -> <pRoot>/dir2/dir | Build : -t <> -f ./dir1/Dockerfile .
#                   * Above . represent the buildContext and it cooresponds to pRoot from where we invoke docker build cmd
#           c.1) docker build -t sdasa/restatestng:latest -f CNResources/ .
#   2) Copy the target/dockerResources to the image
#   3) Run the tests using testRunner.sh script
#
###########   Build Cmd -> docker build -t sdasa/restatestng -f ./CNresources/Dockerfile . --no-cache #########

FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /home/das/restAssuredTestNG

COPY ./target/dockerResources .

ENTRYPOINT ["sh" , "testRunnerSh.sh"]