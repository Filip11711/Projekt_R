import React, { useState } from "react";
import Button from './Button';
import { useNavigate } from 'react-router-dom';

const Form1 = () => {
  const [dateTime, setDateTime] = useState("");

  const navigate = useNavigate();

  const handleClick = () => {
      if (dateTime == "") {
        alert("Odaberite datum i vrijeme!!!");
      } else {
        navigate('/map?dateTime=' + dateTime + '&type=1');
      }
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
