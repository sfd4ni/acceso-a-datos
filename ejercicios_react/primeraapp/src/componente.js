import React from "react";
const ComponenteApp = () => {
    const misDatos = {
        nombre: "Daniel Barroso Rocío",
        edad: "23",
        estudios: "2º DAM"
      }
return (
    <>
        <h1>Mis datos:</h1>
        <h4>{JSON.stringify(misDatos)}</h4>
    </>
)
}
export default ComponenteApp;