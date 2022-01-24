import React from "react";
const FCContador = (props) => {
const [contador, incrementar] = React.useState(0);
return (
    <>
        <p>Has hecho click {contador} veces</p>
        <button onClick={() => incrementar( contador + 1) }>
            Haz click!
        </button>
    </>
);
}
export default FCContador;