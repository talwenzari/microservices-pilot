package fi.talwenzari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class SongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongServiceApplication.class, args);
	}
}
@Component
class DummyDataCLR implements CommandLineRunner {
	@Autowired
	private SongRepository songRepository;

	@Override
	public void run(String... strings) throws Exception {
		Stream.of("Like a Rolling Stone", "All Along the Watchtower", "All the Tired Horses")
				.forEach(title -> songRepository.save(new Song(title, "Bob Dylan", null)));
	}
}
@RepositoryRestResource
interface SongRepository extends JpaRepository<Song, Long> {

}
@Entity
class Song {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String writer;

	private Integer year;

	public Song() {
	}

	public Song(String title, String writer, Integer year) {
		this.title = title;
		this.writer = writer;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
