package rgmek.backend.dto.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "adressall")
public class AdressAll {
    @Id
    @Column(length = 61)
    private String code;

    @Column(name = "POSTINDEX", length = 20)
    private String postindex;

    @Column(name = "PUNKTNM", length = 200)
    private String punktnm;

    @Column(name = "DISTNM", length = 30)
    private String distnm;

    @Column(name = "STREETNM", length = 50)
    private String streetnm;

    @Column(name = "HOUSENO", length = 10)
    private String houseno;

    @Column(name = "HOUSEPOSTFIX", length = 50)
    private String housepostfix;

    @Column(name = "HOUSENAME", length = 10)
    private String housename;

    @Column(name = "NUMBEROFKVART", nullable = true)
    private Integer numberofkvart;

    @Column(name = "KORPUSNO", nullable = true)
    private Integer korpusno;

    @Column(name = "KORPUSPOSTFIX", length = 10)
    private String korpuspostfix;

    @Column(name = "KORPUSNAME", length = 10)
    private String korpusname;

    @Column(name = "FLOORCOUNT", nullable = true)
    private Integer floorcount;

    @Column(name = "FLATNO", nullable = true)
    private Short flatno;

    @Column(name = "FLATPOSTFIX", length = 50)
    private String flatpostfix;

    @Column(name = "FLATNAME", length = 10)
    private String flatname;

    @Column(name = "ROOMNO", nullable = true)
    private Short roomno;

    @Column(name = "ROOMPOSTFIX", length = 10)
    private String roompostfix;

    @Column(name = "ROOMNAME", length = 10)
    private String roomname;

    @Column(name = "FIAS", length = 50)
    private String fias;

    @Column(name = "search", length = 512)
    private String search;

    @Column(name = "addressfull", length = 512)
    private String adressfull;

    @Column(name = "kadastr", length = 50)
    private String kadastr;
}
