import { Parada } from "./parada.model";

export interface Favorito{
  id: number;
  parada: Parada;

}

export interface FavoritoRequest{
  paradaId: number;
}
