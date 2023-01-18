import React from 'react';
import { BrowserRouter, Route, Routes} from "react-router-dom"
import HomePage from './screens/HomePage';
import Map from "./screens/Map"

export const api_url = "https://projekt-r-backend.onrender.com/api/";

function App() {
  return (
    <BrowserRouter>
    <div>
      <Routes>
        <Route path='/' element={<HomePage></HomePage>}></Route>
        <Route path='/map' element={<Map></Map>}></Route>
      </Routes>
    </div>
    </BrowserRouter>

  );
}

export default App;
