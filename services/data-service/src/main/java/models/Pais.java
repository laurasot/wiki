package models;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paises")
public class Pais {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pais")
	private Number codPais;

    @Column(name = "nom_pais")
    private String nomPais;

    @OneToMany(mappedBy="cod_pais", fetch = FetchType.LAZY)
	private Collection<Region> regiones;

}
