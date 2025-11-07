import React, { useState } from 'react';
import './App.css';

function App() {
  const [checkResponse, setCheckResponse] = useState('');
  const [bookingResponse, setBookingResponse] = useState('');
  const [checkForm, setCheckForm] = useState({
    containerSize: 20,
    containerType: 'DRY',
    origin: '',
    destination: '',
    quantity: 1
  });
  const [bookingForm, setBookingForm] = useState({
    containerSize: 20,
    containerType: 'DRY',
    origin: '',
    destination: '',
    quantity: 1,
    timestamp: new Date().toISOString()
  });

  const handleCheckSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/bookings/check', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(checkForm)
      });
      const data = await response.json();
      setCheckResponse(`Available: ${data.available}`);
    } catch (error) {
      setCheckResponse('Error checking availability');
    }
  };

  const handleBookingSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/bookings', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bookingForm)
      });
      const data = await response.json();
      setBookingResponse(`Booking Ref: ${data.bookingRef}`);
    } catch (error) {
      setBookingResponse('Error creating booking');
    }
  };

  return (
    <div className="App">
      <h1>Maersk Container Booking</h1>
      
      <h2>Check Availability</h2>
      <form onSubmit={handleCheckSubmit}>
        <input type="number" placeholder="Container Size (20 or 40)" value={checkForm.containerSize} onChange={(e) => setCheckForm({...checkForm, containerSize: parseInt(e.target.value)})} required />
        <select value={checkForm.containerType} onChange={(e) => setCheckForm({...checkForm, containerType: e.target.value})}>
          <option value="DRY">DRY</option>
          <option value="REEFER">REEFER</option>
        </select>
        <input type="text" placeholder="Origin (5-20 chars)" value={checkForm.origin} onChange={(e) => setCheckForm({...checkForm, origin: e.target.value})} required />
        <input type="text" placeholder="Destination (5-20 chars)" value={checkForm.destination} onChange={(e) => setCheckForm({...checkForm, destination: e.target.value})} required />
        <input type="number" placeholder="Quantity (1-100)" value={checkForm.quantity} onChange={(e) => setCheckForm({...checkForm, quantity: parseInt(e.target.value)})} required />
        <button type="submit">Check</button>
      </form>
      <p>{checkResponse}</p>

      <h2>Create Booking</h2>
      <form onSubmit={handleBookingSubmit}>
        <input type="number" placeholder="Container Size (20 or 40)" value={bookingForm.containerSize} onChange={(e) => setBookingForm({...bookingForm, containerSize: parseInt(e.target.value)})} required />
        <select value={bookingForm.containerType} onChange={(e) => setBookingForm({...bookingForm, containerType: e.target.value})}>
          <option value="DRY">DRY</option>
          <option value="REEFER">REEFER</option>
        </select>
        <input type="text" placeholder="Origin (5-20 chars)" value={bookingForm.origin} onChange={(e) => setBookingForm({...bookingForm, origin: e.target.value})} required />
        <input type="text" placeholder="Destination (5-20 chars)" value={bookingForm.destination} onChange={(e) => setBookingForm({...bookingForm, destination: e.target.value})} required />
        <input type="number" placeholder="Quantity (1-100)" value={bookingForm.quantity} onChange={(e) => setBookingForm({...bookingForm, quantity: parseInt(e.target.value)})} required />
        <input type="text" placeholder="Timestamp (ISO-8601)" value={bookingForm.timestamp} onChange={(e) => setBookingForm({...bookingForm, timestamp: e.target.value})} required />
        <button type="submit">Book</button>
      </form>
      <p>{bookingResponse}</p>
    </div>
  );
}

export default App;