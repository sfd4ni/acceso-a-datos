import React from 'react';
import { Persona } from './Persona';

interface IProps {
    modificarstatepadre: Function;
    statepadre: Persona;
}
interface IState { persona: Persona }

export class PersonaCard extends React.Component<IProps, IState> {
    modificarstatepadre: Function;
  constructor(props: IProps)  {
    super(props);
    
    const { modificarstatepadre, statepadre } = props;
    this.modificarstatepadre = modificarstatepadre;
    this.state = { persona: statepadre };
    console.log(statepadre);
    
    
    //modificarstatepadre(this.state.persona);
    this.modificarPersona = this.modificarPersona.bind(this);
    
    }
    modificarPersona(event: React.ChangeEvent<HTMLInputElement>) {
      event.preventDefault();
      if (event.currentTarget.id === "nombre") {
        this.state.persona.nombre = event.currentTarget.value;
      } else if (event.currentTarget.id === "apellido") {
        this.state.persona.apellido = event.currentTarget.value;
      } else if (event.currentTarget.id === "altura") {
        this.state.persona.altura = parseFloat(event.currentTarget.value);
      } else if (event.currentTarget.id === "edad") {
        this.state.persona.edad = parseInt(event.currentTarget.value, 10);
      } else {
        this.state.persona.peso = parseFloat(event.currentTarget.value);
      }
      this.modificarstatepadre(this.state.persona);
    }
    render() {
        return (
            <div>
              <h4>id: {this.state.persona.id}</h4>
              <span>nombre: </span><input id="nombre" type="text" defaultValue={this.state.persona.nombre} onBlur={this.modificarPersona}></input><br/>
              <span>apellido: </span><input id="apellido" type="text" defaultValue={this.state.persona.apellido} onBlur={this.modificarPersona}></input><br/>
              <span>altura: </span><input id="altura" type="text" defaultValue={this.state.persona.altura > 0 ? this.state.persona.altura : ""} onBlur={this.modificarPersona}></input><br/>
              <span>edad: </span><input id="edad" type="text" defaultValue={this.state.persona.edad > 0 ? this.state.persona.edad : ""} onBlur={this.modificarPersona}></input><br/>
              <span>peso: </span><input id="peso" type="text" defaultValue={this.state.persona.peso > 0 ? this.state.persona.peso : ""} onBlur={this.modificarPersona}></input><br/>
              <span>imc: {this.state.persona.getIMC()}</span>
            </div>
          )
    }
}