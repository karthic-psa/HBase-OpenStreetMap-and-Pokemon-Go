# HBase-OpenStreetMap and Pokemon Go #

## Introduction: ##
"OpenStreetMap is a collaborative project to create a free editable map of the world. The creation and growth of OSM has been motivated by restrictions on use or availability of map information across much of the world, and the advent of inexpensive portable satellite navigation devices. OSM is considered a prominent example of volunteered geographic information.”
Pokemon Go is an augmented-reality game which allows gamers to catch pokemon/monsters in the real world. It also acquires map data from the real world and converts some points of interest (e.g. shopping malls and restaurants) into pokestops.


## General Steps: ##
We downloaded **openstreetmap API (xml file)** from their website according to the specific requirements of a 5-mile radius from the University of Houston (by manually selecting the map area on openstreetmap).
The we downloaded the Pokemon Go data which contains the information about pokestops and gyms at specific locations.
We created an **XML parser in java** for parsing both the xml/kml files (JAVA DOM Parser) and a java file for various queries.
We used MobaXterm to do this assignment. Upload all the required files to your MobaXterm folder.
Since there is no Hive on the whale cluster, We wrote shell scripts to **create mock hbase tables** for both the osm and pokemon go data.

If you want to compile and run just the specific java files use the commands:
1. ```javac filename.java```
2. ```java filename```

Creating XML to HBase
Just type the following in the terminal window where the files are located to start running the program.
 1. ```bash HW_mainfile.sh```

### Table Creation for OpenStreetMap data in the Format: ###

Row Name  | 'Type:Type' |  ‘ID:ID'  |  'Coordinate:Lat' |  'Coordinate:Lon'  

### Table Creation for Pokemon Go data in the Format: ###

Row Name  | 'Type:Type' |  'Name:Name'  |  'Coordinate:Lat' | 'Coordinate:Lon' | 'Coordinate:Height'

## Queries Written: ##
1.)	Finding restaurants that have Pokestops or Gyms nearby around(within 5 miles) University of Houston (Main Campus - Coordinate [[29.7199489,-95.3422334]])
2.)	Finding out which university has the largest number of Pokestops in Houston area.
3.)	The University that has the most Pokemon GO regions within 5 miles radius




