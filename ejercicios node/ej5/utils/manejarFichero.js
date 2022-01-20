const fs = require('fs');
async function escribir(nombreFichero, datos) {
  fs.writeFileSync(nombreFichero, //nombre del fichero
  datos
);
}
exports.escribir = escribir;
