package models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "usuarios_roles")
public class UsuarioRol {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuariorol")
	private Number codUsuarioRol;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Rol rol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRol that)) return false;
        return codUsuarioRol.equals(that.codUsuarioRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codUsuarioRol);
    }
}
