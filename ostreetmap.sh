#!/bin/bash
fname=$1
echo "disable 'Streetmap_Table_K'" | hbase shell
echo "drop 'Streetmap_Table_K'" | hbase shell
echo "create 'Streetmap_Table_K', 'Type', 'ID', 'Coordinate'" | hbase shell
cat $1 | hbase shell
echo "scan 'Streetmap_Table_K', {LIMIT => 10}" | hbase shell

