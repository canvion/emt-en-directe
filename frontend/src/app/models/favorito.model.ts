export interface Favorito {
  id: number;
  usuarioId: number;
  usuarioUsername: string;
  paradaId: number;
  paradaNombre: string;
  paradaCodigo: string;
  paradaLatitud: number;
  paradaLongitud: number;
  createdAt: string;
  lineas?: string[];
}

export interface FavoritoRequest {
  paradaId: number;
}
