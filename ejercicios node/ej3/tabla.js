function getTabla(tabla) {
  let limite = 10;
  let respuesta = "";
  for (let i = 0; i <= limite; i++) {
    respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
  }
  return respuesta;
}
console.log(getTabla(4));