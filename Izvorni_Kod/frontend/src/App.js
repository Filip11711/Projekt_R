import './App.css';
import React from 'react';
import {MapContainer, TileLayer, Marker, Popup, useMap, useMapEvents} from "react-leaflet";
import L from 'leaflet';
import fire from './fire-icon.png'
import cloud from './cloud-icon.png'
import polar from './polar-icon.png'
import bioluminescence from './bioluminescence-icon.png'

function App() {

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
                    scrollWheelZoom={true}
                >
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    <Marker
                        position={[45, 15]}
                        icon={ fireIcon }
                    >
                    </Marker>
                    <Marker
                        position={[75, 5]}
                        icon={ cloudIcon }
                    >
                    </Marker>
                    <Marker
                        position={[30, 40]}
                        icon={ polarIcon }
                    >
                    </Marker>
                    <Marker
                        position={[55, 70]}
                        icon={ bioIcon }
                    >
                    </Marker>

                </MapContainer>
        </div>
    </div>
  );
}

export default App;
