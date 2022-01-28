import React from "react";
interface IProps { }
interface IState { tiempoRestante: number }
class Cronometro extends React.Component<IProps, IState> {
    timerID: any;
    estadoBoton: string;
    inputTiempo: any;
    constructor(props:IProps) {
        super(props);
        this.state = { tiempoRestante: 0 };
        this.inputTiempo = React.createRef();
        this.estadoBoton = "Iniciar";
    }
    componentDidMount() {

    }
    componentWillUnmount() {
        alert("¡Tiempo completado!");
        clearInterval(this.timerID);
    }


    tick() {
        let i = this.state.tiempoRestante - 1;
        this.setState({ tiempoRestante: i });
        if ( i === 0) {
            this.estadoBoton = "Iniciar";
            this.componentWillUnmount();
            this.setState({tiempoRestante: 0})
        }
    }

    render() {
        const iniciarCronometro = () => {
            if (this.estadoBoton === "Iniciar") {
                this.timerID = setInterval(
                    () => this.tick(),
                    1000
                    );
                this.setState({ tiempoRestante: this.inputTiempo.current.value })
                this.estadoBoton = "Parar";
            } else {
                this.componentWillUnmount();
                this.estadoBoton = "Iniciar";
                this.setState({ tiempoRestante: 0 });
            }
            
        }
        return (
            <div>
                <h1>Cronómetro</h1>
                Cantidad segundos: <input type="text" ref={this.inputTiempo} />
                <br /><button onClick={iniciarCronometro}> {this.estadoBoton} </button>
                <h2>Quedan: {this.state.tiempoRestante} segundos.</h2>
            </div>
        );
    }
}
export default Cronometro;