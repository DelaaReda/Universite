//package Proj.Reda.Classes;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//public class CoursDonne {
//
//    @Id
//    @GeneratedValue
//    private String id;
//
//    @Column
//    @NotNull
//    private char jourSemaine;
//
//    @ManyToOne
//    @JoinColumn(name ="FK_COURS_ID")
//    Cour cour;
//
//    @ManyToOne
//    @JoinColumn(name ="FK_SESSION_ID")
//    Session session;
//
//
//}
