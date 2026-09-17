# Diagrama de classes do Sonora

## Relacionamentos principais

1. Plataforma -- acervo -- Musica
   - Papel: Plataforma: acervo / Musica: catalogada
   - Nome: Plataforma cadastra Musica
   - Multiplicidade: Plataforma 1 para 0..* Musica
   - Navegabilidade: bidirecional, pois a plataforma acessa as músicas e uma música pode ser referenciada pela plataforma

2. Plataforma -- registrados -- Usuario
   - Papel: Plataforma: cadastro / Usuario: participante
   - Nome: Plataforma registra Usuario
   - Multiplicidade: Plataforma 1 para 0..* Usuario
   - Navegabilidade: bidirecional, pois a plataforma guarda os usuários e pode consultar o cadastro do usuário

3. Usuario -- dono -- Playlist
   - Papel: Usuario: dono / Playlist: pertencente
   - Nome: Usuario cria Playlist
   - Multiplicidade: Usuario 1 para 0..* Playlist
   - Navegabilidade: bidirecional, pois o usuário conhece as playlists que possui e a playlist conhece seu dono

4. Usuario -- seguindo -- Usuario
   - Papel: Usuario: seguindo / Usuario: seguidores
   - Nome: Usuario segue Usuario
   - Multiplicidade: Usuario 0..* para 0..* Usuario
   - Navegabilidade: bidirecional, porque um usuário pode navegar para quem segue e também saber quem o segue no modelo de associação reflexiva

## Observações

- A associação de `Playlist` para `Musica` foi tratada como um relacionamento de composição/associação de coleção: a playlist guarda várias músicas.
- A troca de vetores por `ArrayList` reforça o lado “muitos” do relacionamento, que vira coleção em memória.
- A associação reflexiva de `Usuario` usa uma lista de usuários seguidos, com validações de auto-seguimento e duplicidade.
