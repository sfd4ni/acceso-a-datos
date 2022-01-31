import React from 'react';

interface IProps { }
interface IState { numero: number }

class OperarBotones extends React.Component<IProps, IState> {
  constructor(props: IProps)  {
    super(props);
    this.state = { numero: 1 };
    //this.operarBoton = this.operarBoton.bind(this);
  }
  
  


  render() {
    const operarBoton = (idButton: string) => {
      let i = this.state.numero;
      if (idButton === "multiplicar") {
        this.setState({numero: i * 2});
      } else {
        this.setState({numero: i / 2});
      }
    }
    const llamarFuncion = (event: React.MouseEvent<HTMLButtonElement>) => {
      event.preventDefault();
      operarBoton(event.currentTarget.id);
    }
    return (
      <div>
        <h4>Valor actual: {this.state.numero}</h4>
        <button onClick={llamarFuncion} id="multiplicar">{this.state.numero} * 2</button>
        <button onClick={llamarFuncion} id="dividir">{this.state.numero} / 2</button>
      </div>
    )
  }
}
export default OperarBotones;