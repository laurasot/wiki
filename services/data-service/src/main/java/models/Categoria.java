package models;


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
@Table(name = "categorias")
public class Categoria {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria")
	private Number codCategoria;


    @Column(name = "nom_categoria", length = 20)
    private String nomCategoria;

    @OneToMany(mappedBy="hostUsuario", fetch = FetchType.LAZY)
	private java.util.Collection<Arbol> arboles;

}
