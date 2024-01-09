package models;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "arboles")
public class Arbol {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_arbol")
	private Number codArbol;
    
    
    @Column(name = "nom_arbol")
    private String nomArbol;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE})
        @JoinTable(
            name = "arboles_paises", 
            joinColumns = @JoinColumn(name = "cod_arbol"), 
            inverseJoinColumns = @JoinColumn(name = "cod_pais")
        )
        private Collection<Pais> paises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cod_categoria")
    private Categoria categoria;

}
