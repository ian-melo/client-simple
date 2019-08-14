#!/bin/bash
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
NAME="client-simple"

case "$1" in 
start)
   $DIR/$NAME.sh &
   echo $!>$DIR/$NAME.pid
   ;;
stop)
   kill `cat $DIR/$NAME.pid`
   rm $DIR/$NAME.pid
   ;;
restart)
   $0 stop
   $0 start
   ;;
status)
   if [ -e $DIR/$NAME.pid ]; then
      echo $NAME.sh is running, pid=`cat $DIR/$NAME.pid`
   else
      echo $NAME.sh is NOT running
      exit 1
   fi
   ;;
*)
   echo "Usage: $0 {start|stop|status|restart}"
esac

exit 0 