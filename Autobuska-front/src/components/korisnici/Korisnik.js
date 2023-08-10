import React, { useCallback, useEffect, useState } from 'react';
import { Button, Container, Form, Row } from 'react-bootstrap';
import Axios from '../../apis/Axios';
import { logout } from '../../service/auth';
import { useNavigate } from 'react-router-dom';

function Korisnik() {

  const profil = {
    id: -1,
    korisnickoIme: '',
    eMail: '',
    ime: '',
    prezime: ''
  }
  var navigate = useNavigate()
  const [korisnik, setKorisnik] = useState(profil)
  var korisnickoIme = window.localStorage['sub']
  const [oldPassword, setOldPassword] = useState("")
  const [password, setPassword] = useState("")
  const [repitedPassword, setRepitedPassword] = useState("")
  
  const getKorisnik = useCallback((korisnickoIme) => {
    Axios.get('/korisnici/profil/' + korisnickoIme)
    .then(res => {
      console.log(res)
      setKorisnik({id: res.data.id, korisnickoIme: res.data.korisnickoIme, eMail: res.data.eMail, ime: res.data.ime, prezime: res.data.prezime})
    })
    .catch(err => {
      console.log(err)
      alert("Dogodila se greska!")
    })
}, [])

useEffect(() => {
    getKorisnik(korisnickoIme)
}, [])

const edit = () => {

    const dto = {
        id: korisnik.id,
        korisnickoIme: korisnik.korisnickoIme,
        eMail: korisnik.eMail,
        ime: korisnik.ime,
        prezime: korisnik.prezime
    }
    const confirmDelete = window.confirm( "Potvrdite izmenjene podatke koji ce biti sacuvani!\n\n" +
                                          "Ime: " + korisnik.ime + "\n" +
                                          "Prezime: " + korisnik.prezime + "\n" +
                                          "E mail: " + korisnik.eMail + "\n" +
                                          "Korisnicko ime: " + korisnik.korisnickoIme
                                          );
    if (confirmDelete) {
      Axios.put('/korisnici/' + korisnik.id, dto)
      .then(res => {
        console.log(res)
        {logout()}
      })
      .catch(err => {
        console.log(err)
      })
    } else {
      navigate('/korisnik')
    }
}

const izmenaLozinke = () => {

    const dto = {
        korisnickoIme: korisnik.korisnickoIme,
        staraLozinka: oldPassword,
        lozinka: password,
        ponovljenaLozinka: repitedPassword
    }

    Axios.put('/korisnici/'+ korisnik.id +'?promenaLozinke', dto)
    .then(res => {
        console.log(res)
        {logout()}
      })
      .catch(err => {
        console.log(err)
        alert("Greska, pokusajte ponovo!")
      })
}

const promenaImena = (e) => {
    const value = e.target.value;
    setKorisnik({...korisnik, ime: value})
}

const promenaPrezimena = (e) => {
    const value = e.target.value;
    setKorisnik({...korisnik, prezime: value})
}

const promenaEmaila = (e) => {
    const value = e.target.value;
    setKorisnik({...korisnik, eMail: value})
}

const promenaKorisnickogImena = (e) => {
    const value = e.target.value;
    setKorisnik({...korisnik, korisnickoIme: value})
}

const handleEnterKeyPressPodaci = (event) => {
  if (event.key === 'Enter') {
        edit()
  }
};

const handleEnterKeyPressLozinka = (event) => {
    if (event.key === 'Enter') {
        izmenaLozinke()
    }
};
  
  return (
    <Container className="p-3 my-5 d-flex flex-column w-50">
      <br/><br/>
      <Row><h1>{korisnik.ime} {korisnik.prezime}</h1></Row>
      <br/><br/>
    <Form>
        <Form.Label htmlFor="ime">Ime</Form.Label>
        <Form.Control id="ime" type="text" name="ime" value={korisnik.ime} onChange={(e) => promenaImena(e)}/>

        <Form.Label htmlFor="prezime">Prezime</Form.Label>
        <Form.Control id="prezime" type="text" name="prezime" value={korisnik.prezime} onChange={(e) => promenaPrezimena(e)}/>

        <Form.Label htmlFor="eMail">Email</Form.Label>
        <Form.Control id="eMail" type="email" name="eMail" value={korisnik.eMail} onChange={(e) => promenaEmaila(e)}/>

        <Form.Label htmlFor="korisnickoIme">Korisnicko ime</Form.Label>
        <Form.Control id="korisnickoIme" type="text" name="korisnickoIme" value={korisnik.korisnickoIme} onChange={(e) => promenaKorisnickogImena(e)} onKeyDown={handleEnterKeyPressPodaci}/>
        <br/>
        <Row><Button className="btn btn-dark" onClick={() => edit()}>Izmeni podatke</Button><p align="center">Priliko izmene podataka bicete automatski izlogovani</p></Row>
        
        <Form.Label htmlFor="staraLozinka">Stara Lozinka</Form.Label>
        <Form.Control id="staraLozinka" type="password" name="staraLozinka" onChange={(e) => setOldPassword(e.target.value)}/>

        <Form.Label htmlFor="lozinka">Lozinka</Form.Label>
        <Form.Control id="lozinka" type="password" name="lozinka"  onChange={(e) => setPassword(e.target.value)}/>

        <Form.Label htmlFor="ponovljenaLozinka">Ponovi lozinku</Form.Label>
        <Form.Control id="ponovljenaLozinka" type="password" name="ponovljenaLozinka"  onChange={(e) => setRepitedPassword(e.target.value)} onKeyDown={handleEnterKeyPressLozinka}/>
        <br/>
        <Row><Button className="btn btn-dark" onClick={() => izmenaLozinke()}>Izmeni lozinku</Button><p align="center">Priliko izmene lozinke bicete automatski izlogovani</p></Row>
    </Form>
    </Container>
  );
}

export default Korisnik;