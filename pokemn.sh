#!/bin/bash
fname=$1
echo "disable 'Pokes_K'" | hbase shell
echo "drop 'Pokes_K'" | hbase shell
echo "create 'Pokes_K', 'Type', 'Name', 'Coordinate'" | hbase shell
cat $1 | hbase shell
echo "scan 'Pokes_K', {LIMIT => 10}" | hbase shell

