import React from 'react';
import ReactDOM from 'react-dom';
import ComponenteApp from './componente';
const mensaje = <h1>Vamos a renderizar este mensaje en nuestra web</h1>;
const divRoot = document.getElementById("root");
ReactDOM.render( <ComponenteApp/>, divRoot);