import React from "react";
const Tabla = (props) => {
    const [contador, incrementar] = React.useState(0);
    const {tabla} = props;
    const multiplicarResultado = ()=>{
        if (contador >= 10) {
            incrementar(1);
        }else{
            incrementar(contador + 1);
        }
        
    }

return (
    <>
        <p> {tabla} * {contador} = {tabla * contador}</p>
        <button onClick={multiplicarResultado }>
        {tabla} * {contador}
        </button>
    </>
);
}
export default Tabla;