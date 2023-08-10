import React from 'react'
import { Carousel, Navbar } from 'react-bootstrap';



class Home extends React.Component {
  render() {
    return (
      <div>
        <br/><br/><br/><br/>
        <h1>VASA OMILJENA AUTOBUSKA STANICA</h1>
        <br/>
      <Carousel>
        <Carousel.Item>
          <img className="d-block w-100" src="slika1.webp" alt="Slika 1" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src="slika2.jpg" alt="Slika 2" />
        </Carousel.Item>
        <Carousel.Item>
          <img className="d-block w-100" src="slika3.webp" alt="Slika 3" />
        </Carousel.Item>
      </Carousel>
      <br/>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae justo ut justo dignissim lacinia sed a libero. Nunc sodales feugiat lectus, a fringilla felis dignissim vitae. Nullam consectetur, sem sed rutrum consequat, tortor sem placerat nulla, vel aliquam leo nunc vitae felis. Curabitur condimentum erat et enim consectetur, nec scelerisque enim tempus. Aenean quis interdum nunc, euismod consequat ipsum. Sed convallis ante ac sagittis scelerisque. Suspendisse ut erat eu mauris dapibus pulvinar. Integer venenatis mi sit amet magna tristique, eget scelerisque nulla volutpat. Nam finibus, arcu nec tristique semper, neque nunc gravida neque, vel aliquet purus sem et nunc. Sed sed bibendum ipsum. Vestibulum eu massa auctor, elementum risus sit amet, ultrices nulla. Sed sed enim eget dolor tempor consectetur id eu purus. Mauris accumsan eleifend sapien ac feugiat.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae justo ut justo dignissim lacinia sed a libero. Nunc sodales feugiat lectus, a fringilla felis dignissim vitae. Nullam consectetur, sem sed rutrum consequat, tortor sem placerat nulla, vel aliquam leo nunc vitae felis. Curabitur condimentum erat et enim consectetur, nec scelerisque enim tempus. Aenean quis interdum nunc, euismod consequat ipsum. Sed convallis ante ac sagittis scelerisque. Suspendisse ut erat eu mauris dapibus pulvinar. Integer venenatis mi sit amet magna tristique, eget scelerisque nulla volutpat. Nam finibus, arcu nec tristique semper, neque nunc gravida neque, vel aliquet purus sem et nunc. Sed sed bibendum ipsum. Vestibulum eu massa auctor, elementum risus sit amet, ultrices nulla. Sed sed enim eget dolor tempor consectetur id eu purus. Mauris accumsan eleifend sapien ac feugiat.
      </p>
      </div>
    );
  }
}

export default Home;