package models;


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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
	private Number codUsuario;

    @NotBlank @NotNull
    @Size(min = 3, max = 12, message = "Ingresa un nombre valido")
    private String nombre;

    @NotBlank @NotNull
    @Size(min = 2, max = 15, message = "Ingresa un apellido valido")
    private String apellido;

    @NotBlank @NotNull
    @Size(min = 8, max = 50, message = "Ingresa un email valido")
    private String email;


    private int tipoUsuario = 1; //usuario normal, por defecto
    
    @NotNull @NotBlank
    private String password;

    @Transient
	private String passwordConfirmation;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE})
        @JoinTable(
            name = "usuarios_roles", 
            joinColumns = @JoinColumn(name = "cod_usuario"), 
            inverseJoinColumns = @JoinColumn(name = "cod_rol")
        )
        private java.util.Collection<Rol> roles;


        public void setRoles(Rol rol){
            roles.add(rol);
        }
}
