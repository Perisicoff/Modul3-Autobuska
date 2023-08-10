import React, { useState } from 'react';
import { MDBInput, MDBCheckbox }from 'mdb-react-ui-kit';
import { Button, Container, Row } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import Axios from '../../apis/Axios';

function Registracija() {

  const korisnik = {
    id: -1,
    korisnickoIme: '',
    eMail: '',
    ime: '',
    prezime: '',
    lozinka: '',
    ponovljenaLozinka: ''
  }

  const [noviKorisnik, setNoviKorisnik] = useState(korisnik)
  var navigate = useNavigate()

  const addKorisnika = () => {

      const dto = {
        korisnickoIme: noviKorisnik.korisnickoIme,
        eMail: noviKorisnik.eMail,
        ime: noviKorisnik.ime,
        prezime: noviKorisnik.prezime,
        lozinka: noviKorisnik.lozinka,
        ponovljenaLozinka: noviKorisnik.ponovljenaLozinka
      }

  Axios.post('/korisnici', dto)
      .then(res => {
          console.log(res)
          navigate('/login')
      })
      .catch(err => {
          console.log(err)
          alert("Doslo je do greske, pokusajte novi unos!")
      })
  }

  const valueInputChanged = (e) => {

      let input = e.target;
      let name = input.name;
      let value = input.value;

      noviKorisnik[name] = value;
      setNoviKorisnik(noviKorisnik);
  }

  const handleEnterKeyPress = (event) => {
      if (event.key === 'Enter') {
          addKorisnika()
      }
  };

  return (
    <Container className="p-3 my-5 d-flex flex-column w-50">
      <br/><br/>
      <Row><h1>Create an account</h1></Row>
      <br/><br/>
        <MDBInput wrapperClass='mb-4' name='ime' label='Name' id='form1' type='text' onChange={(e) => valueInputChanged(e)}/>
        <MDBInput wrapperClass='mb-4' name='prezime' label='Last Name' id='form2' type='text' onChange={(e) => valueInputChanged(e)}/>
        <MDBInput wrapperClass='mb-4' name='eMail' label='Email' id='form1' type='email' onChange={(e) => valueInputChanged(e)}/>
        <MDBInput wrapperClass='mb-4' name='korisnickoIme' label='Username' id='form1' type='text' onChange={(e) => valueInputChanged(e)}/>
        <MDBInput wrapperClass='mb-4' name='lozinka' label='Password' id='form1' type='password' onChange={(e) => valueInputChanged(e)}/>
        <MDBInput wrapperClass='mb-4' name='ponovljenaLozinka' label='Repeat password' id='form1' type='password' onChange={(e) => valueInputChanged(e)} onKeyDown={handleEnterKeyPress}/>
      <div className='d-flex flex-row justify-content-center mb-4'>
        <MDBCheckbox name='flexCheck' id='flexCheckDefault' label='I agree all statements in Terms of service' />
      </div>
        <Button className="btn btn-dark" onClick={() => addKorisnika()}>Register</Button>
      <div className="text-center">
        <p>Have already an account? <a href="/login">Login here</a></p>
      </div>
    </Container>
  );
}

export default Registracija;