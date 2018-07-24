#!/bin/bash

# Start VM
vagrant up

#Speichern des Docker Images des MS
docker save -o parcelsize.tar parcelsize

#Hole Dateien vom Asset Server
wget http://192.168.56.103/images/parcelwebserver.tar
wget http://192.168.56.103/images/mysql.tar

#Lade Docker images (aus shared folder)
vagrant ssh -c 'docker load -i /vagrant/parcelsize.tar'
vagrant ssh -c 'docker load -i /vagrant/parcelwebserver.tar'
vagrant ssh -c 'docker load -i /vagrant/mysql.tar'

#Netzwerk einrichten

vagrant ssh -c 'docker network create ParcelNet'

# Starten Docker Container
# DB vor den anderen!!!
vagrant ssh -c 'docker run --name MySQLParcelsize --net ParcelNet -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=mysqlroot --mount type=bind,src=/vagrant/scripts/,dst=/docker-entrypoint-initdb.d/ mysql:5.7.22'
sleep 7s

vagrant ssh -c 'docker run --name webserver --net ParcelNet -p 8888:8080 -d parcelwebserver'
vagrant ssh -c 'docker run --name parcelservice --net ParcelNet -p 1100:1100 -d parcelsize'


