#!/bin/bash

# Start VM
# vagrant up

#Lade Docker images (aus shared folder)

vagrant ssh -c 'docker load -i parcelsize-service'
vagrant ssh -c 'docker load -i parcelwebserver'

#Netzwerk einrichten

vagrent ssh 'docker newtork create ParcelNet'

# Starten Docker Container
vagrent ssh 'docker run --name webserver --net ParcelNet -p 8888:8080 -d percelwebserver'
vagrent ssh 'docker run --name parcelservice --net ParcelNet -p 1100:1100 -d parcelsize-service'
vagrent ssh 'docker run --name MySQLParcelsize --net ParcelNet -p 3306:3306 -d MYSQL_ROOT_PASSWORD=mysqlroot --mount type=bind,src=/vagrant/scripts/,dst=/docker-entrypoint-initdb.d/ mysql:5.7.22'

