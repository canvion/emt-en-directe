export interface Linea {
  id: number;
  numero: string;
  nombre: string;
  color: string;
  createdAt?: string;
  updatedAt?: string;
  paradas?: ParadaResumen[];
}

export interface ParadaResumen {
  id: number;
  codigo: string;
  nombre: string;
  orden: number;
  esParada: boolean;
  latitud: number;
  longitud: number;
}
