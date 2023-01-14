import React from 'react';
import { BrowserRouter, Route, Routes} from "react-router-dom"
import HomePage from './screens/HomePage';
import Map from "./screens/Map"

export const api_url = "http://localhost:5000/api/";

function App() {
  return (
    <BrowserRouter>
    <div>
      <Routes>
        <Route path='/' element={<HomePage></HomePage>}></Route>
        <Route path='/map/:dateTime' element={<Map></Map>}></Route>
      </Routes>
    </div>
    </BrowserRouter>

  );
}

export default App;
