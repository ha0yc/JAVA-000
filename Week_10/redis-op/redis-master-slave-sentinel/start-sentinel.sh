redis-server &
redis-server redis-slave.conf &
redis-server redis-slave1.conf &

redis-senetinel sentinel.conf
redis-sentinel sentinel1.conf
