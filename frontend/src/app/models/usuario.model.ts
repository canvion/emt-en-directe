export interface Usuario{
  id: number;
  nombre: string;
  email: string;
}

export interface JwtResponse{
  token: string;
  usuario: Usuario;
}

export interface LoginRequest{
  email: string;
  password: string;
}

export interface RegisterRequest{
  nombre: string;
  email: string;
  password: string;
}
