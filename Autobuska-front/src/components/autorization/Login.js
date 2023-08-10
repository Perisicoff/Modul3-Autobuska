import React, { useState } from 'react';
import { MDBInput, MDBCheckbox } from 'mdb-react-ui-kit';
import { Button, Container, Row } from 'react-bootstrap';
import { login } from '../../service/auth'

function Login() {

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const handleEnterKeyPress = (event) => {
    if (event.key === 'Enter') {
       login(username, password)
    }
  };

  return (
    <Container className="p-3 my-5 d-flex flex-column w-50">
        <br/><br/>
        <Row><h1>Login</h1></Row>
        <br/><br/>
          <MDBInput wrapperClass='mb-4' label='Username' id='form1' type='text' onChange={(e) => setUsername(e.target.value)}/>
          <MDBInput wrapperClass='mb-4' label='Password' id='form2' type='password' onChange={(e) => setPassword(e.target.value)} onKeyDown={handleEnterKeyPress}/>

        <div className="d-flex justify-content-between mx-3 mb-4">
          <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
          <a href="/login">Forgot password?</a>
        </div>

        <Button className="btn btn-dark" onClick={() => login(username, password)}>Sign in</Button>

        <div className="text-center">
          <p>Not a member? <a href="/registracija">Register</a></p>
        </div>
    </Container>
  );
}

export default Login;