import React from "react";
import PropTypes from 'prop-types'
const ComponenteApp = (props) => {
    const misDatos = {
        nombre: "Daniel Barroso Rocío",
        edad: "23",
        estudios: "2º DAM"
      }
    const mostrarHora = ()=>{
        alert(new Date());
    }
return (
    <>
        <h1>Mis datos:</h1>
        <h4>{JSON.stringify(misDatos)}</h4>
        <p>Datos recibido en el componente: { props.num1 + props.num2}</p>
        <h4> Pulsar en el botón para ver la hora</h4>
        <button onClick={mostrarHora}>Pulsar</button>
    </>
)
}
ComponenteApp.propTypes = {
    num1: PropTypes.number.isRequired,
    num2: PropTypes.number.isRequired,
}
export default ComponenteApp;