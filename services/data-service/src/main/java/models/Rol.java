package models;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_rol")
	private Number codRol;

    @Column(name = "nom_rol", nullable = false, length = 10)
    private String nomRol;

    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios;


    @ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
            name = "cod_rol"), 
        inverseJoinColumns = @JoinColumn(
            name = "privilege_id"))
    private Collection<Privilegio> privilegios;

    public void setPrivilegios(Privilegio privilegio){
        privilegios.add(privilegio);
    }
}
