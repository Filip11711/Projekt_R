import React, { useEffect } from 'react';
import {MapContainer, TileLayer, Marker, Popup, useMap, useMapEvents} from "react-leaflet";
import L from 'leaflet';
import fire from '../../assets/images/fire-icon.png';
import cloud from '../../assets/images/cloud-icon.png';
import polar from '../../assets/images/polar-icon.png';
import bioluminescence from '../../assets/images/bioluminescence-icon.png';
import noData from '../../assets/images/noData.png';
import { Navigate } from 'react-router-dom';
import { useState } from "react";
import dateTimeService from '../../services/dateTimeService';
import Button from '../../components/Button';
import { useNavigate } from 'react-router-dom';

const Map = () => {

  const searchParams = new URLSearchParams(document.location.search)
  const dateTime = searchParams.get('dateTime');
  const type = searchParams.get('type');
  const navigate = useNavigate();
  let data = {};
  const [error, setError] = useState(undefined);
  const [pojave, setPojave] = useState([]);

  const fetchData = async () => {
    data = await (await dateTimeService.oneDateTime(dateTime)).data;
    if (type == 1) {
      setPojave(data.naoblake);
      setIcon(cloudIcon);
    }
    if (type == 2) {
      setPojave(data.pozari);
      setIcon(fireIcon);
    }
    if (type == 3) {
      setPojave(data.polarna);
      setIcon(polarIcon);
    }
    if (type == 4) {
      setPojave(data.planktoni);
      setIcon(bioIcon);
    }
    console.log(data);
  }

  useEffect(() => {
    console.log(type, dateTime);
    fetchData();
  },[]);

  const handleClick1 = () => {
    navigate('/map?dateTime=' + dateTime + '&type=1');
    window.location.reload(true);
  }

  const handleClick2 = () => {
    navigate('/map?dateTime=' + dateTime + '&type=2');
    window.location.reload(true);
  }

  const handleClick3 = () => {
    navigate('/map?dateTime=' + dateTime + '&type=3');
    window.location.reload(true);
  }

  const handleClick4 = () => {
    navigate('/map?dateTime=' + dateTime + '&type=4');
    window.location.reload(true);
  }

  const handleClick5 = () => {
    navigate('/');
  }

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
    iconSize: [10,10],
  });
  
  let bioIcon = L.icon({
    iconUrl: bioluminescence,
    iconRetinaUrl: bioluminescence,
    iconSize: [15,15],
  });

  let noDataIcon = L.icon({
    iconUrl: noData,
    iconRetinaUrl: noData,
    iconSize: [15,15],
  });

  const [icon, setIcon] = useState(cloudIcon);
  
  return (
    <div className="App">
      <div className='form-container'>
        <div className="filter" onClick={() => handleClick1()}>
            <Button color="white" text="Naoblake"></Button>
        </div>
        <div className="filter" onClick={() => handleClick2()}>
            <Button color="white" text="Pozari"></Button>
        </div>
        <div className="filter" onClick={() => handleClick3()}>
            <Button color="white" text="Polarna svijetlost"></Button>
        </div>
        <div className="filter" onClick={() => handleClick4()}>
            <Button color="white" text="Bioluminiscentni plaktoni"></Button>
        </div>
        <div className="filter" onClick={() => handleClick5()}>
            <Button color="white" text="Povratak"></Button>
        </div>

      </div>
      <div className="map" id="map">
         <MapContainer
            style={{height: "90vh"}}
            center={[20, 0]}
            zoom={2}
            scrollWheelZoom={true}>
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
            { type == 1 ?
              (pojave ? pojave.map(pojava => (pojava.prisutnost == 1 ? <Marker position={[pojava.primaryKeyId.latitude, pojava.primaryKeyId.longitude]} icon={ icon }></Marker> : <Marker position={[pojava.primaryKeyId.latitude, pojava.primaryKeyId.longitude]} icon={ noDataIcon }></Marker>)) : null ) : null
            }
            { type == 2 ?
              (pojave ? pojave.map(pojava => (<Marker position={[pojava.primaryKeyId.latitude, pojava.primaryKeyId.longitude]} icon={ icon }></Marker>)) : null ) : null
            }
            { type == 3 ?
              (pojave ? pojave.map(pojava => (<Marker position={[pojava.primaryKey.latitude, pojava.primaryKey.longitude - 180]} icon={ icon }></Marker>)) : null ) : null
            }
            { type == 4 ?
              (pojave ? pojave.map(pojava => (<Marker position={[pojava.latitude, pojava.longitude]} icon={ icon }></Marker>)) : null ) : null
            }
          </MapContainer>
        </div>
    </div>
  )
}

export default Map
