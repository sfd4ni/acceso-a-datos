import axios from 'axios';
import React from 'react';
import { matchPath, Location, useParams, useNavigate } from 'react-router-dom';
import { Operador } from './Modelo/operador';
interface IState {
  operadorPost: Operador
}
export const Login = () => {
const ip = "localhost";
const puerto = 8080;
const rutaBase = "http://" + ip + ":" + puerto + "/api/login";
const [statePost, setStatePost] = React.useState<IState>({operadorPost: new Operador("", "")});
const nickoperador = React.useRef<HTMLInputElement>(null);
const pwdoperador = React.useRef<HTMLInputElement>(null);
const navigate = useNavigate();


const postOperadorEffect = React.useEffect(() => {
    const postOperador = async (operador: Operador) =>{
      if (operador.nick !== "" && operador.password !== "") {
        await axios.post(rutaBase, operador)
          .then(function (response) {
            localStorage.clear();
            localStorage.setItem("token",response.data)
            navigate('/clientes');
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      }
    postOperador(statePost.operadorPost);
}, [statePost]);

const postOperador = (event: React.MouseEvent<HTMLButtonElement>) =>  {
    event.preventDefault();
    console.log("Algo pasa");
    const nickOperador = nickoperador.current?.value;
    const pwdOperador = pwdoperador.current?.value;
    if (typeof nickOperador === "string" && typeof pwdOperador === "string") {
        let operador = new Operador(nickOperador, pwdOperador);
        setStatePost({operadorPost: operador});
    }
}
return (
    <>
        <h3>Inicie sesión operador</h3>
        <div>
          <span>Introduzca sus credenciales:</span><br/>
          <span>Nick: </span><input type="text" ref={nickoperador}></input><br/>
          <span>Password: </span><input type="text" ref={pwdoperador}></input><br/>
          <button onClick={postOperador}>Enviar</button>
        </div>
    </>
);
}