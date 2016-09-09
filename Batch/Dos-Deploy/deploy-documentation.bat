@Echo Off


set targetDirectory=D:\target\
set targetWebServer=%targetDirectory%web-server\
set targetWebContent=%targetDirectory%web-content\

set executableFile=%targetWebServer%startWebServer.bat
set configurationFile=%targetWebServer%CustomWebServer.properties



rmdir /S /Q %targetDirectory%

mkdir %targetDirectory%
mkdir %targetWebServer%
mkdir %targetWebContent%


echo socket.port=8000
echo socket.backlog=0
echo socket.shutdown-time=10
echo content.content-types=html,htm,gif,js,css,png,svg

echo content.base-directory=%targetWebContent%
set "targetWebContent=%targetWebContent:\=//%"
echo content.base-directory=%targetWebContent%


echo.>> %configurationFile%
echo "socket.port=8000">> %configurationFile%
echo "socket.backlog=0">> %configurationFile%
echo "socket.shutdown-time=10">> %configurationFile%
echo "content.base-directory=%targetWebContent%">> %configurationFile%
echo "content.content-types=html,htm,gif,js,css,png,svg">> %configurationFile%
exit /b 0


rem call ant.bat -verbose -buildfile ..\Ant-Deploy\deploy-webserver.xml deploy
call ant.bat -buildfile ..\Ant-Deploy\deploy-webserver.xml deploy
xcopy ..\..\tmp\web-server %targetWebServer% /E /Y

call ant.bat -buildfile ..\Ant-Execute\run-instrumented-tests.xml coverage
xcopy ..\..\tmp\web-content %targetWebContent% /E /Y


echo socket.port^=8000>%configurationFile%
echo socket.backlog^=0>>%configurationFile%
echo socket.shutdown-time^=10>>%configurationFile%
echo content.base-directory^=%targetWebContent%>>%configurationFile%
echo content.content-types^=html,htm,gif,js,css,png,svg>>%configurationFile%


start %executableFile%


set executableFile=
set configurationFile=

set targetDirectory=
set targetWebServer=
set targetWebContent=
