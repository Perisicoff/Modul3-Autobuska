import React, { useCallback, useEffect, useState } from 'react'
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import Axios from '../../apis/Axios';
import {IsLoggedIn} from '../../service/auth'
import '../../index.css'
import {Rola} from '../../service/auth'

const Linije = () => {

    var navigate = useNavigate()
    const [linije, setLinije] = useState([])
    const [prevoznici, setPrevoznici] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [isChecked, setIsChecked] = useState(false);
    const [searchParams, setSearchParams] = useState({
      destinacija: "",
      prevoznikId: "",
      maxCenaKarte: ""
  })

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };
      
    const getAll = useCallback((nextPage) => {
        const config = {
          params: {
            destinacija: searchParams.destinacija,
            prevoznikId: searchParams.prevoznikId,
            maxCenaKarte: searchParams.maxCenaKarte,
            pageNo: nextPage
          }
        }
        Axios.get('/linije', config)
        .then(res => {
          console.log(res)
          setLinije(res.data)
          setPageNo(nextPage)
          setTotalPage(res.headers["total-pages"])
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    const getAllPrevoznike = useCallback(() => {
      Axios.get('/prevoznici')
      .then(res => {
        console.log(res)
        setPrevoznici(res.data)
      })
      .catch(err => {
        console.log(err)
      })
  }, [])

  
    useEffect(() => {
      getAll(pageNo)
      getAllPrevoznike()
    }, [])

    const goToEdit = (linijaId) => {
      navigate('/linija/edit/' + linijaId)
    }

    const obrisiLiniju = (linijaId) => {
      const confirmDelete = window.confirm("Da li ste sigurni da zelite da obrisete ovu liniju?");
      if (confirmDelete) {
        Axios.delete('/linije/' + linijaId)
        .then(res => {
          console.log(res)
          setLinije((linije)=>linije.filter(linija => linija.id !== linijaId))
        })
        .catch(err => {
          console.log(err)
          alert("Doslo je do greske!")
        })
      } else {
        navigate('/linije')
      }
    }
  
  const rezervacija = (linijaId) => {

      Axios.put('/linije/' + linijaId + '/rezervacija')
      .then(res => {
          console.log(res)
          alert("Uspesna rezervacija, srecan put!")
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
          alert("Sve karte su rasprodate!")
        })
  }

    const rednerAll = () => {
      return linije.map((linija) => {
        return(
          <tr key={linija.id}>
            <td>{linija.nazivPrevoznik}</td>
            <td>{linija.destinacija}</td>
            <td>{linija.brojMesta}</td>
            <td>{linija.vremePolaska}</td>
            <td>{linija.cenaKarte}</td>
            {IsLoggedIn() && Rola() == "korisnik" && <td>{linija.brojMesta > 0?<Button className="btn btn-primary" onClick={() => rezervacija(linija.id)}>Rezervisi</Button>:<Button className="btn btn-secondary" disabled={linija.brojMesta == 0}>Popunjeno</Button>}</td>}
            {IsLoggedIn() && Rola() == "admin" && <td><Button className="btn btn-warning" onClick={() => goToEdit(linija.id)}>Izmeni</Button></td>}
            {IsLoggedIn() && Rola() == "admin" && <td><Button className="btn btn-danger" onClick={() => obrisiLiniju(linija.id)}>Ukloni</Button></td>}
          </tr>
        )
      })

    }

    const serchValue = (event) => {
      let name = event.target.name
      let value = event.target.value
      
      searchParams[name] = value
      setSearchParams(searchParams)
      getAll(0)
    }

    const renderPrevoznike = () => {
      return prevoznici.map((prevoznik) => {
        return(<option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>)
      })

    }

    const linija = {
      id: -1,
      brojMesta: 0,
      cenaKarte: 0,
      addDestinacija: '',
      vremePolaska: '',
      prevoznikId: -1
  }

  const [noviLinija, setNoviLinija] = useState(linija)

    const addLiniju = () => {

      const dto = {
        brojMesta: noviLinija.brojMesta,
        cenaKarte: noviLinija.cenaKarte,
        destinacija: noviLinija.addDestinacija,
        vremePolaska: noviLinija.vremePolaska,
        prevoznikId: noviLinija.prevoznikId
      }

      Axios.post('/linije', dto)
          .then(res => {
              console.log(res)
              window.location.reload()
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

    noviLinija[name] = value;
    setNoviLinija(noviLinija);
  }
    
    return (
      <div>
        {Rola() == "admin" && IsLoggedIn() && <div>
            <Col>
          <br/><br/><br/><br/>
          <Row><h1>Nova linija</h1></Row>
          <br/>
          <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
              <Form>
                <Form.Label htmlFor="brojMesta">Broj mesta</Form.Label>
                <Form.Control id="brojMesta" type="text" name="brojMesta" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="cenaKarte">Cena karte</Form.Label>
                <Form.Control id="cenaKarte" name="cenaKarte" type="number" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="addDestinacija">Destinacija</Form.Label>
                <Form.Control id="addDestinacija" type="text" name="addDestinacija" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="vremePolaska">Vreme polaska</Form.Label>
                <Form.Control id="vremePolaska" type="text" name="vremePolaska" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label>Prevoznik</Form.Label>
                <Form.Select className="mb-4" name='prevoznikId' onChange={(e) => valueInputChanged(e)}>
                  <option value={""}>--izaberi prevoznika--</option>
                  {renderPrevoznike()}
                </Form.Select>
                <br/>
                <Button className="btn btn-primary" onClick={() => addLiniju()}>Dodaj liniju</Button>
              </Form>
            </Col>
            <Col></Col>
          </Row>
        </Col>
        </div>}
        <div>
        <Row className="justify-content-center">
        <Col  xs="12" sm="10" md="8">
          <br/><br/><br/><br/>
          <Row><h1>Linije</h1></Row>
          <br/>
          <Row><Col><Form.Check type='checkbox' label="Pretraga" checked={isChecked} onChange={handleCheckboxChange}></Form.Check></Col></Row>
          {isChecked && <Row>
            <Form.Label>Destinacija</Form.Label>
            <Form.Control className="mb-4" type='text' name='destinacija' onChange={serchValue} ></Form.Control><br/>
            <Form.Label>Prevoznik</Form.Label>
            <Form.Select className="mb-4" name='prevoznikId' onChange={serchValue}>
                  <option value={""}>--izaberi prevoznika--</option>
                  {renderPrevoznike()}
              </Form.Select>
            <Form.Label>Maksimalna cena</Form.Label>
            <Form.Control className="mb-4" type='number' min={0} name='maxCenaKarte' onChange={serchValue}></Form.Control>
          </Row>}
          <Row>
            <Col>
              <Table>
                <thead>
                  <tr>
                    <th>Prevoznik</th>
                    <th>Destinacija</th>
                    <th>Broj mesta</th>
                    <th>Vreme polaska</th>
                    <th>Cena karte</th>
                    <th></th>
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
           <Button className="btn btn-dark" disabled={pageNo==totalPage-1 || linije.length === 0} onClick={() => getAll(pageNo+1)}>{'❯'}</Button>
          </div>
        </Col>
        </Row>
      </div>
      </div>
    );
}

export default Linije;