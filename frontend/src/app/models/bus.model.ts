export interface Bus{
  id: number;
  matricula: string;
  latitud: number;
  longitud: number;
  linea: {
    id: number;
    nombre: string;
    numero: string;
  }
}
