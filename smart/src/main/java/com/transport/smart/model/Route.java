package com.transport.smart.model;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;

@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        ),
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
@Entity
@Table(name="routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;

    @Type( type = "string-array" )
    @Column(
            name = "stations",
            columnDefinition = "text[]"
    )
    private String[] stations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String[] getStations() {
        return stations;
    }

    public void setStations(String[] stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", stations=" + Arrays.toString(stations) +
                '}';
    }
}