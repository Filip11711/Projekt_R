import React, { useState } from "react";
import Button from './Button';
import dataService from "../services/dateTimeService";
import { useNavigate } from 'react-router-dom';

const Form1 = () => {
  const [dateTime, setDateTime] = useState("");

  const navigate = useNavigate();

  const handleClick = () => {
      navigate('/map/' + dateTime)
  }

  return (
    <div className='form'>
        <p>Učitaj traženi datum</p>
        <input type="datetime-local" id='datetime' onChange={(e) => {setDateTime(e.target.value)}}/>
        <div onClick={() => handleClick()}>
            <Button color="white" text="Submit"></Button>
        </div>
    </div>

  )
}

export default Form1
