import React, { useEffect } from 'react';
import {MapContainer, TileLayer, Marker, Popup, useMap, useMapEvents} from "react-leaflet";
import L from 'leaflet';
import fire from '../../assets/images/fire-icon.png';
import cloud from '../../assets/images/cloud-icon.png';
import polar from '../../assets/images/polar-icon.png';
import bioluminescence from '../../assets/images/bioluminescence-icon.png';
import { useParams } from 'react-router-dom';
import { useState } from "react";
import dateTimeService from '../../services/dateTimeService';

const Map = () => {

  const { dateTime } = useParams();
  let data = {}
  const [error, setError] = useState(undefined);
  const [naoblake, setNaoblake] = useState();

  const fetchData = async () => {
    data = await (await dateTimeService.oneDateTime(dateTime)).data;
    setNaoblake(data.naoblake);
    console.log(data);
  }

  useEffect(() => {
    fetchData();
  },[]);

  let fireIcon = L.icon({
    iconUrl: fire,
    iconRetinaUrl: fire,
    iconSize: [15,15],
  });
  
  let cloudIcon = L.icon({
    iconUrl: cloud,
    iconRetinaUrl: cloud,
    iconSize: [15,15],
  });
  
  let polarIcon = L.icon({
    iconUrl: polar,
    iconRetinaUrl: polar,
    iconSize: [15,15],
  });
  
  let bioIcon = L.icon({
    iconUrl: bioluminescence,
    iconRetinaUrl: bioluminescence,
    iconSize: [15,15],
  });
  
  return (
    <div className="App">
      <div className="map" id="map">
         <MapContainer
            style={{height: "90vh"}}
            center={[20, 0]}
            zoom={2}
            scrollWheelZoom={true}>
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
            {/*naoblake ? naoblake.map(naoblaka => (<Marker position={[naoblaka.primaryKeyId.latitude, naoblaka.primaryKeyId.longitude]} icon={ cloudIcon }></Marker>)) : null */}
            <Marker position={[45, 15]} icon={ fireIcon }></Marker>
            <Marker position={[30, 40]} icon={ polarIcon }></Marker>
            <Marker position={[55, 70]} icon={ bioIcon }></Marker>
          </MapContainer>
        </div>
    </div>
  )
}

export default Map
