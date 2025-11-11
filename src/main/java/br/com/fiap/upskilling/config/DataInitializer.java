package br.com.fiap.upskilling.config;

import br.com.fiap.upskilling.model.Trilha;
import br.com.fiap.upskilling.model.Usuario;
import br.com.fiap.upskilling.repository.TrilhaRepository;
import br.com.fiap.upskilling.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, TrilhaRepository trilhaRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario usuario1 = new Usuario();
                usuario1.setNome("Carlos Santos");
                usuario1.setEmail("carlos.santos@email.com");
                usuario1.setAreaAtuacao("Desenvolvimento");
                usuario1.setNivelCarreira("Pleno");
                usuario1.setDataCadastro(LocalDate.of(2025, 1, 15));
                usuarioRepository.save(usuario1);

                Usuario usuario2 = new Usuario();
                usuario2.setNome("Maria Oliveira");
                usuario2.setEmail("maria.oliveira@email.com");
                usuario2.setAreaAtuacao("Design");
                usuario2.setNivelCarreira("Senior");
                usuario2.setDataCadastro(LocalDate.of(2025, 2, 20));
                usuarioRepository.save(usuario2);

                Usuario usuario3 = new Usuario();
                usuario3.setNome("João Pereira");
                usuario3.setEmail("joao.pereira@email.com");
                usuario3.setAreaAtuacao("Marketing");
                usuario3.setNivelCarreira("Junior");
                usuario3.setDataCadastro(LocalDate.of(2025, 3, 10));
                usuarioRepository.save(usuario3);

                Usuario usuario4 = new Usuario();
                usuario4.setNome("Ana Costa");
                usuario4.setEmail("ana.costa@email.com");
                usuario4.setAreaAtuacao("Gestão de Projetos");
                usuario4.setNivelCarreira("Senior");
                usuario4.setDataCadastro(LocalDate.of(2025, 4, 5));
                usuarioRepository.save(usuario4);

                System.out.println("✅ 4 usuários inseridos com sucesso!");
            }

            if (trilhaRepository.count() == 0) {
                Trilha trilha1 = new Trilha();
                trilha1.setNome("Java Avançado");
                trilha1.setDescricao("Domine conceitos avançados de Java, incluindo concorrência, streams e Spring Framework");
                trilha1.setNivel("AVANCADO");
                trilha1.setCargaHoraria(120);
                trilha1.setFocoPrincipal("Backend");
                trilhaRepository.save(trilha1);

                Trilha trilha2 = new Trilha();
                trilha2.setNome("Python para Data Science");
                trilha2.setDescricao("Aprenda Python aplicado à ciência de dados com Pandas, NumPy e Machine Learning");
                trilha2.setNivel("INTERMEDIARIO");
                trilha2.setCargaHoraria(80);
                trilha2.setFocoPrincipal("Data Science");
                trilhaRepository.save(trilha2);

                Trilha trilha3 = new Trilha();
                trilha3.setNome("Fundamentos de IA");
                trilha3.setDescricao("Introdução à Inteligência Artificial e suas aplicações práticas no mercado de trabalho");
                trilha3.setNivel("INICIANTE");
                trilha3.setCargaHoraria(60);
                trilha3.setFocoPrincipal("IA");
                trilhaRepository.save(trilha3);

                Trilha trilha4 = new Trilha();
                trilha4.setNome("DevOps Essencial");
                trilha4.setDescricao("Aprenda Docker, Kubernetes, CI/CD e práticas modernas de DevOps");
                trilha4.setNivel("INTERMEDIARIO");
                trilha4.setCargaHoraria(100);
                trilha4.setFocoPrincipal("DevOps");
                trilhaRepository.save(trilha4);

                Trilha trilha5 = new Trilha();
                trilha5.setNome("Soft Skills para Liderança");
                trilha5.setDescricao("Desenvolva habilidades de comunicação, empatia e gestão de equipes");
                trilha5.setNivel("INICIANTE");
                trilha5.setCargaHoraria(40);
                trilha5.setFocoPrincipal("Soft Skills");
                trilhaRepository.save(trilha5);

                System.out.println("✅ 5 trilhas inseridas com sucesso!");
            }

            System.out.println("🚀 Dados iniciais carregados no banco H2!");
        };
    }
}
