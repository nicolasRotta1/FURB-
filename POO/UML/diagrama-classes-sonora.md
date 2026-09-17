# Diagrama de Classes - Sonora

```mermaid
classDiagram
    class Plataforma {
        - ArrayList<Musica> musicas
        - ArrayList<Usuario> usuarios
        - ArrayList<Playlist> playlists

        + Plataforma()
        + cadastrarMusica(Musica) boolean
        + getMusica(int) Musica
        + getMusicaPorTitulo(String) Musica
        + atualizarMusica(int, String, String, int) boolean
        + cadastrarPlaylist(Playlist) boolean
        + getPlaylists() Playlist[]
        + removerMusica(int) boolean
        + cadastrarUsuario(Usuario) boolean
        + getUsuario(int) Usuario
        + atualizarUsuario(int, String, String) boolean
        + removerUsuario(int) boolean
        + getTotalMusicas() int
        + getMusicas() Musica[]
        + getUsuarios() Usuario[]
        + getTotalUsuarios() int
    }

    class Usuario {
        - static int proximoId
        - int id
        - String nome
        - String email
        - ArrayList<Usuario> seguindo

        + Usuario(String, String)
        + getId() int
        + getNome() String
        + validarNome(String) void
        + setNome(String) void
        + validarEmail(String) void
        + getEmail() String
        + setEmail(String) void
        + seguir(Usuario) boolean
        + deixarDeSeguir(Usuario) boolean
        + getSeguindo() ArrayList<Usuario>
        + getQuantidadeSeguindo() int
    }

    class Playlist {
        - String nome
        - Usuario dono
        - ArrayList<Musica> musicas
        - Plataforma plataforma

        + Playlist(String, Usuario)
        + Playlist(String, Usuario, Plataforma)
        + adicionar(Musica) boolean
        + getNaPosicao(int) Musica
        + getTitulo(String) Musica
        + atualizarPlaylist(int, String, String, int) boolean
        + removerNaPosicao(int) boolean
        + getDuracaoTotalSegundos() int
        + reproduzirTudo() void
        + getNome() String
        + validaNome(String) void
        + setNome(String) void
        + validarDono(Usuario) void
        + setDono(Usuario) void
        + getDono() Usuario
        + getQuantidade() int
    }

    class Musica {
        - static int proximoId
        - int id
        - String titulo
        - String artista
        - int duracaoSegundos
        - int reproducoes

        + Musica(String, String, int)
        + reproduzir() void
        + getId() int
        + getTitulo() String
        + validarTitulo(String) void
        + validarArtista(String) void
        + setTitulo(String) void
        + setArtista(String) void
        + validarDuracao(int) void
        + setDuracaoSegundos(int) void
        + getArtista() String
        + getDuracaoSegundos() int
        + getDuracaoFormatada() String
        + getReproducoes() int
    }

    Plataforma "1" o-- "0..*" Musica : armazena
    Plataforma "1" o-- "0..*" Usuario : cadastra
    Plataforma "1" o-- "0..*" Playlist : registra

    Usuario "0..*" --> "0..*" Usuario : segue
    Playlist "0..*" --> "1" Usuario : possui dono
    Playlist "0..*" --> "0..*" Musica : contém
    Playlist "0..*" --> "0..1" Plataforma : pode pertencer a
```

## Revisão das relações
- `Plataforma` é um agregador de músicas, usuários e playlists.
- `Usuario` tem associação reflexiva: um usuário pode seguir outros usuários.
- `Playlist` possui um dono (`Usuario`) e uma coleção de músicas.
- A referência a `Plataforma` em `Playlist` é opcional, não composição obrigatória.
- Não há `App` no diagrama, conforme solicitado.
