import React, { useCallback, useEffect, useState } from 'react'
import { Button, Col, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import Axios from '../../apis/Axios';
import {IsLoggedIn} from '../../service/auth'

const Korisnici = () => {

  var navigate = useNavigate()
  const [korisnici, setKorisnici] = useState([])
  const [pageNo, setPageNo] = useState(0)
  const [totalPage, setTotalPage] = useState(0)
    
  const getAll = useCallback((nextPage) => {
    const config = {
      params: {
        pageNo: nextPage
      }
    }
      Axios.get('/korisnici', config)
      .then(res => {
        console.log(res)
        setKorisnici(res.data)
        setPageNo(nextPage)
        setTotalPage(res.headers["total-pages"])
      })
      .catch(err => {
        console.log(err)
      })
  }, [])

  useEffect(() => {
    getAll(pageNo)
  }, [])

  const obrisiKorisnika = (korisnikId) => {
    const confirmDelete = window.confirm("Da li ste sigurni da zelite da obrisete ovog korisnika?");
    if (confirmDelete) {
      Axios.delete('/korisnici/' + korisnikId)
      .then(res => {
        console.log(res)
        setKorisnici((korisnici)=>korisnici.filter(korisnik => korisnik.id !== korisnikId))
      })
      .catch(err => {
        console.log(err)
        alert("Greska, pokusajte ponovo!")
      })
    } else {
      navigate('/korisnici')
    }
  }

  const rednerAll = () => {
    return korisnici.map((korisnik) => {
      return(
        <tr key={korisnik.id}>
          <td>{korisnik.ime}</td>
          <td>{korisnik.prezime}</td>
          <td>{korisnik.eMail}</td>
          <td>{korisnik.korisnickoIme}</td>
          {IsLoggedIn() && <td><Button className="btn btn-danger" onClick={() => obrisiKorisnika(korisnik.id)}>Ukloni</Button></td>}
        </tr>
      )
    })

  }
  
  return (
    <div>
      <Row className="justify-content-center">
      <Col  xs="12" sm="10" md="8">
        <br/><br/><br/><br/>
        <Row><h1>Korisnici</h1></Row>
        <br/>
        <Row>
          <Col>
            <Table>
              <thead>
                <tr>
                  <th>Ime</th>
                  <th>Prezime</th>
                  <th>Email</th>
                  <th>Username</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                {rednerAll()}
              </tbody>
            </Table>
          </Col>
        </Row>
        <br/>
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <Button className="btn btn-dark" disabled={pageNo==0} onClick={() => getAll(pageNo-1)}>{'❮'}</Button>
          <span style={{ margin: '10px' }}> {pageNo + 1} </span> 
          <Button className="btn btn-dark" disabled={pageNo==totalPage-1 || korisnici.length === 0} onClick={() => getAll(pageNo+1)}>{'❯'}</Button>
        </div>
      </Col>
      </Row>
    </div>
  );
}

export default Korisnici;