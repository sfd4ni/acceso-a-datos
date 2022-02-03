import React from 'react';
import { Persona } from './Persona';
import { PersonaCard } from './PersonaCard';
interface IProps { }
interface IState { arrayPersonas: Array<Persona>, idcount: number }

export class PersonasRaiz extends React.Component<IProps, IState> {
    state: any;
    constructor(props: IProps) {
        super(props);
        this.state = {
            arrayPersonas: new Array<Persona>(),
            idcount: 0
        }
        this.modificarState = this.modificarState.bind(this);
        this.llamarFuncion = this.llamarFuncion.bind(this);
    }
    modificarState(persona: Persona) {

        let newarray = [...this.state.arrayPersonas];
        let index = newarray.findIndex((el) => el.id = persona.id);
        newarray[index] = persona;
        this.setState({ arrayPersonas: newarray });
    }

    añadirPersona() {
        let arrayPers = this.state.arrayPersonas;
        let i = this.state.idcount;
        arrayPers.push(new Persona(i, "",  "", 0, 0, 0));
        this.setState({ arrayPersonas: arrayPers, idcount: i + 1 });
        console.log(this.state.arrayPersonas[0]);
    }

    llamarFuncion(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        this.añadirPersona();
    }

    render() {
        return (
            <div>
                {this.state.arrayPersonas?.map((persona: Persona) => {
                    return (
                        <PersonaCard statepadre={persona} modificarstatepadre={this.modificarState}/>
                    );
                })
                }
                <button onClick={this.llamarFuncion}>+</button>
            </div>
        )
    }

}