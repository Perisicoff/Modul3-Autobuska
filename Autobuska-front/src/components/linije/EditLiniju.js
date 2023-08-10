import { Button, Col, Form, Row } from "react-bootstrap";
import Axios from "../../apis/Axios";
import { useCallback, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditLiniju = () => {

    var navigate = useNavigate()
    var params = useParams()
    var linijaId = params.id;

    const linija = {
        id: -1,
        brojMesta: 0,
        cenaKarte: 0,
        vremePolaska: '',
        destinacija: '',
        prevoznikId: -1
    }

    const [prevoznici, setPrevoznici] = useState([])
    const [editovanaLinija, setEditovanaLinija] = useState(linija)
      
    const getLinijaById = useCallback((linijaId) => {
        Axios.get('/linije/' + linijaId)
        .then(res => {
          console.log(res)
          setEditovanaLinija({id: res.data.id, brojMesta: res.data.brojMesta, cenaKarte: res.data.cenaKarte, vremePolaska: res.data.vremePolaska, destinacija: res.data.destinacija, prevoznikId: res.data.prevoznikId})
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
        getLinijaById(linijaId)
        getAllPrevoznike()
    }, [])
    
    const edit = () => {

        const dto = {
            id: editovanaLinija.id,
            brojMesta: editovanaLinija.brojMesta,
            cenaKarte: editovanaLinija.cenaKarte,
            vremePolaska: editovanaLinija.vremePolaska,
            destinacija: editovanaLinija.destinacija,
            prevoznikId: editovanaLinija.prevoznikId
        }

        Axios.put('/linije/' + editovanaLinija.id, dto)
        .then(res => {
            console.log(res)
            navigate('/linije')
          })
          .catch(err => {
            console.log(err)
          })
    }

    const naPromenaBrojMesta = (e) => {
        const value = e.target.value;
        setEditovanaLinija({...editovanaLinija, brojMesta: value})
    }

    const naPromenaCenaKarte = (e) => {
        const value = e.target.value;
        setEditovanaLinija({...editovanaLinija, cenaKarte: value})
    }

    const naPromenaVremePolaska = (e) => {
        const value = e.target.value;
        setEditovanaLinija({...editovanaLinija, vremePolaska: value})
    }

    const naPromenaDestinacija = (e) => {
        const value = e.target.value;
        setEditovanaLinija({...editovanaLinija, destinacija: value})
    }

    const naPromenaPrevoznika = (e) => {
      const value = e.target.value;
      setEditovanaLinija({...editovanaLinija, prevoznikId: value})
  }

    const renderPrevoznike = () => {
      return prevoznici.map((prevoznik) => {
        return(<option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>)
      })

    }


    return (
        <div>
             <Col>
          <br/><br/><br/><br/>
          <Row><h1>Izmeni Liniju</h1></Row>
          <br/>
          <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
              <Form>
                <Form.Label htmlFor="brojMesta">Broj mesta</Form.Label>
                <Form.Control id="brojMesta" type="number" name="brojMesta" value={editovanaLinija.brojMesta} onChange={(e) => naPromenaBrojMesta(e)}/>
                <Form.Label htmlFor="cenaKarte">Cena karte</Form.Label>
                <Form.Control id="cenaKarte" type="number" name="cenaKarte" value={editovanaLinija.cenaKarte} onChange={(e) => naPromenaCenaKarte(e)}/>
                <Form.Label htmlFor="vremePolaska">Vreme polaska</Form.Label>
                <Form.Control id="vremePolaska" type="text" name="vremePolaska" value={editovanaLinija.vremePolaska} onChange={(e) => naPromenaVremePolaska(e)}/>
                <Form.Label htmlFor="destinacija">Destinacija</Form.Label>
                <Form.Control id="destinacija" type="text" name="destinacija" value={editovanaLinija.destinacija} onChange={(e) => naPromenaDestinacija(e)}/>
                <Form.Label>Prevoznik</Form.Label>
                <Form.Select className="mb-4" name='prevoznikId' onChange={(e) => naPromenaPrevoznika(e)}>
                  <option value={""}>--izaberi prevoznika--</option>
                  {renderPrevoznike()}
                </Form.Select>
                <br/>
                <Button className="btn btn-primary" onClick={() => edit()}>Izmeni liniju</Button>
              </Form>
            </Col>
            <Col></Col>
          </Row>
        </Col>
        </div>
    );

}

export default EditLiniju;