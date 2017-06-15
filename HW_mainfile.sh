#!/bin/bash
echo "# Jar File Creation #"
#$javac data_parser.java
echo "# XML Parsing #"
#$java data_parser $1 $2
echo "# Tables #"
echo "Doing PKMN Go table"
bash pokemn.sh "poke_parse.txt"
echo "Doing OSM data table"
bash ostreetmap.sh "streetmap_parse.txt"
echo "Finish tables"
echo "# Different parts required #"
echo "# Done #"



