import React from "react";
const Tabla = (props) => {
    const multiplicarResultado = ()=>{
        //contador %= 10;
        incrementar(contador + 1);
    }
const [contador, incrementar] = React.useState(0);
return (
    <>
        <p>2 * {contador} = {2 * contador}</p>
        <button onClick={multiplicarResultado }>
            2 * {contador}
        </button>
    </>
);
}
export default Tabla;