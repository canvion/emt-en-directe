export interface Parada {
  id: number;
  nombre: string;
  codigo: string;
  latitud: number;
  longitud: number;
  esParada: boolean;
  createdAt?: string;
  updatedAt?: string;
  lineas?: LineaResumen[];
}


export interface LineaResumen {
  id: number;
  numero: string;
  nombre: string;
  color: string;
  orden: number;
}
