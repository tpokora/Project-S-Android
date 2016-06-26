package projects.tpokora.com.project_s_android.rest.model;

/**
 * Created by pokor on 26.06.2016.
 */
public class AbstractEntity {
    private Integer id;

    public AbstractEntity() {

    }

    public AbstractEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
