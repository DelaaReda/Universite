package Proj.Reda.Classes;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CoursDonne {

    @Id
    @GeneratedValue(generator = "cour-generator")
    @GenericGenerator(name = "cour-generator",
            parameters = @Parameter(name = "prefix", value = "COURDOONEE"),
            strategy = "Proj.Reda.Helper.CustomGenericGenerator")
    private String id;

    @Column
    @NotNull
    private char jourSemaine;
}
