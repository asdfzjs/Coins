#!/bin/bash
JARPath=./coins-0.0.1-SNAPSHOT.jar
APP_NAME=coins_project
APPLICATION_FILE=/opt/scpip_monitor/application.properties
PID=$(ps aux | grep coins-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' )

function check_if_process_is_running {
 if [ "$PID" = "" ]; then
   return 1
 fi
 ps -p $PID | grep "java"
 return $?
}


case "$1" in
  status)
    if check_if_process_is_running
    then
      echo -e " $APP_NAME is running "
    else
      echo -e " $APP_NAME not running "
    fi
    ;;
  stop)
    if ! check_if_process_is_running
    then
      echo  -e " $APP_NAME  already stopped "
      exit 0
    fi
    kill -9 $PID
    echo -e " Waiting for process to stop "
    NOT_KILLED=1
    for i in {1..20}; do
      if check_if_process_is_running
      then
        echo -ne " . "
        sleep 1
      else
        NOT_KILLED=0
      fi
    done
    echo
    if [ $NOT_KILLED = 1 ]
    then
      echo -e " Cannot kill process "
      exit 1
    fi
    echo  -e " $APP_NAME already stopped "
    ;;
  start)
    if [ "$PID" != "" ] && check_if_process_is_running
    then
      echo -e " $APP_NAME already running "
      exit 1
    fi
   nohup java -jar $JARPath >/dev/null 2>&1 &
   echo -ne " Starting "
    for i in {1..20}; do
        echo -ne "."
        sleep 1
    done
    if check_if_process_is_running
     then
       echo  -e " $APP_NAME fail "
    else
       echo  -e " $APP_NAME started "
    fi
    ;;
  restart)
    $0 stop
    if [ $? = 1 ]
    then
      exit 1
    fi
    $0 start
    ;;
  *)
    echo "Usage: $0 {start|stop|restart|status}"
    exit 1
esac

exit 0