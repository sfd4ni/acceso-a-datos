const {escribir} = require("./utils/manejarFichero");
const {crearTabla} = require("./modelo/tabla");
const yargs = require('yargs/yargs')
const { hideBin } = require('yargs/helpers')

const argv = yargs(hideBin(process.argv)).argv;
console.log(argv);
/*if (process.argv[2] != null) {
  let parametroEntrada = process.argv[2];

  escribir("tabla.txt", crearTabla(parametroEntrada))
    .then(console.log("ok grabado"))
    .catch(err => console.log("error al grabar"));
} else {
  console.log("Pase parámetro de entrada.");
}*/
if (argv.tabla != null) {
  escribir("tabla.txt", crearTabla(argv.tabla))
    .then(console.log("ok grabado"))
    .catch(err => console.log("error al grabar"));
} else {
  console.log("El método de uso es: node app --tabla=<numero>");
}

